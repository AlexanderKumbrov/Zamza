package com.example.waves.zamza.date

import java.text.DateFormat
import java.util.*

fun formatDateAsString (dateStyle : Int, date:Date):String{
    var formatter:DateFormat = DateFormat.getDateInstance(dateStyle , Locale.getDefault())
    return formatter.format(date)
}

fun formatDateAsTimeString(dateStyle: Int, date: Date): String {
    val formatter = DateFormat.getTimeInstance(dateStyle, Locale.getDefault())
    return formatter.format(date)
}