package kotlin2.chapter5

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    완료를 기다리기 위한 블로킹
        - runBlocking의 사용
            - 새로운 코루틴을 실행하고 완료되기 전까지는 현재(caller) 스레드를 블로킹
            - 코루틴 빌더와 마찬가지로 CoroutineScope의 인스턴스를 가짐

    특정 문맥과 함께 실행
        -withContext()
            - 인자로 코루틴 문맥을 지정하며 해당 문맥에 따라 코드 블록을 실행
            - 해당 코드 블록은 다른 스레드에서 수행되며 결과를 반환 한다.
            - 부모 스레드는 블록하지 않는다.

    또 다른 사용 예
        -완료 보장
            -withContext(NonCancelable){..}
                -> try{..} finally{..} 에서 finally 블록의 실행을 보장하기 위해 취소 불가 불록 구성

    스코프 빌더(scope Builder)
        - coroutineScope 빌더
            - 자신만의 코루틴 스코프를 선언하고 생성할 수 있다.
            - 모든 자식이 완료되기 전까지는 생성된 코루틴 스코프는 종료되지 않는다.
            - runBlocking과 유사하지만 runBlocking은 단순 함수로 현재 스레드를 블로킹, coroutineScope는 단순히 지연(suspend)함수 형태로 넌블로킹으로 사용됨.
            - 만일 자식 코루틴이 실패하면 이 스코프도 실패하고 남은 모든 자식은 취소된다. (반면 supervisorScope는 실패하지 않음)
                -> 외부에 의해 작업이 취소되는 경우 CancellationException 발생
        - supervisorScope 빌더
            - 마찬가지로 코루틴 스코프를 생성하며 이때 SupervisorJob과 함께 생성하여 기존 문맥의 Job을 오버라이드 한다.
                -> launch를 사용해 생성한 작업의 실패는 CoroutineExceptionHandler를 통해 핸드링
                -> async를 사용해 생성한 작업의 실패는 Deferred.await의 결과에 따라 핸드링
                -> parent를 통해 부모작업이 지정되면 자식작업이 되며 이때 부모에 따라 취소여부 결정
            - 자식이 실패하더라도 이 스코프는 영향을 받지 않으므로 실패하지 않는다.
                ->실패를 핸들링하는 정책을 구현할 수 있다.
            -예외나 의도적인 취소에의해 이 스코프의 자식들을 취소하지만 부모의 작업은 취소하지 않는다.


    부모와 잣기 코루틴과의 관계
        -병렬 분해(Parallel decomposition)
            - loadImage(name2)는 여전히 진행된다.
            - 코루틴 문맥에서 실행하여 자식 코루틴으로 구성한다면 예외를 부모에 전달하고 모든 자식 코루틴을 취소할 수 있다
            suspend fun loadAndCombine(name1: String, name2: String): Image =
            coroutineScope { // 스코프를 주어줌으로 특정 자식 코루틴의 취소가 생기면 모든 자식을 취소하게 됨
                val deferred1 = async { loadImage(name1) }
                val deferred2 = async { loadImage(name2) }
                combineImages(deferred1.await(), deferred2.await())
            }

    스코프의 취소와 예외처리
        val scope2 = CoroutineScope
        val routine1 = scope2.launch { ... }
        val routine2 = scope2.async { ... }
        scope2.cancel()
        또는
        scope2.cancelChildren()

        try {
            ...
        } catch (e: CancellationException) {
            ... 취소에 대한 예외처리...
        }

    -코루틴의 실행 시간 지정
        - 실행 시간 제한
            - withTimeout(시간값) { ... } - 특정 시간값 동안만 수행하고 블록을 끝냄
                -> 시간값이 되면 TimeoutCancellationException 예외를 발생
            - withTimeoutOrNull(시간값) { ... } - 동작은 위와동일
                -> 단, 예외를 발생하지 않고 null을 반환
            val result = withTimeoutOrNull(1300L) {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
                "Done" // 코루틴 블록이 취소되면 이 값이 result에 반환됨
            }
            println("Result is $result")


 */


fun main()= runBlocking<Unit>{    //메인 메서드가 코루틴 환경에서 실행
    launch {    //백그라운드로 코루틴 실행
        delay(1000L)
        println("World")
    }
    println("Hello")    //즉시 이어서 실행됨
    //delay(2000L)  //delay()를 사용하지 않아도 코루틴을 기다림
}