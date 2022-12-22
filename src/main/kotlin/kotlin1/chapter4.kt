package kotlin1

import java.io.FileOutputStream
import java.io.PrintWriter
import kotlin.random.Random

fun main(){

    data class Person(var name : String, var skills : String)

    //코틀린 제공 표준 라이브러리
    //prac_closure()

    //prac_let()

    //prac_also()
    println("<also>")
    val person = Person("Gill","kotlin")
    val a = person.also {
        it.skills = "java"
        "Success"       //let과 달리 반영이 안됨
    }
    println("also prac_ a= $a")

    //prac_apply()
    // 여기서 this는 person 객체를 가리킴
    person.apply { this.skills = "Swift" }
    println(person)

    println("<apply>")
    val retrunObj = person.apply {
        name = "Sean" // ① this는 생략할 수 있음
        skills = "Java" // this 없이 객체의 멤버에 여러 번 접근
    }
    println(person)
    println(retrunObj)

    println("<run>")
    //prac_run()

    println("<with>")
   // prac_with()
    val user = Person("gill","act")
    val res = with(user){
        skills="singing"
        name="Gill"
        //마지막 반환식 없으면 Unit이 반환
    }
    println("res = $res")
    println(user)

    println("<use>")
   // prac_use()

    prac_etc()

}
//클로저 연습
class Calc{
    fun addNum(a: Int, b:Int,add:(Int,Int)->Unit){  //람다식 add에는 반환값이 없음
        add(a,b)
    }
}
fun prac_closure(){
    /*
    - 람다식으로 표현된 내부 함수에서 외부 범위에 선언된 변수에 접근할 수 있는 개념
    - 람다식 안에 있는 외부 변수는 값을 유지하기 위해 람다가 포획(captur)한 변수
     */
    val calc = Calc()
    var res = 0 //외부의 변수
    calc.addNum(2,3){x,y->res=x+y}  //클로저
    println("res = $res")   //값을 유지하여 5가 출력
}

//let() 연습
fun prac_let(){
    /*
    public inline fun <T,R> T.let(block: (T) -> R) : R {...return block(this)}
    - 매개변수 block 은 T를 매개변수로 받아 R을 반환
    - let()함수 역시 R을 반환
    - 본문의 this는 객체 T를 가리키는데 람다식 결과 부분을 그대로 반환한다는 뜻.
    - 다른 메서디를 실행하거나 연산을 수행해야 하는 경우 사용
    */

    /*
    ! 지정된 값이 null 이 아닌 경우에 코드를 실행해야 하는 경우.
    ! Nullable 객체를 다른 Nullable 객체로 변환하는 경우.
    ! 단일 지역 변수의 범위를 제한 하는 경우.
    */

    //1.null 이 아닌 경우에 코드를 실행해야 하는 경우.
    println("1.null 이 아닌 경우에 코드를 실행해야 하는 경우.")
    val score : Int? = 32
    fun checkScore(){       //일반적인 null검사
        if(score!=null){
            println("Score : $score")
        }
    }
    //let을 이용한 null검사
    fun checkScoreNull(){
        score?.let { println("Score : $it") }
        var str = score.let { it.toString() }
        println("str : $str")
    }
    checkScore()
    checkScoreNull()

    //2. let 함수의 체이닝(chaining)
    println("2. let 함수의 체이닝(chaining)")
    var a=1
    var b=2

    a= a.let { it+2 }.let {
        println("a = $a")
        val i = it +b
        i
    }
    println("a = $a")

    //3. let의 중첩사용
    println("3. let의 중첩사용")
    var x = "kotlin"
    x=x.let { outer ->
        outer.let { inner->
            println("Inner is $inner and outer is $outer")    //이떄는 it을 사용하지 않고 명시적이름 사용
            "Inner String"                              //반환x
        }
        "Outer String"                                  //반환o
    }
    println("x = $x")

    //null검사 할때 else기능 추가
    val name: String?
    name =null
    name?.let { println("name is $name") } ?: println("?: name is $name")

}

//also연습
fun prac_also(){
    /*
    public inline fun <T> T.also(block: (T) -> Unit): T { block(this); return this }
    - 함수를 호출하는 객체 T를 이어지는 block에 정달하고 객체 T 자체를 반환
    - 블록 아늬 코드 수행 결과와 상관없이 T인 바로 객체 this 를 반환
     */
    /*
    ! 수신 객체 람다가 전달된 수신 객체를 전혀 사용 하지 않거나 수신 객체의 속성을 변경하지 않고 사용하는 경우 also 를 사용합니다.
     */

    //기본 사용
    var m = 1
    m = m.also { it + 3 }
    println("m = $m") // 원본 값 1

    //예제는 메인 함수에 있어
}

//apply연습
fun prac_apply(){
    /*
    public inline fun <T> T.apply(block: T.() -> Unit): T { block(); return this }
    -  also()함수와 마찬가지로 호출하는 객체 T를 이어지는 block으로 전달하고 객체 자체인 this를 반환합니다.
    - 특정 객체를 생성하면서 함께 호출해야 하는 "초기화" 코드가 있는 경우 사용할 수 있습니다.
    - 다른 점은 T.()와 같은 표현에서 람다식이 확장 함수로서 처리된다
     */

    /*
    ! 수신 객체 람다 내부에서 수신 객체의 함수를 사용하지 않고 수신 객체 자신을 다시 반환 하려는 경우에 apply 를 사용
     */
}

fun prac_run(){
    /*
    public inline fun <R> run(block: () -> R): R  = return block()
    public inline fun <T, R> T.run(block: T.() -> R): R = return block()
    - 인자가 없는 익명 함수처럼 동작하는 형태로 단독 사용하거나 확장 함수 형태로 호출하는 형태 두 가지로 사용할 수 있습니다.
     */
    /*
    ! 어떤 값을 계산할 필요가 있거나 여러개의 지역 변수의 범위를 제한할 때
     */
    var skills ="kotlin"
    val a = 10
    skills = run {
        val level = "Kotlin Level:" + a
        level // 마지막 표현식이 반환됨
    }
    println(skills)
}

fun prac_with(){
    /*
    public inline fun <T, R> with(receiver: T, block: T.() -> R): R  = receiver.block()
    - 인자로 받는 객체를 이어지는 block의 receiver로 전달하며 결과값을 반환
    - run()함수와 기능이 거의 동일한데, run의 경우 receiver가 없지만 with()에서는 receiver로 전달할 객체를 처리
    */
    /*
    ! Non-nullable (Null 이 될수 없는) 수신 객체 이고 결과가 필요하지 않은 경우에만 with 를 사용합니다.
     */

}

fun prac_use(){
    /*
    public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R
    - 보통 특정 객체가 사용된 후 닫아야 하는 경우가 생기는데 이때 use()를 사용하면 객체를 사용한 후 close() 등을 자동적으로 호출해 닫아 줄 수 있습니다.
     */

    //output.txt 파일에 hello라는 문자열을 저장하는 코드
    PrintWriter(FileOutputStream("d:\\test\\output.txt")).use {
        it.println("hello")
    }
}

fun prac_etc(){
    /*
    takeIf()과 takeUnless()의 활용
    public inline fun <T> T.takeIf(predicate: (T) -> Boolean): T?
    = if (predicate(this)) this else null
    -takeIf()함수는 람다식이 true이면 결과를 반환하고, takeUnless()함수는 람다식이 false이면 결과를 반환합니다.

     */
    val input = "kotlin"
    val keyword = "in"
    input.indexOf(keyword).takeIf { it>=0 } ?: error("keyword not found")
    input.indexOf(keyword).takeUnless { it<0 } ?: error("keyword not found")

    //예제
    val executionTime = measureTimeMillis {
        // 측정할 작업 코드
    }
    println("Execution Time = $executionTime ms")

    //난수생성
    val number = Random.nextInt(21)  // 숫자는 난수 발생 범위
    println(" = $number")
}

//block은 측정하고자 하는 코드
public inline fun measureTimeMillis(block: () -> Unit): Long {
    val start = System.currentTimeMillis()  //시작시간
    block()
    return System.currentTimeMillis() - start   //햔재시간-시작시간 = 실행에 걸린 시간
}

public inline fun measureNanoTime(block: () -> Unit): Long {
    val start = System.nanoTime()
    block()
    return System.nanoTime() - start
}