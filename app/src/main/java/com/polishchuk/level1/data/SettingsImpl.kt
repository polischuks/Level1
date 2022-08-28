package com.polishchuk.level1.data

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.util.Log
import com.polishchuk.level1.model.User
import com.polishchuk.level1.view.AuthActivity

const val APP_PREFERENCES = "settings"
const val APP_PREFERENCES_EMAIL_PASSWORD = "UserData"

class SettingsImpl(var authActivity: AuthActivity) : ISettings {

    override fun loadSettings(): SharedPreferences {
        return authActivity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    override fun checkSetting(): Boolean {
        return authActivity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            .getBoolean("hasVisited", false)
    }

    override fun saveSettings(newUser: User) {
        val editor = authActivity.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            .edit()
        editor.putString(
            APP_PREFERENCES_EMAIL_PASSWORD, newUser.userEmail
                .plus("&")
                .plus(newUser.userPassword)
        )
        editor.putBoolean("hasVisited", true)
        editor.apply()
        Log.i("Level1", "Saved settings")
        Log.i(
            "Level1", "user data ".plus(
                Editable.Factory.getInstance()
                    .newEditable(
                        authActivity
                            .getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                            .getString(APP_PREFERENCES_EMAIL_PASSWORD, "")
                    )
            )
        )
    }

    override fun getUser(): User {
        val set = authActivity
            .getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            .getString(APP_PREFERENCES_EMAIL_PASSWORD, "").toString()
        var userName = set.split("&")[0].split("@")[0]
        userName = userName.split("@")[0]
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
        return User(set.split("&")[0], set.split("&")[1], userName)
    }
}