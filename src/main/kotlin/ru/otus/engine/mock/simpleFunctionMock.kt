package ru.otus.engine.mock

fun simpleFunctionMock(continuation: MyContinuation<Any>): Any {
    val cont = continuation as? SimpleFunctionMockContinuation ?: SimpleFunctionMockContinuation(continuation)

    if (0 == cont.state) {
        println("Point 1")
        cont.state = 1
        val res = getRandomMock(cont)
        if (SUSPENDED == res) {
            return SUSPENDED
        }
        cont.result = Result.success(res)
    }
    if (1 == cont.state) {
        val random = checkNotNull(cont.result).getOrThrow() as Int
        cont.state = 2
        cont.random = random
        val res = multiplyByFiveMock(random, cont)
        if (SUSPENDED == res) {
            return SUSPENDED
        }
        cont.result = Result.success(res)
    }
    if (2 == cont.state) {
        val random = checkNotNull(cont.random)
        val multiplied = checkNotNull(cont.result).getOrThrow() as Int
        println("Point 2: $random multiplied by five equals $multiplied")
        return Unit
    }
    throw IllegalStateException("Unexpected state: ${cont.state}")
}

class SimpleFunctionMockContinuation(private val completion: MyContinuation<Any>) : MyContinuation<Any> {
    override val context: MyContext get() = completion.context

    var state = 0
    var result: Result<Any>? = null
    var random: Int? = null

    override fun resumeWith(result: Result<Any>) {
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