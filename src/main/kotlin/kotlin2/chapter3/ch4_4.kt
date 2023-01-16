package kotlin2.chapter3

import java.lang.IllegalStateException

/*
    <자료형 프로젝션>
        - 선언 지점 변성
            -> 클래스 자체에 가변성을 지정하는 방식으로 클래스에 in/out을 지정할 수 있다.
            -> 선언하면서 지정하면 클래스의 공변성을 전체적으로 지정하는 것
                (클래스를 사용하는 장소에서는 따로 자료형을 지정해 줄 필요가 없음)
            class Box<in T: Animal>(var item: T)

        - 사용 지점 변성
            -> 메서드 매개변수에서 또는 제네릭 클래서를 생성할 떄와 같이 사용 위치에서 가변성을 지정하는 방식
            class Box<T>(var item: T)
            fun <T> printObj(box: Box<out Animal>) {
                val obj: Animal = box.item // item의 값을 얻음(get)
                println(obj)
            }
            *클래스BOx의 사죶시저메서 box의 item을 얻느냐(get) 설정하느냐(set)에 따라 out/in결정
            **자료형 프로젝션을 통해 자료의 안정성을 보장

    <스타 프로젝션>
        - in/out을 정하지 않고 추후에 결정
            -> 어떤 자료형이라도 들어올 수 있으나 구체적으로 자료형이 결정되고 난 후에는 그 자료형과 하위 자료형의 요소만 담을 수 있도록 제한

    <reified 자료형 필요한 이유>
        fun <T> myFunc(c: Class<T>)
        - T 자료형은 실행 시간에 삭제
        - 컴파일 시간에는 접근 가능하나 함수 내부에서 사용하려면 위의 코드에서 함수의 매개변수를 넣어
            c : Class<T> 처럼 지정해야만 실행 시간에 사라지지 않고 접근
    <reified 자료형>
        inline fun <reified T> myFunc()
        - reified로 형식 매개변수 T를 지정하면 실행시간에 접근 가능
        - reified 자료형은 inline함수에서만 사용할 수 있다.
            -> 컴파일로가 복사해 넣을 때 실제 자료형을 알 수 있기 때문에 실행 시간에도 사용ㅎ라 수 있게 됨.

    Class<T>
        - .class 형태로 반환받는 객체
        - Class 라는 클래스는 원본 클래스에 대한 많은 메타데이터를 가짐

    Object::class
        - 코틀린의 표현 방법으로 kClass를 나타냄
     Object::class.java : 자바의 클래스를 가져옴
 */

fun main(){
    val result=getType<Float>(10)
    println("res= $result")
}

inline fun <reified T> getType(value: Int):T{
    println(T::class)       //실행 시간에 삭제되지 않고 사용가능
    println(T::class.java)

    return when(T::class){      //받아들인 제네릭 자료형에 따라 반환
        Float::class -> value.toFloat() as T
        Int::class ->value as T
        else -> throw IllegalStateException("${T::class} is not supported!")
    }
}


