package com.example.kotlinprojectdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.network.MyAPI
import com.example.kotlinprojectdemo.network.RetrofitBuilder
import com.example.kotlinprojectdemo.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val ioDispacher: CoroutineDispatcher = Dispatchers.IO
    private val myAPI: MyAPI = RetrofitBuilder.api
    private val appRepository = AppRepository(ioDispacher, myAPI)

    private var _comments = MutableLiveData<List<CommentResponseItem>>()

    val comments: LiveData<List<CommentResponseItem>> = _comments

    fun getComments() {
        viewModelScope.launch {
            _comments.postValue(appRepository.getComment())
        }
    }


    override fun onCleared() {
        super.onCleared()
        ioDispacher.cancel()
    }
}