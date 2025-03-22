package com.amaurypm.ifilesdm

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager

fun Activity.toast(text: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(
        this,
        text,
        length
    ).show()
}

fun Activity.sbMessage(
    view: View,
    text: String,
    textColor: Int = R.color.white,
    bgColor: Int = R.color.snackbar_red,
    length: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(view, text, length)
        .setTextColor(getColor(textColor))
        .setBackgroundTint(getColor(bgColor))
        .show()
}

fun View.hideKeyboard() {
    val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}
