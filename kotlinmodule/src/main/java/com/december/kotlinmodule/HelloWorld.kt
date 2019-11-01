package com.december.kotlinmodule

fun main(args:Array<String>){
    val arg=if (args.isNotEmpty())args[0] else "ll"
    println("Hello,$arg".lastChar())
    val boy = Person("tom",9)
    println(boy.Adult)
}

class Person(val name : String="tom",val age : Int=18) : Clickable{
    val Adult:Boolean get() = age>=18
    override fun showOff() = { println("i need clicklistener") }

}

interface Clickable{
    fun showOff() = {
        val i = true
        println("i am cliackable")
    }
}
