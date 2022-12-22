package kotlin2.chapter1

/*
    -객체지향 프로그래밍(OOP:Object-Oriented Programming)
        -> 프로그램의 구조를 객체간 상호작용으로서 표현하는 프로그래밍 방식
        -> 절차적 프로그래밍의 한계를 극복하고자 나온 언어의 한 가지 방법론
        -> 객체와 관계를 표현하고 이를 통해 확장과 재상용이 용이
        -> 자바와 코틀린은 OOP/FP를 지원

    -객체지향 개념상의 용어
        -> 추상화(abstraction): 특정 클래스를 만들 때 기본 형식을 규정하는 방법
        -> 인스턴스(instance): 클래스로부터 객체를 생성
        -> 상속(inheritance): 부모 클래스의 내용을 자식 클래스가 그대로 물려 받는 것
        -> 다형성(polymorphism): 하나의 이름으로 다양한 처리를 제공하는 것
        -> 캡슐화(encapsulation): 내용을 숨기고 필요한 부분만 사용
        -> 메시지 전송(message sending): 객체들 간에 주고 받는 메시지
        -> 연관(association): 클래스들의 관계
        
    -클래스(class)
        -> 분류, 계층, 등급 등의 우리말 뜻
        -> 특정 대상을 분류하고 특징(속성)과 동적 홀동(함수) 내용을 기록
        
    -추상화        
        -> 목표로 하는 것에 대해 필요한 만큼 속성과 동작을 정의하는 과정

    분류/범주  -> class
    속성/변수/필드/데이터    -> property
    함수/동작/행동    -> Method
    인스턴스    -> Object(객체)

 */

class chapter1_1 {
    val Wheel: Int =4
    fun start(){
        println("Engine Start!")
    }
}
fun main(){
    val sonata = chapter1_1()
    println(sonata.Wheel)
    sonata.start()
}