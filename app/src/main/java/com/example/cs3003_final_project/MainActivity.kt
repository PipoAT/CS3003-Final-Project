package com.example.cs3003_final_project

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cs3003_final_project.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var runningStopwatch1: Boolean = false
    private var startTimeStopwatch1: Long = 0
    private var elapsedTimeStopwatch1: Long = 0
    private var stopwatchJobStopwatch1: Job? = null

    private var runningStopwatch2: Boolean = false
    private var startTimeStopwatch2: Long = 0
    private var elapsedTimeStopwatch2: Long = 0
    private var stopwatchJobStopwatch2: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView1: TextView = binding.textStopwatch1
        val textView2: TextView = binding.textStopwatch2

        val startButton1: Button = binding.btnStartStopwatch1
        val stopButton1: Button = binding.btnStopStopwatch1
        val resetButton1: Button = binding.btnResetStopwatch1

        val startButton2: Button = binding.btnStartStopwatch2
        val stopButton2: Button = binding.btnStopStopwatch2
        val resetButton2: Button = binding.btnResetStopwatch2

        startButton1.setOnClickListener {
            if (!runningStopwatch1) {
                startTimeStopwatch1 = System.currentTimeMillis() - elapsedTimeStopwatch1
                runningStopwatch1 = true
                startStopwatch1()
            }
            val noCoroutines = NoCoroutines()
            noCoroutines.main()

        }

        stopButton1.setOnClickListener {
            if (runningStopwatch1) {
                runningStopwatch1 = false
                stopwatchJobStopwatch1?.cancel()
            }

        }

        resetButton1.setOnClickListener {
            if (!runningStopwatch1) {
                elapsedTimeStopwatch1 = 0
                updateElapsedTime(textView1, elapsedTimeStopwatch1)
            }
        }

        startButton2.setOnClickListener {
            if (!runningStopwatch2) {
                startTimeStopwatch2 = System.currentTimeMillis() - elapsedTimeStopwatch2
                runningStopwatch2 = true
                startStopwatch2()
            }
            val coroutines = Coroutines()
            runBlocking {
                coroutines.main()
            }
        }

        stopButton2.setOnClickListener {
            if (runningStopwatch2) {
                runningStopwatch2 = false
                stopwatchJobStopwatch2?.cancel()
            }
        }

        resetButton2.setOnClickListener {
            if (!runningStopwatch2) {
                elapsedTimeStopwatch2 = 0
                updateElapsedTime(textView2, elapsedTimeStopwatch2)
            }
        }
    }

    override fun onDestroy() {
        if (runningStopwatch1) {
            runningStopwatch1 = false
            stopwatchJobStopwatch1?.cancel()
        }
        if (runningStopwatch2) {
            runningStopwatch2 = false
            stopwatchJobStopwatch2?.cancel()
        }
        super.onDestroy()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun startStopwatch1() {
        stopwatchJobStopwatch1 = GlobalScope.launch(Dispatchers.Main) {
            while (runningStopwatch1) {
                val currentTime = System.currentTimeMillis()
                elapsedTimeStopwatch1 = currentTime - startTimeStopwatch1
                updateElapsedTime(binding.textStopwatch1, elapsedTimeStopwatch1)
                delay(1000)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun startStopwatch2() {
        stopwatchJobStopwatch2 = GlobalScope.launch(Dispatchers.Main) {
            while (runningStopwatch2) {
                val currentTime = System.currentTimeMillis()
                elapsedTimeStopwatch2 = currentTime - startTimeStopwatch2
                updateElapsedTime(binding.textStopwatch2, elapsedTimeStopwatch2)
                delay(1000)
            }
        }
    }

    private fun updateElapsedTime(textView: TextView, elapsedTime: Long) {
        val formattedTime = String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(elapsedTime),
            TimeUnit.MILLISECONDS.toMinutes(elapsedTime) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % TimeUnit.MINUTES.toSeconds(1)
        )
        textView.text = "Time: $formattedTime"
    }
}
