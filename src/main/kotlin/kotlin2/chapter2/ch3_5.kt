package kotlin2.chapter2

/*
    코틀린의 내부 클래스 종류 : 중첩(nested)클래스 , 이너(inner)클래스

    자바 vs 코틀린의 내부 클래스
    정적 -  중첩
    멤버 -  이너
    지역 - 지역
    익명클래스 - 익명객체(object)

    *지역 클래스 : 특정 메서드 블록이나 init 블록과 같이 블록 범위에서만 유효한 클래스
                 블록 범위를 벗어나면 더 이상 사용되지 않음

    *익명 객체 : 자바에서 익명 이너 클래스 라는 것을 제공해 일회성으로 객체를 생성해 사용
               코틀린에서는 object 키워드를 사용하는 익명 객체로 값은 기능을 수행
 */

//지역 클래스
class Smartphone2(val model: String) {

    private val cpu = "Exynos"

    inner class ExternalStorage(val size: Int) {
        fun getInfo() = "${model}: Installed on $cpu with ${size}Gb" // 바깥 클래스의 프로퍼티 접근
    }

    fun powerOn(): String {
        class Led(val color: String) {  // 지역 클래스 선언
            fun blink(): String = "Blinking $color on $model"  // 외부의 프로퍼티는 접근 가능
        }
        val powerStatus = Led("Red") // 여기에서 지역 클래스가 사용됨
        return powerStatus.blink()
    } // powerOn() 블록 끝
}


//익명 객체

interface Switcher { // ① 인터페이스의 선언
    fun on(): String
}
class Smartphone3(val model: String) {

    private val cpu = "Exynos"

    inner class ExternalStorage(val size: Int) {
        fun getInfo() = "${model}: Installed on $cpu with ${size}Gb" // 바깥 클래스의 프로퍼티 접근
    }

    fun powerOn(): String {
        class Led(val color: String) {
            fun blink(): String = "Blinking $color on $model"
        }
        val powerStatus = Led("Red")
        val powerSwitch = object : Switcher {  // ② 익명 객체를 사용해 Switcher의 on()을 구현
            override fun on(): String {
                return powerStatus.blink()
            }
        } // 익명(object) 객체 블록의 끝
        return powerSwitch.on() // 익명 객체의 메서드 사용
    }
}


fun main() {

    //지역
    val myphone = Smartphone2("Note9").ExternalStorage(128)
    println(myphone.getInfo())
    println(Smartphone2("Note9").powerOn())

    println("============")
    //익명
    val myphone2 = Smartphone3("Note9").ExternalStorage(128)
    println(myphone.getInfo())
    println(Smartphone2("Note9").powerOn())

    //둘이 실행 결과 같음
}