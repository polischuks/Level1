package com.polishchuk.level1.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.polishchuk.level1.R

class MainActivity : AppCompatActivity() {

    private lateinit var textFirstLastName: TextView
    private lateinit var textCareer: TextView
    private lateinit var textHomeAddress: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userName = intent.getStringExtra("userName").toString()
        textFirstLastName = findViewById(R.id.textFirstLastName)
        textCareer = findViewById(R.id.textCareer)
        textHomeAddress = findViewById(R.id.textHomeAddress)

        textFirstLastName.text = userName
    }
}