package com.example.kotlinprojectdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinprojectdemo.R
import com.example.kotlinprojectdemo.model.Test
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.canonicalName
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        Log.d(TAG, "onCreate: ${Thread.currentThread().name}")
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.comments.observe(this) { comments ->
            Log.d(TAG, "onCreate: $comments")
            val adapter = AdapterClass(comments)
            val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(baseContext)
            recyclerView.setHasFixedSize(true)
        }
        val tutorialDocuments = Firebase.firestore.collection("coroutineFirebase").document("postId")
        val test = Test("SHRADDHA")
        GlobalScope.launch(Dispatchers.IO) {
            tutorialDocuments.set(test).await()
            // Now to get from firestore and set in the main UI thread
            /** Dont forget to add google play service coroutine dependency*/
            val person = tutorialDocuments.get().await().toObject(Test::class.java)
            withContext(Dispatchers.Main) {
                val itemText: TextView = findViewById(R.id.textFromFirebase)
                itemText.text = person.toString()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        mainViewModel.getComments()

        /** for observing Live data from server through view Model */
    }
}