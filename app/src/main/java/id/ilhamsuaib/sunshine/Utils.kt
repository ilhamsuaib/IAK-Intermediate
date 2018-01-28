package id.ilhamsuaib.sunshine

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ilham on 1/28/18.
 */

fun convertDateToWeekDay(date: String?): String? {
    /*konversi tanggal bertipe string menjadi tgl bertipe Date*/
    val oldSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val oldDate: Date = oldSdf.parse(date)

    /*konversi tgl Date ke nama hari*/
    val daySdf = SimpleDateFormat("EEEE", Locale("id"))
    val day = daySdf.format(oldDate)

    println(day)
    return day
}

fun getIcon(weatherId: Int): Int {
    return when (weatherId) {
        in 300..321, 801 -> R.drawable.ic_light_rain
        in 500..504, in 520..531 -> R.drawable.ic_rain
        511, in 600..622 -> R.drawable.ic_snow
        in 701..760 -> R.drawable.ic_fog
        800, in 951..957 -> R.drawable.ic_clear
        in 802..804 -> R.drawable.ic_cloudy
        in 900..906, in 958..962, 761, 771, 781, in 200..232 -> R.drawable.ic_storm
        else -> R.drawable.ic_storm
    }
}