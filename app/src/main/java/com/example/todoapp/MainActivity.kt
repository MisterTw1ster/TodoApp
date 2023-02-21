package com.example.todoapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.todoapp.datasource.TaskDataParams
import com.example.todoapp.datasource.tasks.cache.TasksCacheDataSourceImpl
import com.example.todoapp.datasource.tasks.cache.mappers.TaskCacheToDataMapper
import com.example.todoapp.datasource.tasks.cache.mappers.TaskDataParamsToCacheMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val text = findViewById<TextView>(R.id.textview)
//
//        val cacheDataSource = TasksCacheDataSourceImpl(
//            RoomBuild().daoRoom(applicationContext),
//            TaskCacheToDataMapper(),
//            TaskDataParamsToCacheMapper()
//        )
//        val newTask = TaskDataParams(
//            text = "text",
//            importance = "low",
//            deadline = 0L,
//            isDone = false,
//            time = System.currentTimeMillis()
//        )
//        runBlocking {
//            cacheDataSource.observeTasks().collect { task ->
//
//            }
//            val id = cacheDataSource.getTaskById(1)
//            val d = id
//            text.setText(id.toString())
//        }

    }
}