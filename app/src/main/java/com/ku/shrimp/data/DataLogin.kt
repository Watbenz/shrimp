package com.ku.shrimp.data

import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("username") val username: String,
    @SerializedName("hashCode") val hashCode: String,
    @SerializedName("isLogin") val isLogin: Boolean
)