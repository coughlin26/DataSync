/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideBreathApi(): BreathApi = BreathApi()
}
