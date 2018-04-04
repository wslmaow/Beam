package com.feb.demo.utils

/**
 * Created by Administrator on 2018/3/30.
 */

fun getResult(a:Any,b:Int) :Any= if (a is String)a.length else b
val a:Int =1
var c:String="sdsd"
val d="$c:$a"
//var e : Array<Int>
fun cases(a: Any){
    when(a){
        1  -> a
        "ads" -> a
        4  -> if (a is Int && a in 1..5)a
        else -> a
    }

}

