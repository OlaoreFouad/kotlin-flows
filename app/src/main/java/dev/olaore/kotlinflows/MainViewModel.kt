package dev.olaore.kotlinflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var countdown = flow {

        val startingValue = 5
        var currentValue = startingValue

        emit(currentValue)
        while (currentValue > 0) {
            delay(200L)
            currentValue--
            emit(currentValue)
        }

    }

    init {
        collectFlow()
        collectFlattenedFlow()
        collectBufferedFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            val count = countdown.filter {
                it % 2 == 0
            }.map {
                it * it
            }.onEach {
                println(it)
            }.count {
                it  % 5 == 0
            }
            println("the count result is: $count")

            val reduceResult = countdown.reduce { accumulator, value ->
                accumulator + value
            }
            println("the reduced result is: $reduceResult")

            val foldResult = countdown.fold(200) { acc, value ->
                acc + value
            }
            println("the folded result is: $foldResult")

        }
    }

    private fun collectFlattenedFlow() {
        val flow = (1..5).asFlow()
        viewModelScope.launch {
            flow.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value + 2)
                }
            }.collect { value ->
                println("The flattened value is: $value")
            }
        }
    }

    private fun collectBufferedFlow() {
        val flow = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main dish")
            delay(100L)
            emit("Dessert")
        }

        viewModelScope.launch {
            flow.onEach {
                println("FLOWWW: $it is delivered")
            }.buffer().collect {
                println("FLOWWW: Now eating $it")
                delay(1500L)
                println("FLOWWW: Finished eating $it")
            }
        }

    }

}

// TODO: Operators: Transformation, Filtering, Flattening, Terminal, Buffering, Conflating.