package org.sopt.dosopttemplate.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import okhttp3.ResponseBody
import org.sopt.dosopttemplate.db.remote.RetrofitManager
import org.sopt.dosopttemplate.model.dto.RespResult

fun View.showShortSnackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_SHORT).show()
}

fun Context.showShortToastMessage(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun <T> Intent.getParcelableData(name: String, className: Class<T>): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) this.getParcelableExtra(name, className)
    else this.getParcelableExtra(name)


fun Activity.hideKeyboard(view: View) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view.windowToken, 0)
}

fun ImageView.roundedCornerGlide(view: View, loadImage: Int, size: Int, radius: Int) {
    Glide.with(view)
        .load(loadImage)
        .override(size)
        .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius)))
        .into(this)
}

fun ResponseBody.toErrorResult(): RespResult? =
    RetrofitManager.retrofit.responseBodyConverter<RespResult>(
        RespResult::class.java,
        RespResult::class.java.annotations
    ).convert(this)