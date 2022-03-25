package dev.olaore.kotlinflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var countdown = flow {

        val startingValue = 10
        var currentValue = startingValue

        emit(currentValue)
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue)
        }

    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            countdown.collect {
                println(it)
            }
        }
    }

}