/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data

import com.example.network.BreathApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class BreathRepository @Inject constructor(
    private val breathDao: BreathDao,
    private val api: BreathApi
) {
    fun getBreathChecks(): Flow<List<com.example.model.BreathCheck>> {
        return flow<List<com.example.model.BreathCheck>> {
            breathDao.insertAll(*api.getRemoteBreathChecks().toTypedArray())
            breathDao.getAll()
        }.flowOn(Dispatchers.IO)
    }

    fun getBreathCheckById(id: Int): Flow<com.example.model.BreathCheck> {
        return flow<com.example.model.BreathCheck> {
            breathDao.insertAll(*api.getRemoteBreathChecks().toTypedArray())
            breathDao.getById(id)
        }.flowOn(Dispatchers.IO)
    }

    fun saveBreathCheck(check: com.example.model.BreathCheck) {
        CoroutineScope(Dispatchers.IO).launch {
            breathDao.insert(check)
            api.addBreathCheck(check)
        }
    }
}
