package kotlin2.chapter5

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    JOB
        - 코루틴의 생명주기를 관리하며 생성된 코루틴 작업들은 부모-자식과 같은 관계를 가질 수 있다.
        - 규칙
            - 부모가 취소되거나 실행실패하면 그 하위 자식들은 모두 취소가 된다.
            - 자식의 실패는 그 부모에 전달되며 부모 또한 실패한다.(다른 모든 자식도 취소됨)
        -Supervisor Job
            - 자식의 실패가 그 부모나 다른 자식에 전달되지 않으므로 실행을 유지할 수 있다.
        - Job() 팩토리 햄수나 launch에 의해 job 인스턴스가 생성. 아래는 인스턴스의 상태
            상태                    isActive    isCompleted     isCancelled
            New                      false       false           false
            Active (기본값 상태)       true        false           false
            Completing               true        false           false
            Cancelling               false       false           true
            Cancelled (최종 상태)     false       true             true
            Completed (최종 상태)     false       true             false
        - 상태를 판별하기 위해 job에는 isActive, isCompleted, isCancelled 변수를 제공
        - 생성되면 활성화 상태인 Active를 가집니다. 하지만 매개변수를 CoroutineStart.LAZY로 설정한 경우에는 아직은 활성화되지 않고 생성만 된 New 상태
        - 이때 Active 상태로 가기 위해서는 start()나 join()을 호출하면 됨.

    JOB과 명시적 완료 기다리기
        - join() 결과 기다리기
            - job객체의 join()을 사용해 완료를 기다릴 수 있다.
                -launch에서 반환 값을 받으면 Job 객체가 되기 때문에 이것을 이용해 Main 메서드에서 join()을 호출할 수 있다.



    코루틴의 중단과 취소
        -중단(코루틴 코드 내에서)
            -> delay(시간값) : 일정 시간을 지연하며 중단
            -> yield()      : 특정 값을 산출하기 위해 중단
        -취소(코루틴 외부에서)
            -> Job.cancel()         : 지정된 코루틴 작업을 즉시 취소
            -> Job.cancleAndJoin()  : 지정된 코루틴 작업을 취소(완료시까지 기다림)
        -> 기본적으로 부모 자식 관계에 적용될 수 있으며 부모 블록이 취소되면 모든 자식 코루틴이 취소된다.
 */


fun main(){
    val job =GlobalScope.launch {
        delay(1000L)
        println("World!")
        doSomething1()
    }
    println("Hello")
    println("job is Active : ${job.isActive}")
    println("job is Active : ${job.isCompleted}")
    Thread.sleep(2000L)
    println("job : $job")
    println("job is Active : ${job.isActive}")
    println("job is Active : ${job.isCompleted}")



    //join 사용하기
    println("======join 사용하기======")
    runBlocking {
        val job2 =GlobalScope.launch {
            delay(1000L)
            println("World!")

        }
        println("Hello~~~")
        println("job is Active : ${job.isCompleted}")
        job2.join()
        println("job is Active : ${job.isCompleted}")
    }

}

suspend fun doSomething1(){
    println("Do Something")
}