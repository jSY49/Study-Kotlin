package kotlin2.chapter1
/*
    getter / setter
    -> 게터와 세터를 합쳐서 접근 메서드 라고 함.
    -> 자바에서는 모든 필드에 대한 접근 메서드를 만들어야 하지만 코틀린은 자동생성됨

    var 프로퍼티이름[: 프로퍼티자료형] [= 프로퍼티 초기화]
    [get() { 게터 본문 } ]
    [set(value) {세터 본문}]
    val 프로퍼티이름[: 프로퍼티자료형] [= 프로퍼티 초기화]
    [get() { 게터 본문 } ]
    **Val 은 getter 만 선언 가능
 */

class PersonInfo(var name: String, var age : Int)

class UserInfo(_id : Int, _name : String, _age : Int){
    //프로퍼티
    val id : Int = _id
        get() = field       //field는 일종의 id를 가리키는 포인터(field 명 변경 불가)
    var name : String = _name
        get() = field
        set(value) {        //value = 외부값 (이름 바꿔도 됨)
            field=value
        }
    var age : Int = _age
        get() = field
        set(value) {
            println("유저의 나이느으으은?")
            field=value
        }

}

open class FirstC{
    open val x: Int =0
        get(){
            println("FirstC")
            return field
        }
    val y :Int =0
}
class SecondC : FirstC(){
    override var x: Int=0   //부모의 val 변수를 오버라이딩 하였지만 var로 선언할 수 있음.

        get(){
            println("Second")
            return field+3
        }
        set(value) {
            field = value+10
        }
}



fun main(){
    val user=PersonInfo("Gill",30)
    val name = user.name
    user.age=43
    println("name : $name, ${user.age}")

    println("----------------------------------")

    val user1= UserInfo(1,"Gill",27)
    //user1.id=2        //Erorr, val 프로퍼티는 값 변경 불가
    user1.age=43
    println("user1.age= ${user1.age}")  //게터 동작

    println("----------------------------------")

    val second = SecondC()
    println(second.x)
    second.x=10
    println(second.x)
    println(second.y)

}