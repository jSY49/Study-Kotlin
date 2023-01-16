package kotlin2.chapter3
/*
    가변성 3가지
        - 공변성   : T'가 T의 하위 자료형이면, C<T'>는 C<T>의 하위 자료형이다. 생산자 입장의 out 성질
        - 반공변성  :T'가 T의 하위 자료형이면, C<T'>는 C<T>의 하위 자료형이다. 소비자 입장의 in 성질
        - 무변성   : C<T'>와 C<T>는 아무 관계가 없다. 소비자+생상자



    - 공변성   : 형식 매개변수 사이의 하위 자료형 관계가 성립. 하위 자료형 관계가 그대로 인스턴스 자료형 사이의 관계로 이어지는 경우
    - 반공변성 : 자료형의 상하관계가 반대. 하위 클래스의 자료형을 상위 클래스의 자료형이 허용
    - 무변성   : 자료형 사이의 하위 자료형 관계가 성립하지 않음. 코틀린에서는 따로 지정해 주지 않으면 기본적으로 무변성
 */

//무변성
class Box1<T> (val size :Int)
//공변성
class Box2<out T> (val size :Int)
//반공변성
class Box3<in T> (val size :Int)

fun main(args:Array<String>){

    //Any-int-Nothing

    //무변성
    //val anys1 : Box1<Any> =Box1<Int>(10)    //자료형 불일치로 오류
    //val anys2 : Box1<Nothing> =Box1<Int>(10)    //자료형 불일치로 오류


    //공변성
    val anys1 : Box2<Any> =Box2<Int>(10)    //관계 성립으로 객체 생성 가능
    //val anys2 : Box<Nothing> =Box<Int>(10)    //자료형 불일치로 오류
    println(anys1.size)

    //반공변성
//    val anys2 : Box3<Any> =Box3<Int>(10)   //자료형 불일치로 오류
    val anys3 : Box3<Nothing> =Box3<Int>(10) //관계 성립으로 객체 생성 가능
    println(anys3.size)
}