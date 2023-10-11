package org.sopt.dosopttemplate.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun View.showShortSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun <T> Intent.getParcelableData(name: String, className: Class<T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) this.getParcelableExtra(name, className)
    else this.getParcelableExtra(name)


fun Activity.hideKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).let { imm ->
        if (imm.isAcceptingText) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}