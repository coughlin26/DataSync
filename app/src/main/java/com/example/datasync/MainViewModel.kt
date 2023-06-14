package com.example.datasync

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.data.BreathRepository
import com.example.model.BreathCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val breathRepo: BreathRepository) : ViewModel() {
    private var _breathChecks = mutableStateOf<List<BreathCheck>>(emptyList())
    val breathChecks: State<List<BreathCheck>>
        get() = _breathChecks

    var breathCheckFlow = flowOf(emptyList<BreathCheck>())

    fun getBreathChecks() {
        breathCheckFlow = breathRepo.getBreathChecks()
    }

    fun saveBreathCheck(check: Double) {
        breathRepo.saveBreathCheck(BreathCheck(Math.random().toInt(), check, Date()))
    }
}
