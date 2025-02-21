package com.gimnastiar.newsapple.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object Helper {

    fun formatDate(inputDate: String): String {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Pastikan membaca sebagai UTC

        val outputFormat = SimpleDateFormat("EEE, d MMM HH.mm", Locale("id", "ID"))

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date ?: Date())
    }

    fun getRandomDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1) // Kemarin
        val yesterday = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_YEAR, -6)
        val lastWeek = calendar.timeInMillis

        val randomTime = (lastWeek..yesterday).random()

        return dateFormat.format(Date(randomTime))
    }

}