package kotlin2.chapter5

import java.lang.System.exit
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.system.exitProcess

/*
    동기적(Synchronous) 수행
        - 순서대로 작업을 수행하여 하나의 루틴을 완료한 후 다른 루틴을 실행하는 방식
        - 다양한 기능이 한꺼번에 일어나는 다중 실행 환경에서는 성능상의 제약 발생
        - 예, UI, 데이터 다운로드를 동시에 대응해야 하는 경우

    비동기적(Asynchronous) 수행
        - 다양한 기능을 동시에 수행할 수 있는 방식
        - 전통적인 스레드를 이용하거나, RxJava, Reactive와 같은 서드파티(third-party) 라이브러리에서 제공
        - 코틀린에서는 코루틴(coroutines)을 기본으로 제공

    코루틴(coroutines)
        - 먼저 하나의 개별적인 작업을 루틴(routine)이라고 부르는데 여러 개의 루틴들이 협력(co)한다는 의미로 만들어진 합성어
        - 코틀린의 코루틴을 사용하면 넌블로킹(Non-blocking) 또는 비동기 코드를 마치 일반적인 동기코드 처럼 쉽게 작성하면서도 비동기 효과를 낼 수 있다.


    문맥교환
        - 하나의 프로세스나 스레드가 CPU를 사용하고 있는 상태에서 다른 프로세스나 스레드가 CPU를 사용하도록 하기위해,
            이전의 프로세스 상태(문맥)를 보관하고 새로운 프로세스의 상태를 적재하는 과정

    스레드 풀
        - 자주 재사용 되는 스레드를 이용하기 위해 미리 생성된 스레드풀에서 스레드 이용
        -(스레드를 매번 생성하면 오버헤드가 생기기 때문에)
 */


//클래스
class SimpleThread :Thread(){
    override fun run() {
        println("Class Thread ${Thread.currentThread()}")
    }
}

//인터페이스
class SimpleRunnable : Runnable{
    override fun run() {
        println("Interface Thread ${Thread.currentThread()}")
    }

}

fun main(){
    //클래스로 만들어진 스레드
    val thread = SimpleThread()
    thread.start()

    //인터페이스로 만들어진 스레드
    val runnable = SimpleRunnable()
    val thread2 = Thread(runnable)
    thread2.start()

    //익명객체로 만들어진 스레드
    object : Thread(){
        override fun run() {
            println("Object Thread ${Thread.currentThread()}")
        }
    }.start()

    //람다식 형태로
    Thread{
        println("Lambda Thread ${Thread.currentThread()}")
    }.start()

    //스레드 커스텀
    //스레드의 옵션 변수를 손쉽게 설정할 수 있다.
    thread(start=true){
        println("Current Thread ${Thread.currentThread()}")
        println("Priority : ${Thread.currentThread().priority}")
        println("Name : ${Thread.currentThread().name}")
        println("Name : ${Thread.currentThread().isDaemon}")

    }

    //스레드풀
    val myService : ExecutorService = Executors.newFixedThreadPool(8)
    var i =0
    val items= arrayOf(1,3,4,5,6)

    while (i<items.size) {    //아주 큰 데이터를 처리할 때
        val item=items[i]
        myService.submit{
            //processItem(item)   //여기서 아주 긴 시간동안 처리하는 경우
        }
        i+=1
    }

}

