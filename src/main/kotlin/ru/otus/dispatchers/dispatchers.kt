package ru.otus.dispatchers

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runBlocking {
        launch {
            println(printThreadName("launch"))
        }
        launch(Dispatchers.Default) {
            println(printThreadName("launch(Dispatchers.Default)"))
        }
        launch(Dispatchers.IO) {
            println(printThreadName("launch(Dispatchers.IO)"))
        }
        launch(newSingleThreadContext("MyOwnThread")) {
            println(printThreadName("launch(Thread)"))
        }
    }
}

fun printThreadName(prefix: String) {
    println("$prefix - Thread: ${Thread.currentThread().name}")
}
