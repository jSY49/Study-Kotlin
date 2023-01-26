package kotlin2.chapter4

import java.util.TreeSet

/*
    set
        - 정해진 순서가 없는 요소들의 집합
        - 집합 개념이기 때문에 동일한 요소를 중복해서 가질 수 없다.
        -생성 핼퍼 함수 : 불변=setOf() / 가변형=mutavleSetOf()

    hashSet
        - 불변성 선언이 없기 떄문에 추가 및 삭제 등의 기능을 수행할 수 있다.
        - 핼퍼 함수 : hashSetOf()
        - 입력 순서와 중복된 요소는 무시된다,
        - 정렬기능은 없지만 해시값을 통해 요소를 찾아내므로 검색 속도는 O(1)이다.

    sortedSetOf()
        - 자바의 TreeSet 컬렉션을 정렬된 상태로 반환
        - java.util.*을 import해야한다.
        - TreeSet은 저장된 데이터의 값에 따라 정렬(이진트리 알고리즘 사용)
        - HashSet보다 성능이 좀 떨어지고 데이터를 추가하거나 삭제하는 게 시간이 걸리지만 검색과 정렬이 뛰어나다

    linkedSetOf()
        - 자바의 LinkedHashSet 자료형을 반환하는 헬퍼함수
        - 자료구조 중 하나인 링크드 리스트를 사용해 구현된 해시 테이블에 요소를 저장
        - HashSet,TreeSet보다 느리지만 데이터 구조상 다음 데이터를 가리키는 포인터 연결을 통해 메모리 저장 공간을 좀 더 효율적으로 사용
 */


fun main() {
    val mixedTypesSet = setOf("Hello", 5, "world", 3.14, 'c') // 자료형 혼합 초기화
    var intSet: Set<Int> = setOf<Int>(1, 5, 5)  // 정수형만 초기화     / 중보된 데이터는 하나만

    println(mixedTypesSet)
    println(intSet)


    //가변형 set 정의하기
    val animals = mutableSetOf("Lion","Dog","Cat","python","Hippo")
    println(animals)
    animals.add("Dog")  //이미 존재하므로 변화 없음
    println(animals)
    animals.remove("python")    //요소 삭제
    println(animals)


    //hash
    val intsHashSet : HashSet<Int> = hashSetOf(6,3,4,7)
    println(intsHashSet)
    intsHashSet.add(5) //추가
    intsHashSet.remove(6)
    println(intsHashSet)

    //sortedSetOf()
    val intsSortedSet : TreeSet<Int> = sortedSetOf(4,1,7,2)
    intsSortedSet.add(6)
    intsSortedSet.remove(1)
    println("intsSortedSet = $intsSortedSet")
    intsSortedSet.clear()
    println("intsSortedSet = $intsSortedSet")

}