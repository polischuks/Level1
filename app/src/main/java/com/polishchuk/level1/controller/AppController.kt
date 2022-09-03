package com.polishchuk.level1.controller

import android.util.Log
import com.polishchuk.level1.data.SettingsImpl
import com.polishchuk.level1.model.User
import com.polishchuk.level1.view.AuthActivity

class AppController(authActivity: AuthActivity) {
    lateinit var user: User
    var hasVisited = false
    private var appSettings: SettingsImpl
    private val authActivity: AuthActivity
    lateinit var emailUser: String
    lateinit var passUser: String

    init {
        appSettings = SettingsImpl(authActivity)
        this.authActivity = authActivity
        hasVisited = appSettings.checkSetting()
        if (hasVisited) user = appSettings.getUser()
    }

    fun saveNewUser() {
        appSettings.saveSettings(User(emailUser, passUser, getUserName(emailUser)))
    }

    fun emailIsValid(): Boolean {
        Log.i("CONTROLLER", authActivity.toString())
        return emailUser.contains("@")
    }

    fun emailIsRegister(): Boolean {
        return if (hasVisited)
            user.userEmail == emailUser
        else false
    }

    fun passIsEmpty() = passUser.isEmpty()

    fun passwordIsValid(passField: String): Boolean {
        return user.userPassword == passField
    }

    private fun getUserName(emailUser: String): String {
        var userName = emailUser.split("@")[0]
        if (userName.contains(".")) {
            val parsStr = userName.split(".")
            val firstName =
                parsStr[0][0].uppercaseChar().plus(
                    parsStr[0].substring(1, parsStr[0].length)
                )
            val lastName =
                parsStr[1][0].uppercaseChar().plus(
                    parsStr[1].substring(1, parsStr[1].length)
                )
            userName = firstName.plus(" ").plus(lastName)
        }
        Log.i("Level1", "user name ".plus(userName))
        return userName
    }

    fun getSavedUser(): User {
        return appSettings.getUser()
    }
}