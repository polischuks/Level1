package com.polishchuk.level1.data

import android.content.SharedPreferences
import com.polishchuk.level1.model.User

interface ISettings {

    fun loadSettings(): SharedPreferences
    fun checkSetting(): Boolean
    fun saveSettings(newUser: User)
    fun getUser(): User
}