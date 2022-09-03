package com.polishchuk.level1.util

import android.content.Context
import android.widget.Toast

object ToastInformation {

    fun showToast(context: Context, msg: Int) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}