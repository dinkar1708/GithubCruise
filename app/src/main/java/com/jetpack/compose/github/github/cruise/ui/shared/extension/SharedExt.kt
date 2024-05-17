package com.jetpack.compose.github.github.cruise.ui.shared.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import timber.log.Timber

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
// TODO keep for future
fun Context.openUrlInBrowser(stringUrl: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(stringUrl))
        startActivity(intent)
    } catch (e: Exception) {
        Timber.e(e)
    }
}
