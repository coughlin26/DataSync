/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.model.BreathCheck

@Dao
interface BreathDao {
    @Query("SELECT * FROM breath_checks")
    suspend fun getAll(): List<BreathCheck>

    @Query("SELECT * FROM breath_checks WHERE id IN (:ids)")
    suspend fun loadAllByIds(ids: IntArray): List<BreathCheck>

    @Query("SELECT * FROM breath_checks WHERE id IS :id")
    suspend fun getById(id: Int): BreathCheck

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(check: BreathCheck)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg checks: BreathCheck)

    @Delete
    suspend fun delete(check: BreathCheck)
}
