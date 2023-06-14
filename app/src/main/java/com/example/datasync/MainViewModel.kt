package com.example.datasync

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BreathRepository
import com.example.model.BreathCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val breathRepo: BreathRepository) : ViewModel() {
    val breathCheckFlow = MutableStateFlow<List<BreathCheck>>(emptyList())

    fun getBreathChecks() {
        breathCheckFlow.value = breathRepo.getBreathChecks().sortedBy { it.timestamp }.reversed()
    }

    fun saveBreathCheck(check: Double) {
        breathRepo.saveBreathCheck(BreathCheck(check, Date()))
    }
}
