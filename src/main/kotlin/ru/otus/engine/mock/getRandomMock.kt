package ru.otus.engine.mock

import kotlin.random.Random

fun getRandomMock(continuation: MyContinuation<Any>): Any {
    val cont = continuation as? GetRandomMockContinuation ?: GetRandomMockContinuation(continuation)
    if (0 == cont.state) {
        println("Getting random...")
        cont.state = 1
        if (SUSPENDED == delayFunctionMock(1000, cont)) {
            return SUSPENDED
        }
    }
    if (1 == cont.state) {
        return Random.nextInt(1, 10)
    }
    throw IllegalStateException("Unexpected state: ${cont.state}")
}

class GetRandomMockContinuation(private val completion: MyContinuation<Any>) : MyContinuation<Any> {
    override val context: MyContext get() = completion.context

    var state = 0
    var result: Result<Any>? = null

    override fun resumeWith(result: Result<Any>) {
        this.result = result
        val callResult = runCatching {
            val nextCall = getRandomMock(this)
            if (SUSPENDED === nextCall) {
                return
            }
            return@runCatching nextCall
        }
        completion.resumeWith(callResult)
    }
}