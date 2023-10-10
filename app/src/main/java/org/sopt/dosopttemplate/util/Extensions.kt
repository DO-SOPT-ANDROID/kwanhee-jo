package org.sopt.dosopttemplate.util

import android.content.Intent
import android.os.Build
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showShortSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun <T> Intent.getParcelableData(name: String, className: Class<T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) this.getParcelableExtra(name, className)
    else this.getParcelableExtra(name)
