fun main(args: Array<String>) {
    chapter2_1()

}

private fun chapter2_1() {
    //함수 선언
    println( "sum1= ${sum1(3,4)}")
    println( "sum2= ${sum2(3,4)}")
    println( "Max is ${max(5,9)}")
    name("yeon")
    println( "sum1= ${sum1(3)}") //디폴트 지정   a->3 b->디폴트
    println( "sum1= ${sum1(b=3)}") //디폴트 지정    a->디폴트   b->3
    println( "normalVarargs_1") //가변 함수
    normalVarargs(1)
    println( "normalVarargs_4") //가변 함수
    normalVarargs(1,2,3,4)

    println( "===가변 함수로 실수의 평균 구하기===")
    avgFunc(1.1f,1.2f,1.3f)
    avgFunc()

    println(highFunc({ x, y -> x + y }, 10, 20)) // 람다식 함수를 인자로 넘김
    val result = highFunc2(1,3) {x,y -> x+y}     //순서를 마지막으로 해서 바깥으로 뺄 수 도 있음
}

fun name(s: String): Unit {     //Unit 생략 가능
    println( "My name is $s")
    return Unit     //여기도 생략
}

fun sum1(a: Int=2, b: Int =5):Int {       //default 지정
    return a+b
}
//sum1 간략
fun sum2(a: Int, b: Int) : Int = a+b    //리턴 자료형 추론 가능하기 때문에 생략 가능

//최대값 고르는 함수
fun max(a: Int, b: Int) :Int {
    return if(a>b) a else b
}

//가변 함수 varargs
fun normalVarargs(vararg a : Int){  //인자가 하나 이상이다.
    for(num in a){
        print(" $num")
    }
    println()
}

//하나 이상의 실수를 받아 모든 실수의 합의 평균을 구해 출력
fun avgFunc(vararg a : Float) {
    var tmp : Float =0f
    for(num in a){
        tmp +=num
    }
    println("result: $tmp, numbers.size: ${a.size}")

    var res : Float =if(tmp>0)(tmp/a.size) else 0f
    println("모든 실수의 평균은 ${res}")
}

fun highFunc(sum: (Int, Int) -> Int, a: Int, b: Int): Int = sum(a, b) // sum 매개변수는 함수
fun highFunc2(a: Int, b: Int ,sum: (Int, Int) -> Int): Int = sum(a, b) // sum 매개변수는 함수