package com.polishchuk.level1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textFirstLastName : TextView
    private lateinit var textCareer : TextView
    private lateinit var textHomeAddress : TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textFirstLastName = findViewById(R.id.textFirstLastName)
        textCareer = findViewById(R.id.textCareer)
        textHomeAddress = findViewById(R.id.textHomeAddress)
        textCareer.text = "Android developer"
        textHomeAddress.text = "I stay in Ukraine"
    }
}