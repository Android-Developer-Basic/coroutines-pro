package ru.otus.engine.mock

import kotlin.random.Random

fun multiplyByFiveMock(value: Int, continuation: MyContinuation<Any>): Any {
    val cont = continuation as? MultiplyByFiveContinuation ?: MultiplyByFiveContinuation(value, continuation)
    if (0 == cont.state) {
        println("Multiplying...")
        cont.state = 1
        if (SUSPENDED == delayFunctionMock(1000, cont)) {
            return SUSPENDED
        }
    }
    if (1 == cont.state) {
        return checkNotNull(cont.value) * 5
    }
    throw IllegalStateException("Unexpected state: ${cont.state}")
}

class MultiplyByFiveContinuation(val value: Int, val completion: MyContinuation<Any>) : MyContinuation<Any> {
    override val context: MyContext get() = completion.context

    var state = 0
    var result: Result<Any>? = null

    override fun resumeWith(result: Result<Any>) {
        this.result = result
        val callResult = runCatching {
            val nextCall = multiplyByFiveMock(value,this)
            if (SUSPENDED === nextCall) {
                return
            }
            return@runCatching nextCall
        }
        completion.resumeWith(callResult)
    }
}