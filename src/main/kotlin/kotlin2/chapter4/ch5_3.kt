package kotlin2.chapter4

import java.util.*

/*
    map
        - 키(key)와 값(value)으로 구성된 요소를 저장 (키와 값은 모두 객체)
        - 키는 중복될 수 없지만 값은 중복될 수 있다. (중복된 키가 들어 오면 기존의 값은 없어지고 새로운 값을 대체)
        - 불변형 = mapOf()     /   가변형 = mutableMapOf()

    mapOf()
        -val map: Map<키자료형, 값자료형> = mapOf(키 to 값[, ...])

    map의 멤버
        - size
        - key   =모든 키를 반환
        - values    =모든 값을 반환
        - isEmpty()
        - containsKey(Key:K)
        - containsValue(value : V)
        - get(Key:K)    = 키에 해당하는 값을 반환 없으면 null

    mutableMap의 추가 멤버
        - put(Key:K,value : V)  = 추가
        - remove(Key:K)
        - putAll(from: Map<out K,V>)    =인자로 주어진 Map 데이터를 갱신하거나 추가
        - clear()

    Map을 위한 기타 자료구조
        - 자바의 HashMap,SortedMap, LinkedHashMap 을 사용할 수 있다.
        - hashMapOf(), sortedMapOf(), linkedMapOf() 로 각각 초기화
        - sortedMap의 경우 기본적으로 키에 대해 오름차순 정렬된 형태

 */


fun main() {
    // 불변형 Map의 선언 및 초기화
    val langMap: Map<Int, String> = mapOf(11 to "Java", 22 to "Kotlin", 33 to "C++")
    for ((key, value) in langMap) { // 키와 값의 쌍을 출력
        println("key=$key, value=$value")
    }
    println("langMap[22] = ${langMap[22]}") // 키 22에 대한 요소 출력
    println("langMap.get(22) = ${langMap.get(22)}") // 위와 동일한 표현
    println("langMap.keys = ${langMap.keys}") // 맵의 모든 키 출력

    //가변형
    val capitalCityMap : MutableMap<String,String>  //선언 시 키와 값의 자료형을 명시할 수 있음.
    = mutableMapOf("Korea" to "Seoul", "China" to "베이징","일본" to "도쿄")
    println(capitalCityMap.values)  //값만
    println(capitalCityMap.keys)    //키만
    capitalCityMap.put("Uk","런던")
    capitalCityMap.remove("China")
    println(capitalCityMap)

    //putAll을 사용한 맵의 추가
    val appData = mutableMapOf("Usa" to "WashingTon")
    capitalCityMap.putAll(appData)  //기존 요소에 추가된 요소를 병합할 수 있다.
    println(capitalCityMap)


    //java.util.HashMap 사용
    val hashMap : HashMap<Int,String> = hashMapOf(1 to "Hello",2 to "World")
    println(hashMap)
    //java.util.SortedMap 사용
    val sortedMap : SortedMap<Int,String> = sortedMapOf(1 to "banana",2 to "apple")
    println(sortedMap)
    //java.util.LinkedHashMap 사용
    val liskedMap : LinkedHashMap<Int,String> = linkedMapOf(1 to "Gill",2 to "Yeon")
    println(liskedMap)


}