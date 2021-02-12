package com.example.kotlinprojectdemo.network

import com.example.kotlinprojectdemo.data.CommentResponseItem
import com.example.kotlinprojectdemo.utils.AppConstants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MyAPI {

    @GET(AppConstants.GET_COMMENTS)
    fun getComment(): Call<List<CommentResponseItem>>
}