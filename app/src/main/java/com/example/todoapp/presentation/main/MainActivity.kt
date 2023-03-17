package com.example.todoapp.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.App
import com.example.todoapp.R
import com.example.todoapp.appComponent
import com.example.todoapp.di.mainactivity.MainActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityComponent: MainActivityComponent

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory.Factory
    private val mainViewModel: MainViewModel by viewModels {
        mainViewModelFactory.create(
            (applicationContext as App).provideNavigationCommunication()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivityComponent =
            appComponent.mainActivityComponent().create()
        mainActivityComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.observe(this) { strategy ->
            strategy.navigate(supportFragmentManager, R.id.container)
        }
        mainViewModel.init(savedInstanceState == null)
    }

}
