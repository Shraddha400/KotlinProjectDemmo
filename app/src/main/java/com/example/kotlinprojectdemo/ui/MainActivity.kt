package com.example.kotlinprojectdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinprojectdemo.R
import com.example.kotlinprojectdemo.model.AdapterClass
import com.example.kotlinprojectdemo.model.MainViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MAINACTIVITY"

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.getComments().observe(this, { comments ->
            Log.d("Main", "onCreate: $comments")
             val adapter = AdapterClass(comments)
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(baseContext)
            recyclerView.setHasFixedSize(true)
        })


    }

}