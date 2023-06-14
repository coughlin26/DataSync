/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.BreathDao
import com.example.data.BreathDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideBreathDao(db: BreathDatabase): BreathDao = db.breathDao()

    @Singleton
    @Provides
    fun provideBreathDatabase(@ApplicationContext applicationContext: Context): BreathDatabase {
        return Room.databaseBuilder(
            applicationContext,
            BreathDatabase::class.java,
            "BreathDatabase"
        ).build()
    }
}
