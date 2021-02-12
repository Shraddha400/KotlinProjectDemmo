package com.example.kotlinprojectdemo.network

import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET

interface MyAPI {

    @GET(AppConstants.GET_COMMENTS)
   suspend fun getComment(): Response<List<CommentResponseItem>>
}