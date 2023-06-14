package com.example.datasync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BreathRepository
import com.example.model.BreathCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val breathRepo: BreathRepository) : ViewModel() {
    val breathCheckFlow = MutableStateFlow<List<BreathCheck>>(emptyList())

    fun getBreathChecks() {
        viewModelScope.launch(Dispatchers.IO) {
            val checks = breathRepo.getBreathChecks().sortedBy { it.timestamp }.reversed()
            launch(Dispatchers.Main) { breathCheckFlow.value = checks }
        }
    }

    fun saveBreathCheck(check: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            breathRepo.saveBreathCheck(
                BreathCheck(
                    check,
                    Date()
                )
            )
        }
    }

    fun deleteBreathChecks() {
        viewModelScope.launch(Dispatchers.IO) { breathRepo.deleteBreathChecks() }
    }
}
