package com.example.fitfitness.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fitfitness.util.toSimpleString
import java.io.Serializable
import java.time.OffsetDateTime
import java.util.*

@Entity(tableName = "attempts")
data class Attempt(
    @PrimaryKey(autoGenerate = true)
    val attemptId: Long = 0,
    val date: OffsetDateTime? = null,
    val successful: Boolean,
    val weight: Float,
    val exerciseId: Long) : Serializable {

    fun getDateString(): String{
        if(date != null){
            var day: Int = date.dayOfMonth
            var month: Int = date.monthValue;
            var year: Int = date.year;
            var dateString: String = String.format("$month/$day/$year")
            return dateString
        }
        return ""
    }
}