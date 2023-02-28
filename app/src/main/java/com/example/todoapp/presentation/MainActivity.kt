package com.example.todoapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.R

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

//        runBlocking {
//            val cloudDataSource = TasksCloudDataSourceImpl(
//                RetrofitBuild().providesApiService(),
//                TaskCloudToDataMapper(),
//                TaskDataToCloudMapper()
//            )
//
//            val taskData1 = TaskData(
//                id = 15L, text = "new new text", importance = "low",
//                deadline = 0L, isDone = false, createdAt = 100L, changedAt = 400L
//            )
//            val taskData2 = TaskData(
//                id = 25L, text = "new text", importance = "basic",
//                deadline = 0L, isDone = false, createdAt = 100L, changedAt = 400L
//            )
////            cloudDataSource.addTask(taskData)
//            try {
//                cloudDataSource.editTasks(listOf(taskData1, taskData2))
//            } catch (e: Exception) {
//                println(1)
//            }
//
//        }
    }
}