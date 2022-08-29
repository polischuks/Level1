package com.polishchuk.level1.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.polishchuk.level1.R
import com.polishchuk.level1.controller.AppController

class AuthActivity : AppCompatActivity() {

    private lateinit var btnReg : Button
    private lateinit var emailField : EditText
    private lateinit var passField : EditText
    private lateinit var signIn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val controller = AppController(this)

        btnReg = findViewById(R.id.btnReg)
        emailField = findViewById(R.id.editTextEmailAddress)
        passField = findViewById(R.id.editTextTextPassword)
        signIn = findViewById(R.id.textViewSignIn)

        if (controller.hasVisited) {
            emailField.text = Editable.Factory.getInstance()
                .newEditable(controller.user.userEmail)
        }

        btnReg.setOnClickListener {
            controller.emailUser = emailField.text.toString()
            controller.passUser = passField.text.toString()
            if (!controller.emailIsValid()) {
                Toast.makeText(this, R.string.error_user_name, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (controller.passIsEmpty()) {
                Toast.makeText(this, R.string.pass_empty, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (controller.emailIsRegister()) {
                Toast.makeText(this, R.string.email_is_register, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            controller.saveNewUser()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", controller.getSavedUser().userName)
            startActivity(intent)
        }

        signIn.setOnClickListener {
            if (!controller.hasVisited) return@setOnClickListener
            if (!controller.passwordIsValid(passField.text.toString())) {
                Toast.makeText(this, R.string.pass_error, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (controller.user.userEmail.contains(emailField.text.toString())) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userName", controller.getSavedUser().userName)
                startActivity(intent)
                overridePendingTransition(R.anim.slidein, R.anim.slideout)
            } else
                Toast.makeText(this, R.string.need_register, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
        }
    }
}