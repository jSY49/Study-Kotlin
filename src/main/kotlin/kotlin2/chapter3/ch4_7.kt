package kotlin2.chapter3

import java.util.*

/*
    정렬된 배열을 반환
        - sortedArray()
        - sortedArrayDescending()
    원본 배열에 대한 정렬을 진행
        - sort()
        - sortDescending()
    List로 반환
        -.sorted()
        -.sortedDescending()

 */

data class Product(val name:String,val price:Double)

fun main() {

    val arr = arrayOf(8, 4, 3, 2, 5, 9, 1)
    println("원본: ${Arrays.toString(arr)}")
    // ① 오름차순, 내림차순으로 정렬된 일반 배열로 반환
    val sortedNums = arr.sortedArray()
    println("ASC: " + Arrays.toString(sortedNums))

    val sortedNumsDesc = arr.sortedArrayDescending()
    println("DEC: " + Arrays.toString(sortedNumsDesc))

    // ② 원본 배열에 대한 정렬
    arr.sort(1, 3) // sort(fromIndex, toIndex)  1부터 3이전까지 정렬한다는 의미
    println("ORI: " + Arrays.toString(arr))
    arr.sortDescending()
    println("ORI: " + Arrays.toString(arr))

    // ③ List 으로 반환
    val listSorted: List<Int> = arr.sorted()
    val listDesc: List<Int> = arr.sortedDescending()
    println("LST: " + listSorted)
    println("LST: " + listDesc)

    // ④ SortBy를 이용한 특정 표현식에 따른 정렬
    val items = arrayOf<String>("Dog", "Cat", "Lion", "Kangaroo", "Po")
    items.sortBy { item -> item.length }    //길이 짧은거부터 긴거까지 정렬
    println(Arrays.toString(items))


    //prac
    val product = arrayOf(
        Product("snow ball",870.00),
        Product("smart Phone",999.00),
        Product("Drone",633.55),
        Product("Mouse",135.34),
        Product("Mouse",150.34),
        Product("tablet",123.99)
    )
    product.sortBy { it.price } //price를 기준으로 오름차순 정렬
    product.forEach { println(it) }

    println("worthWith")
    //두 객체으 ㅣ비교, p1이 크면 1, 같으면 0, 작으면 -1
    product.sortWith(
        Comparator<Product>{p1,p2->
            when{
                p1.price>p2.price ->1
                p1.price==p2.price ->0
                else -> 1
            }
        }
    )
    product.forEach { println(it) }
    println()

    //compareBy를 함께 사용해 두개의 정보 정렬
    //varargs로 받고 있으므로 두개 이상 사용 가능
    product.sortWith(compareBy({it.name},{it.price}))   //앞에 것을 기준으로 정렬, 이름이 같으면 가격으로 정렬
    product.forEach { println(it) }
    println()

    //지정된 필드의 가장 작은/ 큰 값 골라내기
    println(product.minBy { it.price })
    println(product.maxBy { it.price })


    //배열 필터링 하기
    val arr2 = arrayOf(1,-2,3,4,-5,0)
    arr2.filter { e->e>0 }.forEach { print("$it ") }    //0보다 큰 값만 골라내기
    println()

    //체이닝을 통한 필터링
    val fruits = arrayOf("banana","avocado","apple","kiwi")
    fruits
        .filter { it.startsWith("a") }      //a로 시작하는거만 골라내기
        .sortedBy { it }                          //정렬
        .map { it.toUpperCase() }                   //대문자로 변환
        .forEach { print("$it ") }                  //출력

    //flatten()
    println("\n\nflatten!")
    val fruits2 = arrayOf("banana","avocado","apple","kiwi")
    val numbers = arrayOf(1,2,3,4)
    val simpleArray = arrayOf(fruits2,numbers)
    simpleArray.forEach { println(it) }

    val flattenSimpleArrays = simpleArray.flatten() //단일 배열로 변환
    println(flattenSimpleArrays)

}