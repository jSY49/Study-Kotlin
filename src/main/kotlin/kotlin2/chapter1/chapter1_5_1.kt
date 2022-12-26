package kotlin2.chapter1

/*
    캡슐화 : 클래스를 작성할 때 외부에서 숨겨하 하는 속성이나 기능
    가시성 지시자를 통해 외부 접근 범위를 결정할 수 있음.
        -private    : 외부 접근 불가
        -public     : 어디서든 접근 가능 (기본)
        -protected  : 외부 전그 불가 하지만 하위 상속 요소에서는 가능 
        -internal   : 같은 정의의 모듈 내부에서는 접근 가능
 */

private class PrivateClass {
    private var i = 1
    private fun privateFunc() {
        i += 1 // 접근 허용
    }
    fun access() {
        privateFunc() // 접근 허용
    }
}

class OtherClass {
    /*
    val opc = PrivateClass() // 불가 - 프로퍼티 opc는 private이 되야 함
    */
    private val Popc = PrivateClass() // 이렇게는 가능

    fun test() {
        val pc = PrivateClass() // 생성 가능
    }
}

fun main() {
    val pc = PrivateClass() // 생성 가능
    /*
    pc.i =3 // 접근 불가
    pc.privateFunc() // 접근 불가
    */
    pc.access() //여기를 통해서 privateFunc 접근

    //======================

    val base = Base_()
//    base.i
//    base.protectedFunc()
    base.access()

    val derived =Derived()
    derived.j=3
    derived.derivedFunc()

}

fun TopFunction() {
    val tpc = PrivateClass() // 객체 생성 가능
}

//============================================================================================================


open class Base_ {
    protected var i =1
    protected fun protectedFunc(){
        i+=1
        println(i)
    }
    fun access(){
        protectedFunc()
    }

}

class Derived :Base_(){
    var j = 1+i
    fun derivedFunc(): Int{
        protectedFunc()
        return i
    }
}

class Other{
    fun other(){
        val bas = Base_()
//        bas.i=3   //i가 public 이면 가능
    }
}







