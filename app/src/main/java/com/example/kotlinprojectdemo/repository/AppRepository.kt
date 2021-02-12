package com.example.kotlinprojectdemo.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinprojectdemo.data.CommentResponseItem
import com.example.kotlinprojectdemo.network.MyAPI
import com.example.kotlinprojectdemo.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.util.*

class AppRepository {

    val myApi = RetrofitBuilder.api

    fun getComment(): LiveData<List<CommentResponseItem>> {
        var comments = MutableLiveData<List<CommentResponseItem>>()

        val call = myApi.getComment()

        call.enqueue(object : Callback<List<CommentResponseItem>> {
            override fun onResponse(
                call: Call<List<CommentResponseItem>>,
                response: Response<List<CommentResponseItem>>
            ) {
                if (response.isSuccessful) {
                    val responseItem = response.body()
                    comments.postValue(responseItem!!)
                }
            }

            override fun onFailure(call: Call<List<CommentResponseItem>>, t: Throwable) {
                t.printStackTrace()
            }

        })

        return comments
    }

}