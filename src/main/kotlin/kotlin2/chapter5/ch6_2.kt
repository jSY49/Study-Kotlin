package kotlin2.chapter5

import kotlinx.coroutines.*


/*
    코루틴
        - 스레드와 달리 코틀린은 코루틴을 통해 복잡성을 줄이고도 손쉽게 일시 중단하거나 다시 시작하는 루틴을 만들어 낼 수 있다,
        - 멀티태스킹을 실현하면서 가벼운 스레드라고도 불림
        - 코루틴은 문맥 교환없이 해당 루틴을 일시 중단(Suspended)을 통해 제어
        - Kotloinx.coroutines 패키지 사용

    코루틴 빌더
        launch
            - 일단 실행하고 잊어버리는(fire and forget) 형태의 코루틴으로 메인 프로그램과 독립되어 실행할 수 있다.
            - 기본적으로 즉시 실행하며 블록 내의 실행 결과는 반환하지 않는다.
            - 상위 코드를 블록 시키지 않고(넌블로킹) 관리를 위한 Job 객페를 즉시 반환한다.
            - join을 통해 상위 코드가 종료되지 않고 완료를 기다리게 할 수 있다.

        async
            - 비동기 호출을 위해 만든 코루틴으로 결과나 예외를 반환한다.
            - 실행 결과는 Deffered<T>를 통해 반환하며 await을 통해 받을 수 있다.
            - await은 작업이 완료될 때까지 기다리게 된다.


    사용자 함수에 suspend
        - 컴파일러는 suspend가 붙은 함수를 자동적으로 추출해 Continuation클래스로부터 분리된 루틴을 만든다.
        - 이러한 함수를 사용하기 위해 코루틴 빌더인 launch 와 async에서 이용할 수 있다.
 */

fun main(){
    //메인 스레드의 문맥
    GlobalScope.launch {//새로운 코루틴을 백그라운드에 실행
        delay(1000L)    //1초의 넌블로킹 지연 (ms)  delay는 코루틴 블록 안에서만 사용
        println("World!")       //1초 지연 후 출력
        doSomething()
    }
    println("Hello, ")  //메인 스레드가 코루틴이 지연되는 동안 계속 실행
    Thread.sleep(2000L) //메인 스레드가 JVM에서 바로 종료되지 않게 2초 기다림. 없으면 world가 찍히지 않고 종료됨



}

suspend fun doSomething(){
    println("Do Something")
}