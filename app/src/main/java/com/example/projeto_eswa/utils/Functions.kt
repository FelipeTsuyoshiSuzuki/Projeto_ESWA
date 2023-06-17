package com.example.projeto_eswa.utils

fun Long.toTime(): String {

    fun formatLong(number: Long): String {
        return if (number.toString().length == 1) {
            "0$number"
        } else {
            "$number"
        }
    }

    var timeResult = this
    val hours = this / 3600000
    timeResult -= (hours * 3600000)
    val minutes = timeResult / 60000
    timeResult -= (minutes * 60000)
    val seconds = timeResult / 1000
    return "${formatLong(hours)}:${formatLong(minutes)}:${formatLong(seconds)}"
}