package com.example.kotlinprojectdemo.repository

import com.example.kotlinprojectdemo.model.CommentResponseItem
import com.example.kotlinprojectdemo.network.MyAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
class AppRepository(
    private val myAPI: MyAPI,
    private val ioDIspacher: CoroutineDispatcher
) {

    fun getComments(): Flow<List<CommentResponseItem>> {
        return flow {
            val response = myAPI.getComment().body()!!
            emit(response)
        }
            .flowOn(ioDIspacher)
    }


}