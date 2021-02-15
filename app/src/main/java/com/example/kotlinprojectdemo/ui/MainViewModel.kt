package com.example.kotlinprojectdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.network.MyAPI
import com.example.kotlinprojectdemo.network.RetrofitBuilder
import com.example.kotlinprojectdemo.repository.AppRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
class MainViewModel : ViewModel() {
    private var _comments = MutableLiveData<List<CommentResponseItem>>()
    val comments: LiveData<List<CommentResponseItem>> = _comments
    private val iodispacher: CoroutineDispatcher= Dispatchers.IO
    private val myAPI: MyAPI = RetrofitBuilder.api
    private val appRepository = AppRepository(myAPI, iodispacher)
    fun getComments(){
        viewModelScope.launch{
            appRepository.getComments().collect { commentList -> _comments.postValue(commentList)}
        }
    }
    override fun onCleared() {
        super.onCleared()
        iodispacher.cancel()
    }
}