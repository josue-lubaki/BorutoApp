package ca.josuelubaki.borutoapp.data.local

import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
        val sb = StringBuilder()
        for (s in list) {
            sb.append(s).append(separator)
        }
        sb.setLength(sb.length - separator.length)
        return sb.toString()
    }

    @TypeConverter
    fun convertStringToList(value: String): List<String> {
        return value.split(separator)
    }

}