package com.example.kotlinprojectdemo.repository

import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.network.MyAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRepository(
    private val iODispatcher: CoroutineDispatcher,
    private val myAPI: MyAPI
) {

    suspend fun getComment(): MutableList<CommentResponseItem> {
        var comments = mutableListOf<CommentResponseItem>()
        withContext(iODispatcher) {
            val responses = myAPI.getComment()
            responses.body()?.let { comments.addAll(it) }
        }
        return comments
    }

}