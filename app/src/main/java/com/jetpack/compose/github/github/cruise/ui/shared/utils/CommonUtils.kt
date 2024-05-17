package com.jetpack.compose.github.github.cruise.ui.shared.utils

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * Created by Dinakar Maurya on 2024/05/16.
 */
object CommonUtils {
    fun encodeUrl(url: String): String {
        return URLEncoder.encode(url, "UTF-8")
    }

    fun decodeUrl(encodedUrl: String): String {
        return URLDecoder.decode(encodedUrl, "UTF-8")
    }
}