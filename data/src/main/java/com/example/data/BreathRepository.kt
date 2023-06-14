/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data

import com.example.model.BreathCheck
import com.example.network.BreathApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BreathRepository @Inject constructor(
    private val breathDao: BreathDao,
    private val api: BreathApi
) {
    fun getBreathChecks(): List<BreathCheck> {
        return runBlocking {
            breathDao.insertAll(*api.getRemoteBreathChecks().toTypedArray())
            breathDao.getAll()
        }
    }

    fun getBreathCheckById(id: Int): BreathCheck {
        return runBlocking {
            breathDao.insertAll(*api.getRemoteBreathChecks().toTypedArray())
            breathDao.getById(id)
        }
    }

    fun saveBreathCheck(check: BreathCheck) {
        CoroutineScope(Dispatchers.IO).launch {
            breathDao.insert(check)
            api.addBreathCheck(check)
        }
    }
}
