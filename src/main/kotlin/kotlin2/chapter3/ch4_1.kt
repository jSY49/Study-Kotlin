package kotlin2.chapter3

/*
    Generic(제네릭)
        - 자료형의 객체들을 다루는 메서드나 클래스에서 컴파일 시간에 자료형을 검사해 적당한 자료형을 선택할 수 있도록 하기 위해

    <제네릭의 일반적인 사용>
        - 앵클 브래킷(<>) 사이에 형식 매개변수를 사용해 선언
        - 형식 매개변수는 자료형을 대표하는 용어로 T와 같이 특정 영문의 대문자로 사용

    <형식 매개변수의 이름>
        :강제성은 없으나 일종의 규칙처럼 사용됨
    E           => 요소
    K           => 키
    N           => 숫자
    T           => 형식
    V           => 값
    S,U,V etc.  => 두번째, 세번째, 네번째 형식

 */



//prac1.
class Box<T>(t: T) { // 제네릭을 사용해 형식 매개변수를 받아 name에 저장
    var name = t
}

//prac2.
open class Parent
class Child :Parent()

class Cup<T>        //가변성을 주려면 in,out 등을 설정햐여 한다.

//prac3.
class GenericNull<T>{                       //null을 허용하지 않으려면 -> <T: Any>로 자료형 지정해주면 됨  **널 가능한 타입은 Any? .
    fun EqualityFunc(arg1 :T , arg2 :T){
        println(arg1?.equals(arg2))
    }
}



fun main() {
    //prac1.
    //자료형이 추론 가능한 경우 앵클 브라켓 표현 생략 가능
    val box1: Box<Int> = Box<Int>(1)
    val box2: Box<String> = Box<String>("Hello")
    println(box1.name)
    println(box2.name)

    println("-------------")

    //prac2.
    val obj1 = Parent()
//    val obj1_1 :Child= Parent()   //불가
    val obj2 = Child()
    val obj2_1:Parent = Child()     //가능

    val obj3 =Cup<Parent>()
//    val obj3_1 :Cup<Child> =Cup<Parent>()     //불가
    val obj4 =Cup<Child>()
//    val obj4_1 :Cup<Parent> =Cup<Child>()     //불가

    println("-------------")
    //prac3.
    val obj5 = GenericNull<String>()                //non null              **String 과 String? 는 다른 타입의 자료형
    obj5.EqualityFunc("Hello","World")  //null 허용 안됨

    val obj6 = GenericNull<Int?>()
    obj6.EqualityFunc(null,10)




}