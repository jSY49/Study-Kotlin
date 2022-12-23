package kotlin2.chapter1

/*
    -다형성(Polymorphism)
        -> 같은 이름을 사용하지만 구현 내용이 다르거나 매개변수가 달라서 하나의 이름으로 다양한 기능을 수행할 수 있는 개념
        -> static Polymorphism  : 컴파일 타임에 결정되는 다형성. 단순하게 보면 메서드 오버로딩 사용할 때
        -> Dynamic Polymorphism : 런타임 다형성. 동적으로 구성되는 오버라이딩된 메서드를 사용할 때

    -오버라이딩
        -> 기능을 완전히 다르게 바꾸어 재설계
        -> 예시> 누르다 -> 행위 -> push()
        -> push()는 확인 혹은 취소 용도로 서로 다른 기능을 수행할 수 있음.
    -오버로딩
        -> 기능은 같지만 인자를 다르게 하여 여러 경우를 처리
        -> 예시> 출력한다 -> 행위 -> print()
        -> print(123) print("hello") 인자는 다르지만 출력의 기능은 동일함
        fun add(a:Int,b:Int):Int = a+b
        fun add(a:Double,b:Double):Double = a+b
        fun add(a:Int,b:Int,c:Int):Int = a+b+c
 */

open class bird (var name:String,val wing:Int,var beak :String){
    open fun fly(){
        println("$name Fly")
    }
}

class lark(name :String, wing : Int, beak: String ):bird(name,wing,beak){

    override fun fly(){     //override 하고 싶으면 상위클래스 동일 함수에 open 해줘야 함
        super.fly()         //상위 클래스의 fly를 먼저 수행
        println("$name Fast Fly ")
    }

    fun singHitone(){
        println("Sing Hitone")
    }
}

//반드시 부모 클래스가 가지는 생성자의 내용이 주 혹은 부 생성자에 정의 되어야 함.
class parrot : bird{

    var language:String
    constructor(name :String, wing : Int, beak: String ,language:String ):super(name,wing,beak){
        this.language=language
    }

    override fun fly(){     //override 하고 싶으면 상위클래스 동일 함수에 open 해줘야 함
        println("$name Slow Fly ")
    }

    fun speak(){
        println("Speak : $language")
    }
}

fun main(){
    val lark =lark("mylark",2,"short")
    val parrot = parrot("myParrot",2,"long","English")

    println("lark- ${lark.name}/${lark.wing}/${lark.beak}")
    println("parrot- ${parrot.name}/${parrot.wing}/${parrot.beak}/${parrot.language}")

    lark.singHitone()
    lark.fly()
    parrot.speak()
    parrot.fly()

    println()
    println()

    val lark2 :bird=lark("mylark",2,"short")
    val parrot2 :bird= parrot("myParrot",2,"long","English")

    println("lark- ${lark2.name}/${lark2.wing}/${lark2.beak}")
    println("parrot- ${parrot2.name}/${parrot2.wing}/${parrot2.beak}/")

    //각 타입이 bird이기 떄문에 lark나 parrot클래스의 함수나 변수 사용 x
//    lark2.singHitone()
    lark2.fly()             //하지만 상위 fly가 아닌 하위 fly가 호출되어 나옴 --> 다향성의 가장 중요한 특징
//    parrot2.speak()
    parrot2.fly()

}