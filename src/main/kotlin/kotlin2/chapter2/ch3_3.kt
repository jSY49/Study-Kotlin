package kotlin2.chapter2

/*
    data class
        - 데이터 전달을 위한 객체 DTO(Data Transfer Object)
        - 자바에서는 POJO(Plain Old Java Object)라고 불리기도
        - 구현 로직을 가지고 있지 않고 순수한 데이터 객체를 표현해서 보통 속성과 속성을 접근하고자 하는 게터/세터를 가짐.
        - oString(), equals() 등과 같은 데이터 표현하거나 비교하는 메서드를 가져야함.

        *구현로직없이 데이터를 표현

        코틀린의 프로퍼티 = 필드(변수) + 게터세터

        선언 예>
        data class Customer(var name: String, var email: String)

        데이터 클래스는 다음과 같은 조건을 만족해야 합니다.
            - 주 생성자는 최소한 하나의 매개변수를 가져야 한다.
            - 주 생성자의 모든 매개변수는 val, var로 지정된 프로퍼티여야 한다.
            - 데이터 클래스는 abstract, open, sealed, inner 키워드를 사용할 수 없다.


        **자동 생성되는 메서드
        equals()        : 두 객체의 내용이 같은지 비교하는 연산자.
        hashCoe()       : 객체를 구별하기 위한 고유한 정수값 생성, 데이터 셋이나 해시테이블을 사용하기 위한 하나의 생성된 인덱스\
        copy()          : 빌더없이 특정 프로퍼티만 변경해서 객체 복사
        toString()      : 데이터 객체를 읽기 편한 문자열로 반환
        componentN()    : 객체의 선언부 구조를 분해하기 위해 프로퍼티에 상응하는 메서드

        -디스트럭처링 : 객체가 가지고 있는 프로퍼티를 개별 변수로 분해
        val (name,email)=cus1
        val (_,email)=cus1  //특정 프로퍼티를 가져올 필요가 없는 경우

 */

data class Customer(var name: String, var email: String){
    var job :String ="Unknown"
    constructor(name:String,email: String,_job:String) :this(name,email){
        job=_job
    }
    init {
        //간단한 로직은 여기에
    }
}

fun main(){

    val cus =Customer("yeon","kotlin@gmail.com","dev")
    println(cus.toString())
    println(cus.job)

}

