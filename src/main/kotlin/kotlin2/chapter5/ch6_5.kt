package kotlin2.chapter5

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    코루틴 문맥(Coroutine Context)
        - 코루틴을 실행하기 위한 다양한 설정값을 가진 관리 정보
            -> 코루틴 이름, 디스패처, 작업 상세사항, 예외 핸들러 등
        -디스패처는 코루틴 문맥을 보고 어떤 스레드에서 실행되고 있는지 식별이 가능해진다.
        -코루틴 문맥은 + 연산을 통해 조합될 수 있다.

    CoroutineName
        - 코루틴에 이름을 주며 디비겅을 위해서 사용됨
        - val coName = CoroutineName("somthingName")
    Job
        - 작업 객체를 지정할 수 있으며 취소가능 여부에 따라 SupervisorJob()사용
        - val parentJob = SupervisorJob()  //or Job()
          val someJob = Job(parentJob)
    CoroutineDispatcher
        - Dispatcher.Default, ...IO, 등을 지정할 수 있으며 필요에 따라 스레드 생성 가능
        - val MyPool = Executors.newFixedThreadPool(2).asCoroutineDispatcher()

    CoroutineExceptionHandler
        - 코루틴 문맥을 위한 예외처리를 담당하며 코루틴에서 예외가 던져지면 처리한다.
        - 예외가 발생한 코루틴은 상위 코루틴에 전달되어 처리될 수 있다.
            -> 스코프를 가지는 경우 예외 에러를 잡아서 처리할 수 있다.
        - 만일 예외처리가 자식에만 있고 부머에 없는 경우 부모에도 예외가 전달되므로 주의
            -> 이 경우 앱이 꺠지게(crash)됨
        -예외가 다중으로 발생하면 최초 하나만 처리하고 나머지는 무시됨

    GlobalScope
        - 독립형(standalone)코루틴을 구성, 생명주기는 프로그램 전체(top-level)에 해당하는 범위를 가지며 main의 생명주기가 끝나면 같이 종료
        - Dispatchers.Unconfined와 함께 작업이 서로 무관한 전역 범위 실행
        - 보통 GlobalScope 상에서는 launch나 async 사용이 권장되지 않음.

    CoroutineScope
        - 특정 목적의 디스패처를 지정한 범위를 블록으로 구성할 수 있다.
        - 모든 코루틴 빌더는 CoroutineScope의 인스턴스를 갖는다.
        - launch{..}와 같이 인자가 없는 경우에는 CoroutineScope에서 상위의 문맥이 상속되어 결정
        - launch(Dispatcher.옵션인자){..} 와 같이 디스패처 스케줄러를 지정가능
            -> Dispatchers.Default는 GlobalScope에서 실행되는 문맥과 동일하게 사용

 */

fun main()= runBlocking{    //runBlocking()인자 넣어 줄 수 있음

    launch {    //launch()인자 넣어 줄 수 있음
        delay(200L)
        println("Task from runBlock")
    }

    coroutineScope {
        launch {
            delay(200L)
            println("Task from nestd launch")   //2
        }
        delay(200L)
        println("Task from coroutineScope") //1
    }

    println("end of runblock")
}
