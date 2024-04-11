package ru.otus.homework

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default + job)
    scope.launch {
        delay(300L)
        throw Exception("launch1: error!")
    }
    scope.launch {
        delay(1000L)
        println("launch2: done!")
    }
    println("blocking waits for job to finish!")
    job.join()
}