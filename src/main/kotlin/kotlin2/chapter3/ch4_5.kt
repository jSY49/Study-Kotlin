package kotlin2.chapter3

import java.util.*

/*
    배열
        - 데이터를 연속적으로 나열한 상태
        - arrayOf() 나 Array() 생성자를 통해 배열 생성
        - arrayOfNulls()은 빈 배열
 */

fun main(){
    //기본
    val numbers = arrayOf(4, 5, 7, 3) // 정수형으로 초기화된 배열
    val animals = arrayOf("Cat", "Dog", "Lion") // 문자열형으로 초기화된 배열
    for (element in numbers) { // 정수형으로 초기화된 배열 출력하기
        println(element)
    }

    //이차원 배열
    val arr1 = arrayOf(1,2,3)
    val arr2 = arrayOf(4,5,6)
    val arr3 = arrayOf(7,8,9)
    val arr2D = arrayOf(arr1,arr2,arr3)
    val arr2D_2=arrayOf(arrayOf(1,2,3),arrayOf(4,5,6),arrayOf(7,8,9))   //이렇게도 가능

    println("<2차원 배열>")
    for (e1 in arr2D){
        for (e2 in e1){
            print(e2)
        }
        println()
    }

    //다양한 자료형 혼합
    println("<배열_ 자료형 혼합>")
    val mixArr= arrayOf(4,5,6,7,"gill",false)   //혼합 가능
    for (e in mixArr) { // 정수형으로 초기화된 배열 출력하기
        print("$e ")
    }
    /*
        특정자료형만
            - arrayOf<자료형>() 혹은
            - charArrayOf()
            - booleanArrayOf()
            - longArrayOf()
            - shortArrayOf()
            - byteArrayOf()
            - intArrayOf()
            - ubyteArrayOf()
            - ushortArrayOf()
            - uintArrayOf()
            - ulongArrayOf()
     */

    //읽기 접근
    val arr4 = intArrayOf(1,2,3,4,5)
    println(arr4.get(3))
    println(arr4[3])
    println(arr2D[1][2])    //2차원 배열 접근
    //쓰기 접근
    arr4.set(4,9)
    arr4[3]=0
    arr2D[1][2]=10

    println("arr4.size()=${arr4.size}") //배열 사이즈
    println("arr4.sum()=${arr4.sum()}") //요소들 모두 더해서 찍어줌
    println("Arrays.toString(arr4)=${Arrays.toString(arr4)}")


}
