package kotlin2.chapter1

import kotlin.properties.Delegates

/*
    프로퍼티 지연 초기화
    필요 이유 :
        클래스에서는 기본적으로 선언하는 프로퍼티 자료형들은 null을 가질 수 없음
        하짐나 객체의 정보가 나중에 나타나는 경우 나중에 초기화 할 수 있는 방법 필요
        지연 초기화를 위해 lateinit 과 lazy 키워드 사용

    lateinit : 의존성이 있는 초기화나 unit 테스트를 위한 코드를 작성 시
        예> Car 클래스의 초기화 부분에 engine 클래스와 의존성을 가지는 경우
        Engine 객체가 생성되지 않으면 완전하게 초기화 할 수 없는 경우
        예> 단위(Unit) 테스트를 위해 임시적으로 객체를 생성 시켜야 하는 경우

        -> 클래스를 선언할 때, 프로퍼티 선언은 Null을 허용하지 않는다.
        하지만 지연 초기화를 위한 lateinit 키워드를 사용하면 프로퍼티에 값이 바로 할당되지 않아도 됨

        * var fh tjsdjsehls vmfhvjxlaks rksmd
        **프로퍼티에 대하나 게터와 세터를 사용할 수 없음.

    lazy : 호출 시점에 by lazy {...} 정의에 의해 블록 부분의 초기화를 진행한다.
        불변의 변수 선언인 val에서만 사용 가능하다.
        val 이므로 값을 다시 변경 할 수 없다.

        -> 3가지 모드
            -Synchronized 락을 사용해 단일 스레드만이 사용하는 것을 보장(기본값)
            -Publication 여러군데서 호출될 수 있으나 처음 초기화 된 후 반환값 사용
            -None 락을 사용하지 않기 때문에 빠르지만 다중 스레드가 접근할 수 있음(값의 일관성을 보장할 수 없음)

    by : 하나의 클래스가 다른 클래스에 위임하도록 선언
        위임된 클래스가 가지는 멤버를 참조없이 호출
    *위임을 사용하는 이유?
        -코틀린의 기본 라이브러리는 open되지 않은 최종 클래스
            -> 표준 라이브러리의 무분별한 상속의 복잡한 문제들을 방지
            -> 단, 상속이난 직접 클래스의 기능 확장을 하기 어렵다.
    *위임을 사용하면?
        -위임을 통해 상속과 비슷하게 최종 클래스의 모든 기능을 사용하면서 동시에 기능을 추가 확장 구현할 수 있다.

 */

//lateinit
class ch2_2Person{
    lateinit var name: String       //1. 늦은 초기화를 위한 선언

    fun test(){
        if(!::name.isInitialized){      //2. 프로퍼티의 초기화 여부 판단
            println("not initialized")
        }else{
            println("initialized")
        }
    }

}

//lazy
class LazyTest {
    init {
        println("init block") // ②
    }

    val subject by lazy {
        println("lazy initialized") // ⑥
        "Kotlin Programming" // ⑦ lazy 반환값
    }
    fun flow() {
        println("not initialized") //  ④
        println("subject one: $subject") // ⑤ 최초 초기화 시점!
        println("subject two: $subject") // ⑧ 이미 초기화된 값 사용
    }
}

class ch2_2_Person(val name: String, val age: Int)

//클래스 위임
interface Car{
    fun go() :String
}
class VanImpl(val power : String):Car{
    override fun go() = "는 짐을 적재하며 $power 마력을 가집니다."
}
class SportImpl(val power : String):Car{
    override fun go() = "는 경주용에 사용되며 $power 마력을 가집니다."
}
class carModel (val model:String, impl : Car): Car by impl{
    fun carInfo(){
        println("$model ${go()}")
    }
}

/*
    observable  : 프로퍼티를 감시하고 있다가 특정 코드의 로직에서 변경이 일어날 때 호출
    vetoable    : 감시보다는 수여한다는 의미로 반환값에 따라 프로퍼티 변경을 허용하거나 취소
 */
class Ob_User{
    //observable은 값이 변화를 감시하는 일종의 콜백루틴
    var name: String by Delegates.observable("NONAME"){     //프로퍼티를 위임
        pop,old,new->       //람다식 매개변수로 프로퍼티, 기존값, 새로운 값
        println("$old -> $new") //이부분은 이벤트가 발생할 때만 실행됨
    }
}



fun main(){
    //latetinit
    val Gill = ch2_2Person()
    Gill.test()
    Gill.name = "Gill"          //3. 이 시점에서 초기화됨 (지연 초기화)
    Gill.test()
    println("name = ${Gill.name}")

    println()
    //lazy
    val test = LazyTest()   //①
    test.flow()             //③



    var isPersonInstantiated: Boolean = false  // ① 초기화 확인 용도

    val person : ch2_2_Person by lazy { // ② lazy를 사용한 person 객체의 지연 초기화
        isPersonInstantiated = true
        ch2_2_Person("Kim", 23) // ③ 이 부분이 Lazy 객체로 반환 됨
    }
    val personDelegate = lazy { ch2_2_Person("Hong", 40) }  // ④ 위임 변수를 사용한 초기화

    println("person Init: $isPersonInstantiated")
    println("personDelegate Init: ${personDelegate.isInitialized()}")

    println("person.name = ${person.name}")  // ⑤ 이 시점에서 초기화
    println("personDelegate.value.name = ${personDelegate.value.name}")  // ⑥ 이 시점에서 초기화

    println("person Init: $isPersonInstantiated")
    println("personDelegate Init: ${personDelegate.isInitialized()}")

    println()

    //by
    val myDamas = carModel("Damas 2010",VanImpl("100마력"))
    val my350z = carModel("Damas 2010",SportImpl("100마력"))

    myDamas.carInfo()
    my350z.carInfo()

    println()
    //observable
    val user = Ob_User()
    user.name="Gill"    //값이 변경되는 시점에서 첫 이벤트 발생
    user.name="Yeon"    //값이 변경되는 시점에서 두번째 이벤트 발생

    println()
    //vetoable
    var max : Int by Delegates.vetoable(0){     //초기값은 0
        prop,old,new ->
        new> old        //조건에 맞지 않으먄 거부권 행사
    }
    println(max)    //0
    max =10
    println(max)    //10
    max=5       //여기서는 조건에 맞지 않으므로 재할당 하지 않음.
    println(max)    //10

}