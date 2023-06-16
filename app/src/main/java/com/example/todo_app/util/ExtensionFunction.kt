package com.example.todo_app.util

import android.os.Handler
import android.os.Looper

fun executeDelayed(delay: Long = 200L, runnable: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        runnable.invoke()
    }, delay)
}