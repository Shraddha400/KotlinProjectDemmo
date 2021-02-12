package com.example.kotlinprojectdemo.network

import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.utils.AppConstants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MyAPI {

    @GET(AppConstants.GET_COMMENTS)
    /**
     * need to change the response to observer and assign to type observable  of particularly RXJAVA
     * */
    fun getComment(): Observable<List<CommentResponseItem>>
}