package com.example.components.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.components.R
import com.example.todoapp.appComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.mainActivityComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.observe(this) { strategy ->
            strategy.navigate(supportFragmentManager, R.id.fragment_container)
        }
        mainViewModel.init(savedInstanceState == null)
    }

}