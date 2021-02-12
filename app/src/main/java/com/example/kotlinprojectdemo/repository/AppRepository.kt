package com.example.kotlinprojectdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.network.MyAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRepository(
    private val myAPI: MyAPI,
    private val disposable: CompositeDisposable
) {

    private var _comments = MutableLiveData<List<CommentResponseItem>>()

     val comments: LiveData<List<CommentResponseItem>> = _comments

    fun getComment() {
       val disposing = myAPI.getComment()
           .subscribeOn(Schedulers.io()) /** Request On IO thread*/
           .observeOn(AndroidSchedulers.mainThread())/** Result to main thread or observe on main thread */
           .subscribe({ response ->  // succes response
                    _comments.postValue(response)
           }, { error -> // network failure

           }) /** Data will come here as per active set */
        disposable.add(disposing)
    }

}