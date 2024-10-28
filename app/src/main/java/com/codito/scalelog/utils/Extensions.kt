package com.codito.scalelog.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Extension function to format a timestamp (Long) into a readable date string
fun Long.formatToDateString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return sdf.format(Date(this))
}
