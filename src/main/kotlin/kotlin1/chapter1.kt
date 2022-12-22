fun main(args: Array<String>) {
    chapter1()
}

fun chapter1() {
    //불변 val(value) 가변 var(variable)
    val userName:String ="kilDong"  // :String이 없다면 자료형을 추론하여 String 으로 결정
    //val userName2 //이러면 자료형 추론 안되서 사용 불가
    println("username: $userName")

    //자료형
    val exp01 =123
    val exp02 =123L         //long으로 추론
    val exp03 =0x3f         //0x = 16진 표기가 사용된 int 형으로 추론
    val exp04 =0b00001011   //0b = 2진 표기가 사용된 int 형으로 추론

    val exp05 : Byte =123   //명시적으로 자료형 지정
    val exp06 =123          //명시적으로 지정하지 않으면 short형 범위의 갑도 int형으로 추론

    val exp07 : UInt =123u  //부호없는(음수없는) 정수형 자료형 /byte,short,int,long모두
    val number =1_000_000   //큰 수를 쉽게 읽기 위해 언더스코어 포함하여 사용

    val exp08 =3.14         //double형으로 추론
    val exp09 =3.14F        //식별자 F에 의해 Float형으로 추론
    
    //3.14 x 10의16제곱 => 3.14E+16 (+혹은 - 부호 사용 ,+는 생략가능)
    val exp10 =3.14E-2  //3.14 x 10의-2제곱 = 0.0314
    val exp11 =3.14e2  //3.14 x 10의2제곱 = 314

    var num : Double =0.1
    for(x in 0..999){
        num+=0.1;             
    }                       //100이 나와야 함
    println("num : $num")   //결과 : num : 100.09999999999859 (지수부와 가수부의 제한 때문)

    //줄 복사 ctlr + D
    //다중선택  alt shift 더블클릭
    println("Byte : ${Byte.MIN_VALUE}~${Byte.MAX_VALUE}")  //Byte 의 최대최소 가져와짐
    println("Int : ${Int.MIN_VALUE}~${Int.MAX_VALUE}")
    println("Short : ${Short.MIN_VALUE}~${Short.MAX_VALUE}")
    println("Long : ${Long.MIN_VALUE}~${Long.MAX_VALUE}")
    println("Float : ${Float.MIN_VALUE}~${Float.MAX_VALUE}")
    println("Double : ${Double.MIN_VALUE}~${Double.MAX_VALUE}")

    //스택에 변수 저장 힙에 값 저장
    //스택에 있는 변수 str1과 str3는 (힙에서 )같은 위치에 있는 hello라는 값을 참조 -> 참조 주소 같음
    var str1 : String = "hello"
    var str2 ="World"
    var str3 = "hello"
    //==는 값만 비교 ===는 참조(주소)까지 비교  (자바는 ==만)
    println("str1=== str2 : ${str1===str2}")
    println("str1=== str3 : ${str1===str3}")

    var a =1
    var s1="a is $a"
    var s2="a is ${a+2}"
    println("s1= \"${s1}\"")
    println("s2= \"${s2}\"")

    //null!
    //코틀린의 변수선언은 기본적으로 null을 허용하지 않는다.
    val a1 : Int = 30    //null불가
    var a2 : String? = null    //null가능
    println("a2 = $a2, length = ${a2?.length}") //?.은 세이프콜 : null이라면 수행x

    val len =if(a2 !=null) a2.length else -1    //이부분을 elvis 로 표현 할 수 있음
    val len2 = a2?.length ?: -1    //elvis
    println("len= $len")

    val a3 : Int =1
    val b : Double = a3.toDouble()  //변환메서드 사용
    val res = 1L +3 //Long + Int = Long

    val a4 : Int =128  //기본형
    val b1 : Int =128
    println("a4== b1 : ${a4==b1}")  //true
    println("a4=== b1 : ${a4===b1}")//true
    val a5 : Int? =a4
    val b2 : Int? =a4  //객체    a4의 값(128)은 동적 공간(힙)에 있고 b2 에는 참조 주소가 들어가 있음
    println("a5== b2 : ${a5==b2}")  //true
    println("a5=== b2 : ${a5===b2}")//false

    //스마트 케스트
    var test : Number =12.2     //Number는 숫자 자료형의 상위
    println("$test")
    test=12
    println("$test")
    test=120L
    println("$test")
    test +=12.0f
    println("$test")
    /*
    결과
    12.2
    12
    120
    132.0
    */

    //is 키워드    ==> 자료형을 알 수 있음
    val num1 = 256
    if(num1 is Int){    
        print("num1 is $num1")
    }else if(num1 !is Int){
        print("Not a Int")
    }

    //Any : 자료형이 정해지지 않은, 모든 자료형의 최상위 클래스
    var any1 : Any =1
    any1 =20L
    println("any1 : $any1 type: ${any1.javaClass}")     //any1의 자바 기본형 출력

    bit()
}

fun bit() {
    /*
    비트 연산을 위한 비트 메서드
    비트 연산을 위한 메서드를 알아보겠습니다. 이런 메서드들은 메서드처럼 사용해도 되지만 연산자처럼 사용할 수도 있습니다. 예를 들어 비트 전체를 왼쪽으로 이동시키는 shl 메서드는 4.shl(1) 또는 4 shl 1과 같은 방법으로 사용할 수 있습니다.
    4 shl 1과 같이 멤버에 접근하는 점(.) 연산자와 소괄호를 생략하는 표현식을 중위 표현식이라고 부릅니다.


    4.shl(bits)     4를 표현하는 비트를 bits만큼 왼쪽으로 이동(부호 있음)

    7.shr(bits)     7을 표현하는 비트를 bits만큼 오른쪽으로 이동(부호 있음)

    12.ushr(bits)   12를 표현하는 비트를 bits만큼 오른쪽 이동(부호 없음)

    9.and(bits)     9를 표현하는 비트와 bits를 표현하는 비트로 논리곱 연산

    4.or(bits)      4를 표현하는 비트와 bits를 표현하는 비트로 논리합 연산

    24.xor(bits)    23를 표현하는 비트와 bits를 표현하는 비트의 배타적 연산

    78.inv          78을 표현하는 비트를 모두 뒤집음
    */
}
