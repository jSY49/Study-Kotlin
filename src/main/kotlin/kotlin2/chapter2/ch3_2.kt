package kotlin2.chapter2

/*
    interface
        - 일종의 현실세계의 계약서
        - 계약서에 작성된 구체적인 내용을 반드시 실행
        - 계약서 자체로는 실행될 수 없음

        -다른 언어와 다르게 기본적인 내용이 포함될 수 있다.
        -선언하려면 interface 키워드를 사용해 정의
        -상속한 하위 클래스에서는 override를 사용해 해당 메서드를 구현

        interface 인터페이스명 [: 인터페이스명...] {
            추상 프로퍼티 선언
            추상메서드 선언

            [일반 메서드 선언 {...}]
        }

    *추상클래스와 다른점
        -클래스가 아니므로 다양한 인터페이스로부터 클래스 구현 가능(다중상속)
        -추상 클래스와는 다르게 강한 연관을 가지지 않는다.

 */

//pr1.
//interface는 abstract 키워드가 없어도 선언되는 프로퍼티나 메서드는 추상이됨
interface Pet {
    var category: String // abstract 키워드가 없어도 기본은 추상 프로퍼티

    //인터페이스에서는 프로퍼티에 값을 저장할 수 없지만 val로 선언된 프로퍼티는 게터를 통해 필요한 내용을 구현할 수 있다.
    val msgTag:String
        get()="I'm your lovely pet!"

    val species:String

    fun feeding() // 마찬가지로 추상 메서드
    fun patting() { // 일반 메서드: 구현부를 포함하면 일반적인 메서드로 기본이 됨
        println("Keep patting!") // 구현부
    }
}

class Cat(override var category: String):Pet{
    override val species: String
        get() = TODO("Not yet implemented")

    override fun feeding() {
        println("Feed the cat a tuna can!")
    }

}

//===========================================
//pr2.
open class Animal(val name :String)

class Dog (name :String,override var category :String):Animal(name),Pet{

    override var species :String ="dog"

    override fun feeding() {
        println("Feed the dog a bone")
    }
}
class Cat2 (name :String,override var category :String):Animal(name),Pet{

    override var species :String ="cat"

    override fun feeding() {
        println("Feed the cat a fish")
    }
}

class Master{
    //동물 늘어날때마다 써주긴 번거로우니까
    /*
    //각 반려동물 종류에 따라 오버로딩됨
    fun playWithPet(dog:Dog){
        println("Enjoy with my dog")
    }
    fun playWithPet(cat:Cat){
        println("Enjoy with my cat")
    }
    */

    fun playWithPet(pet:Pet){
        println("Enjoy with my ${pet.species}.")
    }
}
//인터페이스 위임
interface A{
    fun fA(){}
}
interface B{
    fun fB(){}
}
class C(val a:A,val b:B){
    fun fC(){
        a.fA()
        b.fB()
    }
}
//위에걸 아래거로 by 위임
class C2(val a:A,val b:B):A by a,B by b{
    fun fC(){
        fA()
        fB()
    }
}



fun main(){
    val obj= Cat("small")
    println("PetCategory : ${obj.category}")
    obj.feeding()   //구현된 메서드
    obj.patting()   //기본 메서드 (인터페이스의 기본 구현부가 동작)

    println("pet Message Tag : ${obj.msgTag}")

    //=============

    val master = Master()
    val dog = Dog("Toto","small")
    val cat = Cat2("Toto","Bigfat")


    master.playWithPet(dog)
    master.playWithPet(cat)
}

