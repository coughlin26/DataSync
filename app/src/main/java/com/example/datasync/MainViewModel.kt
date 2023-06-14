package com.example.datasync

import androidx.lifecycle.ViewModel
import com.example.data.BreathRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val breathRepo: BreathRepository) : ViewModel() {
}
