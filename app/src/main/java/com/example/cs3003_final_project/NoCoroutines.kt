package com.example.cs3003_final_project

class NoCoroutines {
    fun run() {
        val startTime = System.currentTimeMillis()
        val result = longRunningTask()
        val endTime = System.currentTimeMillis()
        println("Result: $result")
        println("Time taken: ${endTime - startTime}ms")
    }

    private fun longRunningTask(): Int {
        // Simulate a long running task
        Thread.sleep(5000)
        return 42
    }
}
