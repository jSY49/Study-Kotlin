package kotlin2.chapter3

import java.lang.StringBuilder

/*
    - 문자열은 불변 값으로 생성.
        ->  val hello :String = "Hello World!"
            val hello2 : String = "Hello World!"    //hello와 hello2는 같은 "Hello World!"가리키는 변수이다.
    -> 그래서 hello[0]='k'가 오류

 */

fun main(){
    val hello :String = "Hello World!"
    println(hello[0])
    //hello[0]='k'  //Error

    var s = "abcdefg"
    s="xyz" //새로운 메모리 공간이 생성된다.
    println(s)  //기존의 값 "abcdefg"는 쓰레기가 되면서 나중에 가비지컬렉터가 수집한다.

    //문자열 추출
    //String.subString(인덱스 범위 지정) :String
    //CharSequence.subSequence(인덱스 범위 지정) :CharSequence
    var str1="abcdef"
    str1=str1.substring(0..1)+"x"+str1.substring(3..str1.length-1)  //ab를 추출하고 x를 덧붙이고 다시 def를 추출
    println("str1= $str1")

    //문자열 비교
    //a.compareTo(b)
    //같으면 0, a가 b보다 작으면 양수, 그렇지 않으면 음수 반환
    var str2 ="Hello Kotlin"
    var str3 ="hello kotlin"
    println("str2 vs str3 = ${str2.compareTo(str3)}")
    println("str2 vs str3 = ${str2.compareTo(str3,true)}")//대소문자 무시

    //StringBuilder의 이용
    //문자열이 사용할 공산을 좀 더 크게 잡아 사용함,
    //간단한 요소 변경이 있을 경우 용이
    //단, 기존의 문자열 처리가 좀 느리고, 만일 단어를 변경하지 않는 경우 불필요한 메모리 낭비
    //문자열이 자주 변경되는 경우에 사용
    var str4 = StringBuilder("Hello")
    str4[2]='x' //허용되지 않았던 요소의 변경이 가능하다. 큰따옴표는 안된다.
    println("str4= $str4")
    str4.append("World")    //문자열 붙이기
    println("str4= $str4")
    str4.insert(5,"Added") //특정 인덱스 부터 붙이기
    println("str4= $str4")
    str4.delete(5,10)   //인덱스 5~10전까지삭제
    println("str4= $str4")


    //소문자 대문자 변경
    //toLowerCase()
    //toUpperCase()

    //특정 문자 단위로 다르기
    var deli = "Welcome to Kotlin"
    val sp=deli.split(" ")
    println(sp)     //결과 : [Welcome, to, Kotlin]

    //앞뒤 공백 제거
    //trim()

}