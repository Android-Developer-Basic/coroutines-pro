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
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runBlocking {
        println(printThreadName("launch"))
        withContext(Dispatchers.Default) {
            println(printThreadName("withContext(Dispatchers.Default 1)"))
            delay(1000L)
            println(printThreadName("withContext(Dispatchers.Default 2)"))
        }
        println(printThreadName("after withContext(Dispatchers.Default)"))
    }
}

fun printThreadName(prefix: String) {
    println("$prefix - Thread: ${Thread.currentThread().name}")
}
