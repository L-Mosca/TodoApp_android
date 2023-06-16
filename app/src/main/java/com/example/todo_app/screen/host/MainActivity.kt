package com.example.todo_app.screen.host

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todo_app.R
import com.example.todo_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.bind(findViewById(R.id.navHostMain))
    }
}