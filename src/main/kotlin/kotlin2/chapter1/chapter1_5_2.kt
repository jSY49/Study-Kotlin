package kotlin2.chapter1

internal class InternalClass{
    internal var i =1
    internal fun icFunc(){
        i+=1    //접근 하용
    }
    fun access(){
        icFunc()    //접근허용
    }
}

class Other_{
    internal val ic = InternalClass()       //프로퍼티 지정시 internal로 맞춰야 함
    fun test(){
        ic.i    //접근 허용
        ic.icFunc()     //접근 ㅇ
    }
}

fun main(){
    val mic = InternalClass()   //생성 ㅇ
    mic.i       //접근 ㅇ
    mic.icFunc()    //접근 ㅇ
}