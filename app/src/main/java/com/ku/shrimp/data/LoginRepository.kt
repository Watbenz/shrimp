package com.ku.shrimp.data

import android.content.Context
import com.google.gson.Gson
import com.ku.shrimp.data.model.LoggedInUser
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var context: Context? = null
    private val FILE_NAME = "login.json"

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            saveLogin(username, password)
            setLoggedInUser(result.data)
        }

        return result
    }

    fun saveLogin(username: String, password: String) {
        // save file in login.json
        val data = DataLogin(username, password, true)
        val json = Gson().toJson(data)
        // check is not null and write file
        context?.let { it ->
            it.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
                it?.write(json.toByteArray())
            }
        }
    }

    fun checkLogin(): Boolean {
        val file = File(context?.filesDir, FILE_NAME)
        if (file.exists()) {
            val fis = InputStreamReader(context!!.openFileInput(FILE_NAME))
            val loginData = Gson().fromJson(BufferedReader(fis), DataLogin::class.java)
            return loginData.isLogin
        }
        return false
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
