package kotlin2.chapter3

import java.util.*

/*
    val|var 변수명 = Array(요소개수, 초기값)

 */

fun main(){
    val arr1= Array(5,{i-> i*2})    //초기값 : 2씩 곱해지는 정수로 채우기
    println("arr1= ${Arrays.toString(arr1)}")

    var arr2 = arrayOfNulls<Int>(1000)  //1000개의 Null로 채워진 정수배열
    val arr3= Array(5, { 0 })   //0으로 초기화된
    //val arr4= Array(1000,{i->myClass(i)})   //툭정 클래스 객체로 배열 생성

    val arr4 = intArrayOf(1,2,3,4,5)    //다섯개로 고정된 배열
    val arr5 =arr4.plus(6);     //하나의 요소를 추가한 새 배열 생성
    println("arr4= ${Arrays.toString(arr4)}")
    println("arr5= ${Arrays.toString(arr5)}")

    val arr6=arr5.sliceArray(0..3)  //필요한 범위를 잘라서 새 배열 생성
    println("arr6= ${Arrays.toString(arr6)}")

    println("arr6.first() = ${arr6.first()}")   //배열의 첫번째 요소 확인
    println("arr6.last() = ${arr6.first()}")   //배열의 마지막 요소 확인
    println("arr6.indexOf(2) =${arr6.indexOf(2)}")   //배열의 특정 요소의 인덱스 값
    println("arr6.average() = ${arr6.average()}")   //배열의 평균값
    println("arr6.count() = ${arr6.count()}")   //배열의 개수
    println("arr6.contains(4) = ${arr6.contains(4)}")   //배열에 특정 요소가 포함되어 있는지
    println("4 in arr6=${4 in arr6}")  //배열에 특정 요소가 포함되어 있는지

    //배열의 순환
    println("배열의 순환")
    arr6.forEach { e-> print("$e ") }
    println()
    arr6.forEach { print("$it ") }
    println()
    arr6.forEachIndexed({i,e-> println("arr6[$i] = $e") })

    //iterator를 사용한 요소 순환
    val iter: Iterator<Int> = arr6.iterator()
    while (iter.hasNext()){
        val e = iter.next()
        print("$e ")
    }
}
