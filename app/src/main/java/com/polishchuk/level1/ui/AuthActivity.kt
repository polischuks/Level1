package com.polishchuk.level1.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.polishchuk.level1.R

const val APP_PREFERENCES = "settings"
const val APP_PREFERENCES_EMAIL_PASSWORD = "UserData"

class AuthActivity : AppCompatActivity() {

    private lateinit var btnReg : Button
    private lateinit var emailField : EditText
    private lateinit var passField : EditText
    private lateinit var signIn : TextView

    private lateinit var userSettings : SharedPreferences
    private lateinit var userData: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        var userEmail = ""
        var userPass = ""

        userSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        Log.i("Level1", "Read user data ".plus(Editable.Factory.getInstance()
            .newEditable(userSettings.getString(APP_PREFERENCES_EMAIL_PASSWORD, ""))))

        val hasVisited = userSettings.getBoolean("hasVisited", false)
        Log.i("Level1", "hasVisited ".plus(Editable.Factory.getInstance()
            .newEditable(userSettings.getBoolean("hasVisited", false).toString())))

        btnReg = findViewById(R.id.btnReg)
        emailField = findViewById(R.id.editTextEmailAddress)
        passField = findViewById(R.id.editTextTextPassword)
        signIn = findViewById(R.id.textViewSignIn)

        if (hasVisited) {
            userData = Editable.Factory.getInstance()
                .newEditable(userSettings.getString(APP_PREFERENCES_EMAIL_PASSWORD, ""))
                .split("&")
            emailField.text = Editable.Factory.getInstance().newEditable(userData[0])
            userEmail = userData[0]
            userPass = userData[1]
        }

        btnReg.setOnClickListener {
            if (!emailField.text.toString().contains("@")) {
                Toast.makeText(this, R.string.error_user_name, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (passField.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.pass_empty, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (userEmail == emailField.text.toString()) {
                Toast.makeText(this, R.string.email_is_register, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            saveUserSettings(emailField.text.toString(), passField.text.toString())
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userName", emailField.text.toString())
            startActivity(intent)
        }

        signIn.setOnClickListener {
            var isPasswordValid = true
            Log.i("Level1", "sign in userEmail ".plus(userEmail))

            if (userPass != passField.text.toString()){
                isPasswordValid = false
                Toast.makeText(this, "Error password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (hasVisited && userData.contains(emailField.text.toString()) && isPasswordValid) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userName", userEmail)
                startActivity(intent)
            } else
                Toast.makeText(this, R.string.need_register, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
        }
    }

    private fun saveUserSettings(userEmail: String, userPass: String) {
        val editor = this.userSettings.edit()
        val userData = userEmail.plus("&").plus(userPass)
        editor.putString(APP_PREFERENCES_EMAIL_PASSWORD, userData)
        editor.putBoolean("hasVisited", true)
        editor.apply()
        Log.i("Level1", "Saved settings")
        Log.i("Level1", "user data ".plus(Editable.Factory.getInstance()
            .newEditable(userSettings.getString(APP_PREFERENCES_EMAIL_PASSWORD, ""))))
    }
}