package com.example.fitfitness.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class Routine(
    @PrimaryKey(autoGenerate = true) val routineId: Long = 0,
    val routineName: String
){
    constructor(name: String) : this(0, name)
}