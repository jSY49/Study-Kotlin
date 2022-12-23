package kotlin2.chapter1

/*
super
this
@       -> 이너 클래스에서 바깥 클래스의 상위 클래스를 호출하려면 super 키워드와 함께 엣(@)기호 옆에 바깥 클래스명을 작성해 호출
<> 앵글브라켓
 */
open class Person {

    constructor(firstName: String) {
        println("[Person] firstName: $firstName")
    }
    constructor(firstName: String, age: Int) { // ③
        println("[Person] firstName: $firstName, $age")
    }
}

class Developer: Person {


    constructor(firstName: String): this(firstName, 10) { // ① 밑에 있는 부생성자를 참조
        println("[Developer] $firstName")
    }
    constructor(firstName: String, age: Int): super(firstName, age) { // ② 상위 클래스로 전달
        println("[Developer] $firstName, $age")
    }
}
open class Base{
    open val x: Int=1
    open fun f()  = println("Base class f()")

}
class Child :Base(){
    override val x: Int=1
    override fun f()  = println("child class f()")

    inner class Inside{
        fun f()= println("Inside class f()")
        fun test(){
            f()
            Child().f()
            super@Child.f()
            println("[Inside] super@Developer.x: ${super@Child.x}")
        }
    }
}
fun main(){

    val sean = Developer("Sean")    //주석 1->2->3
    println("\n")

    val t=Child()
    t.Inside().test()


}