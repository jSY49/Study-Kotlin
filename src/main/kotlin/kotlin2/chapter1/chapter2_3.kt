package kotlin2.chapter1

/*
    -정적 변수와 메서드
    -컴페니언 객체(Companion object)


    보통 클래스는 동적으로 객체를 생성하는데 정적으로 고정하는 방법은?
        -동적인 초기화 없이 사용할 수 있는 개념으로 자바에서는 static 변수 또는 객체
        -코틀린에서는 이것을 컴페니언 객체로 사용
        -프로그램 실행 시 고정적으로 가지는 메모리로 객체 생성 없이 사용
        -단, 자주 사용되지 않는 변수나 객체를 만들면 메모리 낭비

    * 컴페이언 객체는 실제 객체의 싱글톤으로 정의됨

 */
class Hey_PerSon{
    var id :Int =0
    var name : String = "Yeon"
    companion object{
        var language : String = "korean"
        fun work(){
            println("working.....")
        }
    }
}


/*
    최상위 함수
        - 클래스 없이 만들었던 최상위 함수들은 객체 생성 없이도 어디어든 실행
        -패키지 레벨 함수(package-level function) 라고도 함
        -최상위 함수는 결국 자바에서 static final로 선언된 함수임
    *자바에서 코틀린의 최상위 함수 접근
        - 코틀린의 최상위 함수는 클래스가 없으나 자바와 연동시 내부적으로 파일명에 kw 접미사가 붙은 클래스를 자동 생성
        - 자동 변환되는 클래스명을 명시적으로 지정하고자 하는 경우 @file:JvmName("ClassNam") 을 코드 상단에 명시
 */

/*
    object와 싱글톤
        -상속할 수 없는 클래스에서 내용이 변경된 객체를 생성할 때
            -> 자바의 경우 익명 내부 클래스를 사용해 새로운 클래스 선언
            -> 코틀린에서는 object 표현식이나 object 선언으로 이 경우를 좀 더 쉽게 처리

 */
class Hobby(val name :String)
// (1) object 키워드를 사용한 방식
//object 선언은 접근 시점에 객체가 생성. 그래서 생성자 호출을 하지 않아 주/부 생성자 사용 불가.
object OCustomer {
    var name = "Kildong"
    fun greeting() = println("Hello World!")
    val HOBBY = Hobby("Basketball")
    init {
        println("Init!")
    }
}
// (2) companion object를 사용한 방식
class CCustomer {
    companion object {
        const val HELLO = "hello"  // 상수 표현
        var name = "Joosol"
        @JvmField val HOBBY = Hobby("Football")
        @JvmStatic fun greeting() = println("Hello World!")
    }
}

/*
    object 표현식
        - object 선언과 달리 이름이 없으며 싱글턴이 아님
        - 따라서 object 표현식이 사용될떄마다 새로운 인스턴스가 생성
        - 이름이 없는 익명(임시로 사용한다는 의미) 내부 클래스로 불리는 형태를 object 표현식으로 만들수 있다.
 */

open class SuperMan(){
    fun work() = println("Taking Photo")
    fun talk() = println("Talking with people")
    open fun fly()  = println("flyinf in the air")
}



fun main(){
    val person =Hey_PerSon()
    println(Hey_PerSon.language)
    Hey_PerSon.language="English"
    println(Hey_PerSon.language)
    Hey_PerSon.work()
    //println(PerSon.name)  //name 이 컴페니언 오브젝트 아니니까 오류
    println(person.name)    //이건 됨


    println()

    val pretendedMan = object :SuperMan(){  //object표현식으로 fly구현의 재정의
        override fun fly() =println("I'm not a real superman. I can't fly!")
    }

    pretendedMan.work()
    pretendedMan.talk()
    pretendedMan.fly()



}