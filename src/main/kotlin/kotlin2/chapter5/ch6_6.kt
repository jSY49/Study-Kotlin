package kotlin2.chapter5

import kotlinx.coroutines.*

/*
    스레드풀
        - 보통 CommonPool이 지정되어 코루틴이 사용할 스레드의 공동 풀을 사용
        - 이미 초기화되어 있는 스레드 중 하나 혹은 그 이상이 선택되며 초기화하기 때문에 스레드를 생성하는 오버헤드가 없어 빠름
        - 하나의 스레드에 다수의 코루틴이 동작할 수 있다.

    빌더의 특정 속성 지정
        - 시작 시점에 대한 속성 - launch의 원형
            public fun CoroutineScope.launch(
                context: CoroutineContext,
                start: CoroutineStart,
                block: suspend CoroutineScope.() -> Unit
            ): Job
        - CoroutineStart
            - DEFAULT       : 즉시 시작 (해당 문맥에 까라 즉시 스케줄링됨)
            - LAZY          : 코루틴을 느리게 시작(처음에는 중단된 상태이며 start() 나 await() 등으로 시작)
            - ATOMIC        : 원자적으로 즉시 시작 (DEFAULT와 비슷하나 코루틴을 실행전까지는 취소 불가)
            - UNDISPATCHED  : 현재 스레드에서 즉시 시작 ( 첫 지연함수까지, 이후 재개시 디스패치됨)

 */

fun main()= runBlocking<Unit> {
    println("runBlovking : $coroutineContext")
    val request = launch {
        GlobalScope.launch {//프로그램 전역을 독립적인 수행(부모-자식관계 없음)
            println("job1: Before suspend function, $coroutineContext")
            delay(1000)
            println("job1: After suspend function, $coroutineContext")
        }

        //밑에 세개 각각 주석해제해서 실행해봐
       // launch {//부모의 문맥을 상속(상위 launch의 자식)
       //launch(Dispatchers.Default){  //부모의 문맥을 상속 (상위 launch의 자식), 분리된 작업
       CoroutineScope(Dispatchers.Default).launch{   //새로운 스코프가 구성되 request와 무관
            delay(100)
            println("job2: Before suspend function, $coroutineContext")
            delay(1000)
            println("job2: After suspend function, $coroutineContext") //requset(부모)가 취소되면 수행되지 않음

        }

    }

    delay(500)
    request.cancel()    //부모 코루틴의 취소
    delay(1000)

}
