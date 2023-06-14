/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.datasync.di

import com.example.data.BreathDao
import com.example.data.BreathRepository
import com.example.network.BreathApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class AppModule {
    @Provides
    fun provideBreathRepository(dao: BreathDao, api: BreathApi): BreathRepository =
        BreathRepository(dao, api)
}
