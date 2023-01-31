package kotlin2.chapter5

import kotlin1.measureTimeMillis
import kotlinx.coroutines.*

/*
    async : 동시성 처리를 위한
        - launch와 다른 점은 Deferred<T>를 통해 결과값을 반환
        - 지연된 결과 값을 받기 위해 await()를 사용
 */
suspend fun doWork1():String {
    delay(1000)
    return "Work1"
}
suspend fun doWork2():String {
    delay(3000)
    return "Work2"
}

//방법1
private fun workInSerial(){
    //순차적실행
    GlobalScope.launch {
        val one = doWork1()
        val two = doWork2()
        //결과는 동시에 나옴
        println("1.Kotlin One : $one")
        println("1.Kotlin Two : $two")

    }

}
//방법2
private fun workInSerial2() :Job{
    //순차적실행
    val job=GlobalScope.launch {
        val one = doWork1()
        val two = doWork2()
        //결과는 동시에 나옴
        println("2.Kotlin One : $one")
        println("2.Kotlin Two : $two")

    }

    return job
}

//병행수행
private fun workInParallel() :Job{
    //순차적실행
    val one=GlobalScope.async {
        doWork1()
    }
    val two=GlobalScope.async {
        doWork2()
    }

    val job =GlobalScope.launch {
        val combined= one.await()+"_"+two.await()
        println("Kotlin Combined: $combined")
    }
    return job
}

fun main(){

    //방법1
    val time= measureTimeMillis {
        workInSerial()
        readLine()//입력 받으면 종료되게
    }
    println("time : $time")

    //방법2
    val time2= measureTimeMillis {
        runBlocking {
            val job2 = workInSerial2()
            job2.join()
        }
    }
    println("time2 : $time2")


    //병행수행
    val time3= measureTimeMillis {
        runBlocking {
            val job = workInParallel()
            job.join()
        }
    }
    println("time3 : $time3")
}