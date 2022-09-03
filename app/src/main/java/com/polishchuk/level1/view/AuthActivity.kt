package com.polishchuk.level1.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.polishchuk.level1.R
import com.polishchuk.level1.controller.AppController
import com.polishchuk.level1.util.ToastInformation

class AuthActivity : AppCompatActivity() {

    private lateinit var controller: AppController
    private lateinit var btnReg: Button
    private lateinit var emailField : EditText
    private lateinit var passField: EditText
    private lateinit var btnSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        btnReg = findViewById(R.id.btnReg)
        emailField = findViewById(R.id.editTextEmailAddress)
        passField = findViewById(R.id.etPassword)
        btnSignIn = findViewById(R.id.tvSignIn)
        controller = AppController(this)
        if (controller.hasVisited) {
            emailField.text = Editable.Factory.getInstance()
                .newEditable(controller.user.userEmail)
        }
    }

    private fun launchActivity(authActivity: AuthActivity, controller: AppController) {
        startActivity(
            Intent(authActivity, MainActivity::class.java)
                .putExtra("userName", controller.getSavedUser().userName)
        )
        overridePendingTransition(R.anim.slidein, R.anim.slideout)
    }

    fun onClickBtnReg(view: View) {
        view.setOnClickListener {
            controller.emailUser = emailField.text.toString()
            controller.passUser = passField.text.toString()
            if (!controller.emailIsValid()) {
                ToastInformation.showToast(this, R.string.error_user_name)
                return@setOnClickListener
            }
            if (controller.passIsEmpty()) {
                ToastInformation.showToast(this, R.string.pass_empty)
                return@setOnClickListener
            }
            if (controller.emailIsRegister()) {
                ToastInformation.showToast(this, R.string.email_is_register)
                return@setOnClickListener
            }
            controller.saveNewUser()
            launchActivity(this, controller)
        }
    }

    fun onClickSignIn(view: View) {
        view.setOnClickListener {
            if (!controller.hasVisited) return@setOnClickListener
            if (!controller.passwordIsValid(passField.text.toString())) {
                ToastInformation.showToast(this, R.string.pass_error)
                return@setOnClickListener
            }
            if (controller.user.userEmail.contains(emailField.text.toString())) {
                launchActivity(this, controller)
            } else {
                ToastInformation.showToast(this, R.string.need_register)
                return@setOnClickListener
            }
        }
    }
}