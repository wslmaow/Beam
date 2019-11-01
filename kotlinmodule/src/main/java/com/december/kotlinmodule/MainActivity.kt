package com.december.kotlinmodule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val text:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //vars(2,3)
        function(Array(3) { i -> "index:$i" })
        helloworld.setOnClickListener {
            Toast.makeText(this,helloworld.text,Toast.LENGTH_SHORT).show()
        }
    }


    fun function1(a: Int, b: Int): Int {
        return (a + b)
    }

    fun function2(a: Int, b: Int): Int = (a + b)

    fun function3(a: Int, b: Int): Unit {
        /*  条件表达式*/
        val c = if (a > b) a else b

        when (a) {
            1 -> print("a == 1")
            2 -> print("a == 2")
            in 1..9 -> print("a is aaaaa")
            //a>8 -> print("")
            is Int -> print("")
            else -> { // 注意这个块
                print("a 不是 1 ，也不是 2")
            }

        }

        return print(a + b)
    }

    /*  循环表达式   */
    fun main(args: Array<String>) {
        val items = listOf("apple", "banana", "kiwi")
        for (a in items) {
            println(a)
        }

        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }
    }

    /*  基本语法    */
    fun vars(vararg v: Int) {
        for (vt in v) {
            print(vt)
        }

        /* 变量var 常量val*/
        val a: Int = 1
        val b = 1       // 系统自动推断变量类型为Int
        val c: Int      // 如果不在声明时初始化则必须提供变量类型
        c = 1           // 明确赋值


        var x = 5        // 系统自动推断变量类型为Int
        x += 1           // 变量可修改

        /*//空安全设计*/
        //类型后面加?表示可为空
        var age: String? = "2"
        //抛出空指针异常
        val ages = age!!.toInt()
        //不做处理返回 null
        val ages1 = age?.toInt()
        //age为空返回-1
        val ages2 = age?.toInt() ?: -1
        if (age is String) {
            // 做过类型判断以后，obj会被系统自动转换为String类型
            print(age.length)
        }

        /*区间*/
        for (i in 1..4) print(i) // 输出“1234”

        for (i in 4..1) print(i) // 什么都不输出
        val i = 0
        if (i in 1..10) { // 等同于 1 <= i && i <= 10
            println(i)
        }

        // 使用 step 指定步长
        for (i in 1..4 step 2) print(i) // 输出“13”

        for (i in 4 downTo 1 step 2) print(i) // 输出“42”


        // 使用 until 函数排除结束元素
        for (i in 1 until 10) {   // i in [1, 10) 排除了 10
            println(i)
        }
    }

    class Person {

        var lastName: String = "zhang"
            get() = field.toUpperCase()   // 将变量赋值后转换为大写
            set

        var no: Int = 100
            get() = field                // 后端变量
            set(value) {
                if (value < 10) {       // 如果传入的值小于 10 返回该值
                    field = value
                } else {
                    field = -1         // 如果传入的值大于等于 10 返回 -1
                }
            }

        var heiht: Float = 145.4f
            set(d) {
                field = d + 1
            }
    }

    fun function(args: Array<String>) {
        var person: Person = Person()

        person.lastName = "wang"


        println("lastName:${person.lastName}")

        person.no = 9
        println("no:${person.no}")

        person.no = 20
        println("no:${person.no}")

        person.heiht = 19f
        println("heiht:${person.heiht}")

    }


}
