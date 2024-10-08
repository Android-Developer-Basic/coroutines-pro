package ru.otus.engine.mock

fun simpleFunctionMock(continuation: MyContinuation<Unit>): Any {
    val cont = continuation as? SimpleFunctionMockContinuation ?: SimpleFunctionMockContinuation(continuation)
    if (0 == cont.state) {
        println("Point 1")
        println("Point 2")
        return Unit
    }
    throw IllegalStateException("Unexpected state: ${cont.state}")
}

class SimpleFunctionMockContinuation(private val completion: MyContinuation<Unit>) : MyContinuation<Unit> {
    override val context: MyContext get() = completion.context

    var state = 0
    var result: Result<Any>? = null

    override fun resumeWith(result: Result<Unit>) {
        this.result = result
        val callResult = runCatching {
            val nextCall = simpleFunctionMock(this)
            if (SUSPENDED === nextCall) {
                return
            }
        }
        completion.resumeWith(callResult)
    }
}