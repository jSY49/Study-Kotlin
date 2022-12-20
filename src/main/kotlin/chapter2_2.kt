fun main(args: Array<String>) {
    chapter2_2()
}

private fun chapter2_2() {

   // lambdaBasic()
   // lambda_With_HigherOrderFunction()
   // lambda_With_HigherOrderFunction2()
    VariousFunction()

}


fun lambdaBasic() {
    var result :Int
    val multi ={x:Int,y:Int->x*y}   //일반 변수에 람다식 할당
    /*
    val multi: (Int, Int) -> Int = {x: Int, y: Int -> x * y} // 생략되지 않은 전체 표현
    val multi = {x: Int, y: Int -> x * y}  // 선언 자료형 생략
    val multi: (Int, Int) -> Int = {x, y -> x * y} // 람다식 매개변수 자료형의 생략
    val multi: (Int, Int) -> Int = {x, y ->
     println("a: $a, b: $b")    //중간 식 삽입 가능
     x * y                      //반환 설정을 했기때문에 반환 진행, 원하지 않으면 반환값 Unit
     }
     */
    result=multi(10,20)             //람다식이 할당된 변수는 함수처럼 사용 가능
    println(result)

    //반환값이 없는 경우
    val greet: () -> Unit = {println("Hello World")}        //()->Unit 생략 가능 (추론가능)
    greet()
    //매개변수 하나인 경우
    val square:(Int)->Int={x->x*x}                          //(Int)->Int 생략하려면 x의 자료형 명시
    println(square(10))

    //람다식 안에 람다식
    val nestedLabmda :()->()->Unit={ { println("nested") }} //:()->()->Unit 생략 가능 (추론가능)
    nestedLabmda()()
}
fun lambda_With_HigherOrderFunction() {
    val result= sum(10,20)
    val result2=mul(sum(10,5),10)
    val result3=funcFunc(2,3)

    println("result: $result, result2: $result2, result3: $result3")

    var res: Int
    res =highOrder({x,y->x+y},10,20)
    println(res)

    val res2 =callByValue(lambda())         //람다식 함수를 호출(즉시 함수가 수행된 후 값을 전달)
    println("res2= $res2")

    val res3 =callByName(otherLambda)       //람다식 이름으로 호출(해당 함수가 사용될때 호출)
    println("res3= $res3")
    
    //다른 함수의 참조에 의한 호출
    //람다식이 아닌 함수를 인자로 넘겨주는 방법
    println("다른 함수의 참조에 의한 호출")
    val res4=funcParam(3,2,::sum3)   //1.인자와 반환값이 있는 함수
    println("res4= $res4")
    hello(::text)                         //2.인자가 없는 함수
    val likeLambda=::sum3                  //3.일반 변수에 값처럼 할당
    println("likeLambda= $likeLambda(6,6)")
}
fun lambda_With_HigherOrderFunction2() {
    //매개변수 없는 람다식
    noParam({"hello world"})
    noParam{"hello world"}      //위 결과 동일, 함수의 마지막 매개변수가 람다식으로 구성된 경우 소괄호 생략 가능

    //매개변수가 하나 있는 람다식 함수
    oneParam({a-> "Helllo World! $a"})
    oneParam{a-> "Helllo World! $a"}    //위 결과 동일, 소괄호 생략 가능
    oneParam{"Helllo World! $it"}       //위 결과 동일, it으로 대체 가능

    //매개변수가 두개 있는 람다식
    moreParam { a, b -> "Hello World $a $b" } // 매개변수명 생략 불가
    moreParam { _, b -> "Hello World $b" } // 사용하지 않는 매개변수명 언더바 사용 하여 생략 가능
    
    //일반매개변수롸 람다식 매개변수를 같이 사용
    withArgs("Arg1", "Arg2", { a, b -> "Helo World! $a $b" }) //인자와 함께 사용
    withArgs("Arg1", "Arg2") { a, b -> "Helo World! $a $b" }  //마지막 인자가 람다식인 경우 바깥으로 분리 가능

    //두개의 람다식을 가진 함수
    twoLambda({a,b->"First $a $b"},{"Second -> $it"})
    twoLambda({a,b->"First $a $b"}){"Second -> $it"}    //위와 동일
}
fun VariousFunction() {
    //익명 함수 : 일반함수 이지만 이름이 없는   fun(x:Int, y:Int):Int=x+y
    val add: (Int, Int)-> Int=fun(x:Int, y:Int):Int=x+y
    val res = add(10,2)
    println("res= $res")

    //인라인 함수 : 함수가 호출되는 곳에 내용을 복사하여 함수의 분기 없이 처리하여 성능 증가
    shortFunc(3) { println("First call: $it") }
    shortFunc(5) { println("Second call: $it") }
    //인라인 단점 : 코드가 본문에 복사되므로 함수에 내용이 많으면 코드가 늘어남
    //일부 람다식 함수를 인라인 되지 않게 noinline
    shortFunc2(3) { println("a: $it") }
    shortFunc2(5) { println("a: $it") }

    //비지역반환
//    shortFunc(3) {
//        println("First call: $it")
//        return // 이렇게 리턴하면 shortFunc에서 out 이후의 코드가 실행x
//                //비지역 반환을 막기 위해 shortFunc 인자 out 앞에crossinline  추가
//    }

    //확장함수 : 클래스의 멤버함수를 외부에서 더 추가할 수 있음
    //fun 확장대상.함수명(매개변수,...):반환값{ 반환}
    val source = "Hello World!"
    val target = "Kotlin"
    println("확장 : "+source.getLongString(target))

    //중위함수 : 연산자를 구현할 수 있는 함수
    //클래스의 멤버 호출 시 사용하는 점(.)을 생략하고 함수 이름 뒤에 소괄호를 붙이지 않아 직관적인 이름을 사용할 수 있는 표현법
    // 중위 표현법
    val multi = 3 multiply 10   //일반 표현법 : 3.multiply(10)
    println("multi =$multi")

    //재귀함수
    //-무한 호출에 빠지지 않도록 탈출조건을 만들어 둔다.
    //-스택 영역을 이용하므로 호출 횟수를 무리하게 많이 지정해 연산하지 않는다.
    //-코드를 복잡하지 않게 한다.
    val number = 5
    println("Factorial: $number -> ${factorial(number)}")
}

//VariousFunction
inline fun shortFunc(a: Int, out: (Int) -> Unit) {
    println("Before calling out()")
    out(a)
    println("After calling out()")
}
inline fun shortFunc2(a: Int, noinline out: (Int) -> Unit) {
    println("Hello")
    out(a)
    println("Good Bye")
}
// String을 확장해 getLongString 추가
fun String.getLongString(target: String): String =
    if (this.length < target.length) this  else target

// Int를 확장해서 multiply() 함수가 하나 더 추가되었음
infix fun Int.multiply(x: Int): Int {  // infix로 선언되므로 중위 함수
    return this * x
}
//꼬리재귀함수 tail recursive function    ->스택 사용 x ,스택 오버플로 현상을 해결
/*
//일반 재귀함수
fun factorial(n: Int): Long {
    return if (n == 1) n.toLong() else factorial(n-1)
}
 */
tailrec fun factorial(n: Int, run: Int = 1): Long {
    return if (n == 1) run.toLong() else factorial(n-1, run*n)
}


//lambda_With_HigherOrderFunction2
fun noParam(out: () -> String) = println(out())
fun oneParam(out: (String)->String) = println(out("OneParam"))
fun moreParam(out:(String,String)->String ) = println(out("oneParam","twoParam"))
fun withArgs(a:String, b:String, out:(String, String )-> String)= println(out(a,b))
fun twoLambda(first:(String,String)->String,Second:(String )->String){
    println(first("one","two"))
    println(Second("one"))
}

//lambda_With_HigherOrderFunction
fun callByValue(b:Boolean): Boolean {      //일반 변수 자료형으로 선언된 매개변수
    println("callByValue Function")
    return b
}

val lambda: ()->Boolean={           //람다 표현식이 두 줄 
    println("lambda Fuction")
    true                    //마지막 표현식이 반환값
}

fun callByName(b:()->Boolean): Boolean {      //람다식 함수 자료형으로 선언된 매개변수(람다식 자체가 매개변수에 복사)
    println("callByName Function")
    return b()                                 //여기서 otherLambda호출
}
val otherLambda: ()->Boolean={
    println("otherLambda Fuction")
    true
}

fun sum3(a: Int, b: Int) = a + b
fun text(a: String, b: String) = "Hi! $a $b"
    fun funcParam(a: Int, b: Int, c: (Int, Int) -> Int): Int {
    return c(a, b)
}
fun hello(body: (String, String) -> String): Unit {
    println(body("Hello", "World"))
}


fun highOrder(sum1:(Int,Int)->Int, a: Int, b: Int): Int {        //인자가 총 3개 (람다식, a,b)
    return sum1(a,b)        //a와 b의 값이 sum1이라는 람다식에 전달되에 리턴
}

fun sum(a: Int,b:Int):Int=a+b   //return a+b 를 간략히
fun mul(a: Int, b: Int):Int=a*b
fun funcFunc(a:Int, b:Int)=sum(a,b) //함수의 반환값이 함수


