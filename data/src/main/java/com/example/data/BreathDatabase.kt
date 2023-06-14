/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [com.example.model.BreathCheck::class], version = 1, exportSchema = false)
@TypeConverters(com.example.model.DateConverters::class)
abstract class BreathDatabase : RoomDatabase() {
    abstract fun breathDao(): BreathDao
}

