package com.example.kotlinprojectdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.network.MyAPI
import com.example.kotlinprojectdemo.network.RetrofitBuilder
import com.example.kotlinprojectdemo.repository.AppRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()
    private val myAPI: MyAPI = RetrofitBuilder.api
    private val appRepository = AppRepository(myAPI, disposable)

    fun getComments(): LiveData<List<CommentResponseItem>> {
        return appRepository.comments
    }

    fun getCommentFromServer() {
        appRepository.getComment()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}