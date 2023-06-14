/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.model.BreathCheck
import com.example.model.DateConverters

@Database(entities = [BreathCheck::class], version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class BreathDatabase : RoomDatabase() {
    abstract fun breathDao(): BreathDao
}

