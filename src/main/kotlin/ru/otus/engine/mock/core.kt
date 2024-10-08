package ru.otus.engine.mock

import java.util.LinkedList

const val SUSPENDED = "SUSPENDED"

class MyDispatcher(private val name: String) {
    fun schedule(runnable: Runnable) {
        println("Dispatching on $name")
        runnable.run()
    }
}

class MyContext(val dispatcher: MyDispatcher)

interface MyContinuation<in T> {
    val context: MyContext
    fun resumeWith(result: Result<T>)
}

private class FinalContinuation(override val context: MyContext): MyContinuation<Any> {
    override fun resumeWith(result: Result<Any>) {
        println("Complete: $result")
    }
}

fun MyContext.launch(block: (MyContinuation<Any>) -> Runnable) {
    dispatcher.schedule(block(FinalContinuation(this)))
}