package kotlin2.chapter1

/*

    -생성자
        -> 클래스를 통해 객체가 만들어질 때 기본적으로 호출되는 함수
        -> 객체 생성 시 필요한 값을 인자로 설정할 수 있게 한다.
        -> 생성자를 위해 특별한 함수인 constructor() 를 정의

    class 클래스명 constructor(필요한 매개변수){   //주 생성자의 위치
        ...
        constructor(필요한 매개변수들..){         //부 생성자의 위치
            //프로퍼티의 초기화
        }
        [constructor(필요한 매개변수들..){...}]   //추가 부 생성자
    {

    -주 생성자 : 클래스명과 함께 기술, 보통 constructor 키워드 생략 가능
    -부 생성자 : 클래스 본문에 기술, 하나 이상의 부 생성자를 정의 할 수 있다.
 */

class chapter1_2_1 {

    //프로퍼티
    var name: String = "noname"
    var wing : Int=2
    var beak : String= "short"

    //메서드
    fun fly(){
        println("Fly")
    }
}

class chapter1_2_2 {

    //프로퍼티
    var name: String
    var wing : Int=2
    var beak : String

    constructor(name: String , wing:Int, beak: String){     //부생성자
        this.name=name
        this.wing=wing
        this.beak=beak
    }

    //메서드
    fun fly(){
        println("Fly")
    }
}

//3.
class chapter1_2_3 constructor(_name:String,_wing:Int,_beak :String){     //constructor 생력하고 매개변수만 쓸 수 있음

    //프로퍼티
    var name: String = _name
    var wing : Int= _wing
    var beak : String= _beak

    //메서드
    fun fly(){
        println("Fly")
    }
}

//4.
class chapter1_2_4 constructor(var name:String,val wing:Int,var beak :String){  //프로퍼티와 생성자가 같이 생성된 (간단!)

    //메서드
    fun fly(){
        println("Fly")
    }
}

//5.
class chapter1_2_5 constructor(var name:String,val wing:Int,var beak :String){  //프로퍼티와 생성자가 같이 생성된 (간단!)

    //간단한 코드를 하는게 좋다.
    init {
        println("=======init start=======")
        name= name.capitalize()     //받은 문자를 대문자로 변경
        println("name :$name, wing : $wing, beak : $beak")
        println("========================")
    }

    //메서드
    fun fly(){
        println("Fly")
    }
}

//6.
class chapter1_2_6{

    //프로퍼티
    var name: String
    var wing : Int=2
    var beak : String

    constructor(name: String , wing:Int, beak: String){     //부생성자
        this.name=name
        this.wing=wing
        this.beak=beak
    }

    constructor(name: String ,  beak: String){     //부생성자
        this.name=name
        this.wing=2
        this.beak=beak
    }

    //메서드
    fun fly(){
        println("Fly")
    }
}

fun main(){

    //1.
    println("-----1-----")
    val coco1 = chapter1_2_1()
    coco1.name="coco"
    coco1.fly()

    //2.
    println("-----2-----")
    val coco2 = chapter1_2_2("coco",2,"long")
    coco2.fly()
    println(coco2.name)
    println(coco2.wing)
    println(coco2.beak)

    //3.
    println("-----3-----")
    val coco3 = chapter1_2_3("coco",2,"long")
    coco3.fly()
    println(coco3.name)
    println(coco3.wing)
    println(coco3.beak)

    //4.
    println("-----4-----")
    val coco4 = chapter1_2_4("coco",2,"long")
    coco4.fly()
    println(coco4.name)
    println(coco4.wing)
    println(coco4.beak)

    println("-----5-----")
    val coco5 = chapter1_2_5("coco",2,"long")
    coco5.fly()

    println("-----6-----")
    val coco6_1 = chapter1_2_6("coco",4,"long")
    val coco6_2 = chapter1_2_6("coco","long")

    println("coco6_1 : ${coco6_1.name},${coco6_1.wing},${coco6_1.beak}")
    println("coco6_2 : ${coco6_2.name},${coco6_2.wing},${coco6_2.beak}")


}