package com.example.cs3003_final_project

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class Coroutines {

    private suspend fun doWork(): Int {
        delay(1000L)
        return 42
    }
    fun main() {
        // With coroutines
        println("\nWith coroutines:")
        val time2 = measureTimeMillis {
            runBlocking {
                val results = List(10) {
                    async { doWork() }
                }
                results.awaitAll()
            }
        }
        println("Elapsed time: $time2 ms")
    }


}

