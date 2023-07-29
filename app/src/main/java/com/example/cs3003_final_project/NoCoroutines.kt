package com.example.cs3003_final_project

import kotlin.concurrent.thread
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class NoCoroutines {

    private suspend fun doWork(): Int {
        delay(1000L)
        return 42
    }

    fun main() {
        // Without coroutines
        println("Without coroutines:")
        val time = measureTimeMillis {
            val results = List(10) {
                thread {
                    runBlocking { doWork() }
                }
            }
            results.forEach { it.join() }
        }
        println("Elapsed time: $time ms")
    }
}
