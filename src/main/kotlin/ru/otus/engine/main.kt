package ru.otus.engine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            simpleFunction()
        }
    }
}

suspend fun simpleFunction() {
    println("Point 1")
    delay(1000)
    println("Point 2")
}