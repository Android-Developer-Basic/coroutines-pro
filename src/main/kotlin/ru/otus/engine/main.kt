package ru.otus.engine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    runBlocking {
        launch {
            simpleFunction()
        }
    }
}

suspend fun simpleFunction() {
    println("Point 1")
    val random = getRandom()
    val multiplied = multiplyByFive(random)
    println("Point 2: $random multiplied by five equals $multiplied")
}

suspend fun getRandom(): Int {
    println("Getting random...")
    delay(1000)
    return Random.nextInt(1, 10)
}

suspend fun multiplyByFive(value: Int): Int {
    println("Multiplying...")
    delay(1000)
    return value * 5
}