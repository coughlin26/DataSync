/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.model.BreathCheck

@Dao
interface BreathDao {
    @Query("SELECT * FROM breath_checks")
    fun getAll(): List<BreathCheck>

    @Query("SELECT * FROM breath_checks WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<BreathCheck>

    @Query("SELECT * FROM breath_checks WHERE id IS :id")
    fun getById(id: Int): BreathCheck

    @Query("SELECT * FROM breath_checks WHERE rowid IS :rowId")
    fun getByRowId(rowId: Long): BreathCheck

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(check: BreathCheck): Long

    @Update
    fun update(check: BreathCheck)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg checks: BreathCheck): List<Long>

    @Delete
    fun delete(check: BreathCheck)
}
