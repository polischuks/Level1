package com.polishchuk.level1.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.polishchuk.level1.R

class MainActivity : AppCompatActivity() {

    private lateinit var textFirstLastName : TextView
    private lateinit var textCareer : TextView
    private lateinit var textHomeAddress : TextView

    private var userName = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = intent.getStringExtra("userName").toString()
        textFirstLastName = findViewById(R.id.textFirstLastName)
        textCareer = findViewById(R.id.textCareer)
        textHomeAddress = findViewById(R.id.textHomeAddress)

        textFirstLastName.text = getUserName(userName)
    }

    private fun getUserName(userName: String?): String {
        if (userName != null) {
            this.userName = userName.split("@")[0]

        }
        if (userName != null && !userName.contains(".")) {
            return userName
        }
        val parsStr = userName?.split(".")
        val firstName =
            parsStr?.get(0)?.get(0)?.uppercaseChar()?.plus(
                parsStr[0].substring(1, parsStr[0].length)
            )
        val lastName =
            parsStr?.get(1)?.get(0)?.uppercaseChar()?.plus(
                parsStr[1].substring(1, parsStr[1].length)
            )
        return firstName.plus(" ").plus(lastName)
    }
}