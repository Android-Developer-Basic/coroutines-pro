package ru.otus.errors

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        try {
            coroutineScope {
                val res = async {
                    badFun()
                }
                res.await()
            }
        } catch (e: Exception) {
            println("Caught $e")
        }
    }
}

suspend fun badFun() {
    delay(100L)
    throw RuntimeException("Error")
}
