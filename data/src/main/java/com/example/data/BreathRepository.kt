/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.data

import com.example.model.BreathCheck
import com.example.network.BreathApi
import javax.inject.Inject

class BreathRepository @Inject constructor(
    private val breathDao: BreathDao,
    private val api: BreathApi
) {
    fun getBreathChecks(): List<BreathCheck> {
        // TODO: Implement a real SyncAdapter.
        // Ideally the sync adapter would get all the remote breath checks and sync them
        // before anything attempts to get the data for the UI.
        breathDao.insertAll(*api.getRemoteBreathChecks().toTypedArray())
        return breathDao.getAll()
    }

    fun getBreathCheckById(id: Int): BreathCheck {
        // This should also use a sync adapter instead.
        breathDao.insertAll(*api.getRemoteBreathChecks().toTypedArray())
        return breathDao.getById(id)
    }

    fun saveBreathCheck(check: BreathCheck) {
        val rowId = breathDao.insert(check)
        api.addBreathCheck(breathDao.getByRowId(rowId))
    }

    fun deleteBreathChecks() {
        for (check in breathDao.getAll()) {
            breathDao.delete(check)
            api.deleteBreathCheck(check)
        }
    }
}
