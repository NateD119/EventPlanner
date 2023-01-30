package com.studentnate.week13demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.studentnate.week13demo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text=intent.getStringExtra("OUT_MSG")


    }
}