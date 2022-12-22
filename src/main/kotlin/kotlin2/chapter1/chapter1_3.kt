package kotlin2.chapter1
/*
    -상속
        -> 자식 클래스를 만들 때 상위 클래스의 속성과 기능을 물려받아 계승
        -> 상위 클래스의 프로퍼티와 메서드가 자식에 적용

    open class 기반클래스명 {             //open으로 파생 가능
    ...
    }
    class 파생클래스명 : 기반클래스명(){    //파생 불가, 최종 클래스
    ...
    }
 */


open class Bird (var name:String,val wing:Int,var beak :String){
    fun fly(){
        println("$name Fly")
    }
}

class Lark(name :String, wing : Int, beak: String ):Bird(name,wing,beak){

    fun singHitone(){
        println("Sing Hitone")
    }
}

//반드시 부모 클래스가 가지는 생성자의 내용이 주 혹은 부 생성자에 정의 되어야 함.
class Parrot : Bird{

    var language:String
    constructor(name :String, wing : Int, beak: String ,language:String ):super(name,wing,beak){
        this.language=language
    }

    fun speak(){
        println("Speak : $language")
    }
}

fun main(){
    val lark =Lark("mylark",2,"short")
    val parrot = Parrot("myParrot",2,"long","English")

    println("lark- ${lark.name}/${lark.wing}/${lark.beak}")
    println("parrot- ${parrot.name}/${parrot.wing}/${parrot.beak}/${parrot.language}")

    lark.singHitone()
    lark.fly()
    parrot.speak()
    parrot.fly()
}