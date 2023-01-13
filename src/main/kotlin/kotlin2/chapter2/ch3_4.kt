package kotlin2.chapter2

/*
    코틀린의 내부 클래스 종류 : 중첩(nested)클래스 , 이너(inner)클래스

    자바 vs 코틀린의 내부 클래스
    정적 -  중첩
    멤버 -  이너
    지역 - 지역
    익명클래스 - 익명객체(object)

   <자바>
   //멤버클래스
   class A {
        class B{    //외부 클래스 A의 필드에 접근 가능
        }
    }
    //정적클래스
    class A {
        static class B{
        }
    }

    //코틀린
    //inner클래스
     class A {
        inner class B{  //외부 클래스 A의 필드에 접근 가능
        }
    }
    //정적클래스처럼 사용한 중첩 클래스
    class A {
        static class B{     //코틀린에서는 아무 키워드가 없는 클래스느 중첩클래스이며 정적클래스처럼 사용
                            //외부 클래스 A의 프로퍼티, 메서드에 접근할 수 없음.
        }
    }
 */

//중첩클래스
class Outer {
    val ov = 5
    class Nested {
        val nv = 10
        fun greeting() = "[Nested] Hello ! $nv" // 외부의 ov에는 접근 불가

        //컴페니언 오브젝트에 접근가능
        fun accessOuter(){
            println(country)
            getSomething()
        }

    }
    fun outside() {
        val msg = Nested().greeting() // 객체 생성 없이 중첩 클래스의 메서드 접근
        println("[Outer]: $msg, ${Nested().nv}") // 중첩 클래스의 프로퍼티 접근
    }

    companion object{       //컴패니언 객체는 static 처럼 접근 가능
        const val country = "korea"
        fun getSomething()= println("Get something...")
    }

}

//이너 클래스
class Smartphone(val model: String) {

    private val cpu = "Exynos"

    inner class ExternalStorage(val size: Int) {
        fun getInfo() = "${model}: Installed on $cpu with ${size}Gb" // 바깥 클래스의 프로퍼티 접근
    }
}


fun main() {
    //중첩클래스
    // static 처럼 Outer의 객체 생성 없이 Nested객체를 생성 사용할 수 있음
    val output = Outer.Nested().greeting()
    println(output)

    Outer.Nested().accessOuter()

    println("--------------")
    // Outer.outside() // 에러! Outer 객체 생성 필요
    val outer = Outer()
    outer.outside()


    //==================================
    println("=================")

    //이너 클래스
    val mySdcard = Smartphone("S7").ExternalStorage(32)
    println(mySdcard.getInfo())
}