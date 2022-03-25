package dev.olaore.kotlinflows

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

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

}