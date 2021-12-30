package com.example.composeclock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class ComposeClockViewModel : ViewModel() {
    private val _clockState = MutableStateFlow(Triple(0f, 0f, 0f))
    val clockState: StateFlow<Triple<Float, Float, Float>> = _clockState

    init {
        initTimer()
    }

    private fun initTimer() {
        viewModelScope.launch {
            while (true) {
                val cal = Calendar.getInstance()
                val hours = cal.get(Calendar.HOUR)
                val minute = cal.get(Calendar.MINUTE)
                val second = cal.get(Calendar.SECOND)
                val secondAngel = (second * 6f).minus(90).plus(360).rem(360)
                val minuteAngel = (minute * 6f).plus(second / 10f).minus(90).plus(360).rem(360)
                val hoursAngel = (hours * 30f).plus(minute / 2f).minus(90).plus(360).rem(360)
                _clockState.emit(Triple(hoursAngel, minuteAngel, secondAngel))
                delay(1000L)
            }
        }
    }
}