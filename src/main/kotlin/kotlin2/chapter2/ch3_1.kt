package kotlin2.chapter2

/*
    추상클래스(abstract class)
        - 구현 클래스에서 가져야할 명세를 정의한 클래스(프로퍼티 및 메서드 템플릿)
        -abstract라는 키워드와 함께 선언하며 추상클래스는 객체 생성 안됨
        -'구체적이지 않은 것'을 나타내기 떄문에 하위 파생 클래스에서 구체적으로 구현
        -open 키워드를 사용하지 않고도 파생 클래스 작성 가능

        abstract class 클래스이름

 */

abstract class Vehicle(val name :String, val color : String , val weight : Double){     //주 생성자에 정의된 프로퍼티는 비 추상 프로파티들

    // 추상 프로퍼티 (반드시 하위 클래스에서 재정의해 초기화해야 함)
    abstract val maxSpeed : Double

    // 일반 프로퍼티 (초기 값인 상태를 저장할 수 있음)
    var year = "2018"

    // 추상 메서드 (반드시 하위 클래스에서 구현해야 함)
    abstract fun start()
    abstract fun stop()

    // 일반 메서드
    fun displaySpecs() {
        println("Name: $name, Color: $color, Weight: $weight, Year: $year, Max Speed: $maxSpeed")
    }
}

//1. 주 생성자의 프로퍼티가 초기화 될 수 있도록 해야함.
//2. 추상 프로퍼티들을 override하여 재정의 해줘야함.
//3. 주상 메서드도 override하여 재정의 해야함.

class Car(name: String,
          color: String,
          weight: Double,
          override val maxSpeed: Double) : Vehicle(name, color, weight) {

    override fun start() {
        println("Car started")
    }

    override fun stop() {
        println("Car stopped")
    }

    fun autoPilotOn(){
        println("Auto Pilot On")
    }

}

class Motobike(name: String,
          color: String,
          weight: Double,
          override val maxSpeed: Double) : Vehicle(name, color, weight) {

    override fun start() {
        println("Motobike started")
    }

    override fun stop() {
        println("Motobike stopped")
    }


}

//추상클래스의 선언
abstract class Printer{
    abstract fun print()
}

val myPrinter = object :Printer(){      //객체 인스턴스
    override fun print() {              //추상 메서드의 구현
        println("출력합니다. ")
    }
}


fun main(){

    var car = Car("Martiz","red",1000.0,100.0);
    //var v = Vehicle("Martiz","red",1000.0);   //추상클래스로부터는 인스턴스를 생성할 수 없음.
    var motor = Motobike("Motor1","green",1000.0,100.0);

    car.year="2014"
    car.displaySpecs()

    motor.displaySpecs()
    car.start()
    motor.start()


    //--------------------

    println("-------------------")
    myPrinter.print()

}