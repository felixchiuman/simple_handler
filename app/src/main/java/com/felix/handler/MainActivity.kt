package com.felix.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.felix.handler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //define our handler
    private lateinit var binding : ActivityMainBinding
    private var mHandler: Handler? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mHandler = Handler(mainLooper)
        binding.btnStart.setOnClickListener {
            startProgress()
        }
    }

    private fun startProgress() {
        //new thread to perform background operation
        Thread{
            for (i in 0..30){
                try {
                    Thread.sleep(500)
                } catch (e : InterruptedException){
                    e.printStackTrace()
                }

                // update the valur background thread to ui thread
                mHandler!!.post {binding.progressHorizontal.progress = i}
            }
        }.start()
    }
}
