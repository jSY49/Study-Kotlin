package kotlin2.chapter2

/*
    실드(shield) 클래스
        - 실드란 봉인된 이라는 의미로 무언가 안전하게 보관하기 위해 묶어 두는 것
        - 실드 클래스 그 자체로는 추상 클래스와 같기 때문에 객체를 만들 수 없다.
        - 생성자도 기본적으로는 private이며 private이 아닌 생성자는 허용하지 않음
        - 실드 클래스는 같은 파일 안에서 상속이 가능 ( 다른 파일에서 상속 불가)
        - 블록 안에 선언되는 클래스는 상속이 필요한 경우 open 키워드로 선언

    열거형 클래스
        - 여러개의 상수를 선언하고 열거된 값을 조건에 따라 선택할 수 있는 특수한 클래스
        - 자료형이 동일한 상수를 나열할 수 있다.    -> 실드클래스 처럼 다양한 자료형을 다루지 못한다.

    애노테이션(annotation) 클래스
        - 코드에 부가정보를 추가하는 기능 역할
        - @ 기호와 함께 나타내는 표기법으로 주로 컴파일러나 프로그램 실행 시간에서 사전 처리를 위해 사용
        @Test : 유닛테스트
        @JvmStatic : 자바 코드에서 컴패니언 객체를 접근
        @Target : @이 지정되어 사용할 종류 (클래스,함수,프로퍼티 등)를 정의
        @Retention : @을 컴파일된 클래스 파일에 저장할 것인지 실행 시간에 반영할 것인지 결정
        @Repeatable : @을 같은 요소에 여러번 사용 가능하게 할지를 결정
        @MustBeDocumented : @이 Api의 일부분을 문서화하기 위해 사용
 */

//실드 클래스 선언 방법 첫번째
sealed class Result {
    open class Success(val message: String): Result()
    class Error(val code: Int, val message: String): Result()
}
class Status : Result()     //실드 클래스 상속은 같은 파일에서만 가능
class Inside : Result.Success("Status")     //내부 클래스 상속

//실드 클래스 선언 방법 두번째
sealed class Result2
open class Success2(val message : String) :Result2()
class Error2(val code: Int, val message: String):Result2()
class Status2 : Result2()
class Inside2 : Success2("Status")


//열거형 클래스
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}
enum class DayOfWeek(val num :Int) {
    MONDAY(1),TUESDAY(2),WEDNESDAY(3),THURSDAY(4),
    FRIDAY(5),SATURDAY(6),SUNDAY(7)
}

interface Score{
    fun getScore(): Int
}

enum class MemberType(var prio :String):Score{
    NORMAL("Third"){
        override fun getScore(): Int =100
    },
    SILVER("Second"){
        override fun getScore(): Int =500
    },
    GOLD("First"){
        override fun getScore(): Int =1500
    }
}




fun main(){
    //실드형
    //Success에 대한 객체 생성
    val result=Result.Success("Good!")
    val msg= eval(result)
    println(msg)

    println("================ ")
    //열거형
    val day = DayOfWeek.SATURDAY
    when(day.num){
        1,2,3,4,5-> println("WEEKDAY")
        6,7->println("WEEKEND")
    }

    println("------------------")
    println(MemberType.NORMAL.getScore())
    println(MemberType.GOLD)
    println(MemberType.valueOf("SILVER"))
    println(MemberType.SILVER.prio)
    for (grade in MemberType.values()){
        println("grade.nam = ${grade.name}, prio =${grade.prio}")
    }


}
//상태를 검사하기 위한 함수
fun eval(result: Result):String =when(result){
    is Status -> "in progress"
    is Result.Success -> result.message
    is Result.Error -> result.message
    //모든 조건을 가지므로 else가 필요 없음.
}





