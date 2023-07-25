package com.example.cs3003_final_project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class Coroutines {
    suspend fun main() = coroutineScope {
        val startTime = System.currentTimeMillis()
        val result = async { longRunningTask() }
        val endTime = System.currentTimeMillis()
        println("Result: ${result.await()}")
        println("Time taken: ${endTime - startTime}ms")
    }

    private suspend fun longRunningTask(): Int = withContext(Dispatchers.IO) {
        // Simulate a long running task
        delay(5000)
        return@withContext 42
    }

}