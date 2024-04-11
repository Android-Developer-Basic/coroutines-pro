package ru.otus.homework

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(500L)
        println("world!")
    }
    println("Hello")
}