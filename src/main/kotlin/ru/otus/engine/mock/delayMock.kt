package ru.otus.engine.mock

import java.util.concurrent.TimeUnit

fun delayFunctionMock(time: Long, continuation: MyContinuation<Unit>): Any {
    println("Dalaying...")
    continuation.context.dispatcher.schedule {
        Thread.sleep(time)
        continuation.resumeWith(Result.success(Unit))
    }
    return SUSPENDED
}