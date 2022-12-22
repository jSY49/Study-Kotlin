fun main(){
    //prac_if()
    //prac_elseif()
    //prac_elseif_range() //prac_elseif()를 range로

    //prac_when()

    //prac_for()
    //prac_while()
    //prac_doWhile()

    //prac_return()
    //prac_breakNcontinue()

    //prac_Exception()
    prac_throw()


}

fun prac_if() {
    val a =12
    val b= 7

    val max=if(a>b){
        println("a 선택")
        a                   //마지막 식이 반환되어 할당됨
    }else{
        println("b 선택")
        b
    }
    println("max = $max")
    
}

fun prac_elseif() {
    print("Enter the score : ")
    val score= readLine()!!.toDouble()  //!! 는 nonNull
    var grade: Char ='F'

    if(score>=90.0){
        grade='A'
    }else if(score>=80&& score<90){
        grade='B'
    }else if(score>=70&& score<80){
        grade='C'
    }else if(score>=60&& score<70){
        grade='D'
    }else{
        grade='F'
    }

    println("score : $score, grade: $grade")
}

fun prac_elseif_range() {
    print("Enter the score : ")
    val score= readLine()!!.toDouble()  //!! 는 nonNull
    var grade: Char ='F'

    if(score>=90.0){
        grade='A'
    }else if(score in 80.0..89.9){
        grade='B'
    }else if(score in 70.0..79.9){
        grade='C'
    }else if(score in 60.0..69.9){
        grade='D'
    }else{
        grade='F'
    }

    println("score : $score, grade: $grade")
}

fun prac_when() {
    print("Enter the score : ")
    val score= readLine()!!.toDouble()  //!! 는 nonNull
    var grade: Char ='F'

    
    //switch 와 유사
    /*
    //기본 형식
    when (val) {
        "a" -> println("a")
        "b" -> println("b")
        "c","d" -> println("other")
        paresInt(s) -> println("일치함")   //함수의 반환값 사용도 가능
        else -> println("null")
    }
    */
    
    when(score){
        in 90.0..100.0 -> grade='A'
        in 80.0..89.9 -> grade='B'
        in 70.0..79.9 -> grade='C'
        in 60.0..69.9 -> grade='D'
        else -> grade='F'
    }
    println("score : $score, grade: $grade")
    
    //혹은, 인수 없는 when 사용
    when{
        score >=90.0 ->grade='A'    //인자 있는 when 과 다르게 조건식을 구성할 수 있음
        score in 80.0..89.9 ->grade ='B'
        score in 70.0..79.9 ->grade ='C'
        score in 60.0..69.9 ->grade ='D'
        score<60.0 ->grade='F'
    }
    println("score : $score, grade: $grade")
}

fun  prac_for(){
    /*
        for(요소 변수 in 컬럭센 혹은 범위){
            반복할 본문
        }
    */
    println("기본반복")
    for(x in 1..5){
        println("x = $x")
    }

    var sum=0
    for(x in 1..10) sum+=x
    println("sum =  $sum")

    //하행반복
    println("하행반복")
    for(i in 5 downTo 1)        //i in 5..1 은 안되용
        println("i = $i")

    //필요 단계 반복
    println("필요 단계 반복 step!")
    for(x in 1..10 step 2)
        println("X = $x")

    //단계, 하행 혼합
    println("단계, 하행 혼합")
    for(x in 10 downTo 1 step 2)
        println("X = $x")


}

fun prac_while(){
    /*
    while(조건식){
        본문
        ...
    }
     */
    print("Enter the number : ")
    var number = readLine()!!.toInt()
    var factorial :Long=1

    while (number>0){
        factorial*=number
        --number
    }
    println("Factorial: $factorial")

}

fun prac_doWhile(){
    /*
    do{
    본문
    }while(조건식)
     */

    do{
        print("Enter the number : ")
        val input = readLine()!!.toInt()
        for(i in 0..(input-1)){     //i int 0 until input 해도됨 input 직전의 수까지 반복
            for(j in 0..(input-1)){
                print((i+j)%input+1)
            }
            println()
        }

    }while (input!=0)
}

fun prac_return(){
    //람다식에서 라벨과 함께 return 사용하기
    println("람다식에서 라벨과 함께 return 사용하기")
    retFunc()
    //암묵적 라벨 사용
    println("암묵적 라벨 사용")
    retFunc2()
    //익명 함수의 사용
    println("익명 함수의 사용")
    retFunc3()

}

fun prac_breakNcontinue(){
    //Basic break
    println("<Basic Break>")
    for(i in 1..5){
        if(i==3)    break
        print(i)
    }
    println()
    println("outside")

    //Basic continue
    println("<Basic continue>")
    for(i in 1..5){
        if(i==3)    continue
        print(i)
    }
    println()
    println("outside")

    //break with Label
    println("<break with Label>")
    brk@for(i in 1..5){
        for(j in 1..4){
            if(i==3)    break@brk
            println("i:$i, j:$j")
        }
        println("after for j")
    }
    println("after for i")

    //continue with Label
    println("<continue with Label>")
    brk@for(i in 1..5){
        for(j in 1..4){
            if(i==3)    continue@brk
            println("i:$i, j:$j")
        }
        println("after for j")
    }
    println("after for i")

}

fun prac_Exception(){
    val a=6
    val b=0
    val c:Int

    try {
        c=a/b
    }catch (e:Exception){
        println("Exception : $e")
        println("예외시 수행되는 Exception")
    }finally {
        println("꼭 수행되어야 하는 finally")
    }
}
fun prac_throw(){
    var amount= 600

    try {
        amount-=100
        checkAmount(amount)
    }catch (e:Exception){
        println(e.message)
    }finally {
        println("amount : $amount")
    }
}

//return, label 연습
inline fun inlineLambda(a: Int, b:Int, out: (Int, Int) -> Unit)=out(a,b)
fun retFunc(){
    /*
    //라벨 사용
    람다식 함수명 라벨이름@{
    ...
    return@라벨이름
    }
    //@라벨이름을 만나면 라벨이름@이 지정된 함수 밖으로 빠져나옴
     */

    println("Start of retFunc")
    inlineLambda(13,3) lit@{a,b->        //함수 호출 인자에 람다식마지막에 있기때문에 괄호 밖으로 빼줄 수 있음
        val res=a+b
        if(res>10) return@lit               //여기서 리턴되면 그 뒤에 코드는 더이상 실행되지 않음.
                                            //람다식 함수 외부의 함수까지 빠져 나가 비지역 반환이 일어난다.
        println("res= $res")
    }
    println("End of retFunc")
}
fun retFunc2(){
    /*
    //라벨 사용
    람다식 함수명 라벨이름@{
    ...
    return@라벨이름
    }
    //@라벨이름을 만나면 라벨이름@이 지정된 함수 밖으로 빠져나옴
     */

    println("Start of retFunc")
    inlineLambda(13,3) {a,b->        //함수 호출 인자에 람다식마지막에 있기때문에 괄호 밖으로 빼줄 수 있음
        val res=a+b
        if(res>10) return@inlineLambda               //여기서 리턴되면 그 뒤에 코드는 더이상 실행되지 않음.
        println("res= $res")
    }
    println("End of retFunc")
}
fun retFunc3(){
    //익명 함수는 비지역 반환x
    println("Start of retFunc")
    inlineLambda(13,3 ,fun(a,b){
        val res=a+b
        if(res>10) return             //여기서 리턴되면 그 뒤에 코드는 더이상 실행되지 않음.
        println("res= $res")
    })
    println("End of retFunc")
}

//throw연습
fun checkAmount(amount: Int){
    /*
    //예외 발생 시키기
    throw Exception(message: String)
    */
    if(amount<1000)
        throw Exception("잔고가 $amount 으로 1000 이하 입니다.")
}