/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "breath_checks")
data class BreathCheck(
    @ColumnInfo val value: Double,
    @ColumnInfo val timestamp: Date,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
)

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

