/* Copyright (C) 2023 Matt Coughlin - All Rights Reserved */

package com.example.network

import com.example.model.BreathCheck

class BreathApi {
    private val breathChecks = mutableListOf<BreathCheck>()

    fun getRemoteBreathChecks(): List<BreathCheck> {
        return breathChecks
    }

    fun addBreathCheck(check: BreathCheck) {
        val existingCheck = breathChecks.find { it.id == check.id }
        val index = breathChecks.indexOf(existingCheck)
        if (index > -1) {
            breathChecks.removeAt(index)
            breathChecks.add(index, check)
        } else {
            breathChecks.add(check)
        }
    }

    fun deleteBreathCheck(check: BreathCheck) {
        breathChecks.removeAll { it.id == check.id }
    }
}
