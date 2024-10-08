package ru.otus.engine.mock

fun main() {
    MyContext(MyDispatcher("main")).launch {
        Runnable { simpleFunctionMock(it) }
    }
}



