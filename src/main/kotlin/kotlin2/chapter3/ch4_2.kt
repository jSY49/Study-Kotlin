package kotlin2.chapter3

/*
<제네릭 함수 혹은 메서드>
    - 해당 함수나 메서드 앞쪽에 <T> 와 같이 지정
    - 자료형의 결정은 함수가 호출될 때 컴파일러가 자료형 추론
    - 이 자료형은 반환 자료형과 매개변수 자료형에 사용할 수 있다.


fun <T> genericFunc(arg: T): T? { ... } // 매개변수와 반환 자료형에 형식 매개변수 T가 사용됨
fun <K, V> put(key: K, value: V): Unit { ... } // 형식 매개변수가 여러 개인 경우

<자료형 제한하기>: 형식 매개변수를 특정한 자료형으로 제한
 - 자료형의 사용범위를 좁히기 위해 자료형을 제한.
 - 자바에서는 extends 나 super 를 사용해 자료형을 제한
 - 코틀린은 콜론 과 자료형을 기입하면 형ㅅ기 매개변수 T의 자료형이 기입한 자료형으로 제한됨

<상, 하위 형식의 가변성>
 가변성 : 형식 매개변수가 클래스 계층에 어떤 영향을 미치는지 정의
        => 형식A의 값이 필요한 모든 장소에 gudtlr ㅠ의 값을 넣어도 아무 문제가 없다면 B는 A의 하위형식
        =>Int 는 Number의 하위형식

*/

//prac1.
fun <T> find(a:Array<T>,Target : T):Int{
    for (i in a.indices){
        if (a[i]==Target)   return i
    }
    return -1
}

//prac2.
/*
fun <T> add(a:T,b:T):T{
    return a+b  //자료형을 아직 결정할 수 없음
}
*/
//위 방법 말고 아래 방법으로
fun <T> add(a:T,b:T,op:(T,T)->T):T{
    return op(a,b)
}

//prac3.
class Calc<T:Number>{       //class의 형식 매개변수 제한
    fun plus(arg1 :T, arg2 :T):Double{
        return arg1.toDouble()+arg2.toDouble()
    }
}

fun main(){
    //prac1.
    val arr1 :Array<String> = arrayOf("Apple","banana","Cherry","Durian")
    val arr2 : Array<Int> = arrayOf(1,2,3,4)

    println("arr.indicies ${arr1.indices}") //indices는 배열의 유효범위 반환
    println(find<String>(arr1,"Cherry"))
    println(find(arr2,3))

    println("-------------")
    //prac2.
    val result=add(2,3,{a,b->a+b})
    println(result)

    println("-------------")
    //prac3.
    val calc = Calc<Int>()
    println(calc.plus(10,20))
    val calc2 = Calc<Long>()
    println(calc2.plus(2L,5L))
    val calc3 = Calc<Double>()
    println(calc3.plus(3.5,2.5))
    //val calc4 = Calc<String>()    //제한된 자료형을 오류
}