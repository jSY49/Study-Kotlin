package kotlin2.chapter2

/*
    연산자 오버로딩
        - 연산자에 여러가지 다른 의미의 작동을 부여
        - 코틀린에서는 특정 연산자의 역할을 함수로 정의
        - operator 키워드를 사용해 정의


    a+b => a.plus(b)
    a-b => a.minus(b)
    a*b => a.times(b)
    a/b => a.div(b)
    a%b => a.rem(b) //a.mod(b)는 지원중단
    a..b => a.rangeTo(b)

    //게터와 세터를 다루기 위한 대괄호 연산자 제공
    arr[i]      =>  arr.get(i)
    arr[i,j]    =>  arr.get(i,j)
    arr[i_1,...,i_n]    =>  arr.get(i_1,...,i_n)
    arr[i] = b  =>  arr.set(i,b)
    arr[i,j] = b  =>  arr.set(i,j,b)
    arr[i_1,...,i_n] = b  =>  arr.set(i_1,...,i_n,b)

    //단일연산자
    +a	     =>  unaryPlus
    -a	     =>  unaryMinus
    !a	     =>  not
    ++a, a++ =>  inc
    --a, a-- =>	 dec

    //포함범위 연산자
    a in b  => b.contains(a)
    a !n b  => !b.contains(a)

    //할당 연산자
    a+=b    =>  a.plusAssign(b)
    a-=b    =>  a.minusAssign(b)
    a*=b    =>  a.timesAssign(b)
    a/=b    =>  a.divAssign(b)
    a%=b    =>  a.remAssign(b)

    //동등성 연산자
    a==b    a?.equals(b)?:(b===null)
    a!=b    !(a?.equals(b)?:(b===null))

    //비교 연산자
    a>b     a.compareTo(b)>0
    a<b     a.compareTo(b)<0
    a>=b    a.compareTo(b)>=0
    a>=b    a.compareTo(b)<=0
 */


// 표준라이브러리의 Primitives.kt 파일의 일부
/*
// + operator for basic types
operator fun plus(other: Byte): Int
operator fun plus(other: Short): Int
operator fun plus(other: Int): Int
operator fun plus(other: Long): Long
operator fun plus(other: Float): Float
operator fun plus(other: Double): Double

// for string concatenation
operator fun String?.plus(other: Any?): String

*/
class Point(var x:Int=0,var y:Int =10){
    //plus함수의 연산자 오버로딩
    operator fun plus(p:Point):Point{
        return Point(x+p.x,y+p.y)
    }
    operator  fun dec()=Point(--x,--y)
}


//호출연산자
class  Manager{
    operator fun invoke(value: String){
        println(value)
    }
}



fun main(){
    val a =5
    val b=10
    print(a.plus(b))

    println("-----------")
    val p1=Point(3,-8)
    val p2=Point(2,9)
    var point=Point()
    point=p1+p2
    println("point = (${point.x},${point.y})")

    --point
    println("point = (${point.x},${point.y})")



    //호출연산자
    println("===========")
    val manager=Manager()
    manager("Do something for me!") //manager.invoke("---")의 형태로 호출되며 invloke가 생략되었다.

}
