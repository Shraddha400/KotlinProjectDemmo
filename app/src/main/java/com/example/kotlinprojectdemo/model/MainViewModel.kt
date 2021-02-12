package com.example.kotlinprojectdemo.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinprojectdemo.data.CommentResponseItem
import com.example.kotlinprojectdemo.network.RetrofitBuilder
import com.example.kotlinprojectdemo.repository.AppRepository

class MainViewModel : ViewModel() {

    private val appRepository = AppRepository()

    fun getComments(): LiveData<List<CommentResponseItem>> {
        return appRepository.getComment()

    }

}