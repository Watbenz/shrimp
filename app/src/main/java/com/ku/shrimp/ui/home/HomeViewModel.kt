package com.ku.shrimp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment ..............."
    }
//    private val _list: ArrayList<String> = MutableLiveData<ArrayList<String>>()
//
//        arrayListOf<String>().apply {
//        addAll(arrayOf("A", "B", "C"))
//    }
    private val _list = MutableLiveData<ArrayList<String>>().apply { value?.addAll(arrayOf("A", "B", "C")) }

    val text: LiveData<String> = _text
    val list: LiveData<ArrayList<String>> = _list
}