package com.intermedia.challenge.utils.extensions

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun dateToSimpleString(date: String?): String {
    date?.let {
        val parsedDate = LocalDate.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
        return DateTimeFormatter.ofPattern("d 'de' MMMM yyyy", Locale("es")).format(parsedDate)
    }
    return "Sin infomracion disponible"
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


