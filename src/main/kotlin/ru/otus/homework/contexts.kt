package ru.otus.homework

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = CoroutineScope(Dispatchers.Default).launch {
        delay(500L)
        println("world!")
    }
    println("Hello")
    job.join()
}