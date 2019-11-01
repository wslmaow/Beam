package com.december.kotlinmodule.other

import com.december.kotlinmodule.*

fun test(){
    isEmpty()
    "".replaceLast()
    SigleInstance.allErnployees.add(Person())
    SigleInstance.allErnployees.maxBy { it.age }
}