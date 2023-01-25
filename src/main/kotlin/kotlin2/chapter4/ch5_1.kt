package kotlin2.chapter4

/*
    Collection
        - 자주 사용ㄷ하눈 가초적인 자료구조를 모아놓은 일종의 프레임워크로 표준 라이브러리로 제공
        - List,Set,Map 등
        - 자바와는 다르게 불변형, 가변형으로 나뉘어 컬렉션을 다룰 수 있음.
        - 불변형 : listOf/setOf/mapOf
        - 가변형 : mutableListOf, arrayListOf/mutableSetOf,hashSetIf,linkedSetOf,sortedSetOf/
                    mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf

    Collection 인터페이스 특징
        - Iterable로부터 확장
        - 불변형이므로 Collection으로부터 확장된 Set 과 List는 읽기 전용의 컬렉션됨
        - size, isEmpty(),contains(element:E),
            containsAll(element:Collection<E>)=>인자로 받아들인 Collection이 있다면 true반환

    MutableIterable 과 MutableCollection 인터페이스
        - 가변형 컬렉션을 지원하기 위해 준비된 인터페이스
        - 요소를 추가하거나 제거하는 등의 기능을 수행할 수 있다.

    MutableCollection의 멤버
        - add(element:E),remove(element:E)
        ,addAll(element:Collection<E>) => 컬렉션을 인자로 전달받아 모든 요소를 추가하고 성공하면 true반환 실패하면 false 반환
        ,removeAll(element:Collection<E>) => 컬렉션을 인자로 전달받아 모든 요소를 삭제하고 성공하면 true반환 실패하면 false 반환
        ,retainAll(element:Collection<E>) => 인자로 전달받은 컬렉션의 요소만 보유한다. 성공 시 true, 실패시 false 반환
        ,clear()



    -List
        - 순서에 따라 정렬된 요소를 가지는 컬렉션( 가장 많이 사용되는 컬렉션 중에 하나)
        - 값을 변경할 수 없는 불변형 List를 만들기 위해 *헬퍼 함수인 listOf()를 사용
        - 값으르 변경할 수 있는 가변형을 표현하기 위해서는 mutableListOf()를 사용
        - 인자는 원하는 만큼의 가변 인자를 가지도록 vararg로 선언 가능
        *헬퍼 함수 : 객체 생성 시 요소를 직접 선언하기 보다 특정 함수의 도움을 통해 생성
        - get(index :Int), indexOf(element:E), lastIndexOf(element :E),
        listIterator()  => 목록에 있는 iterator를 반환
        ,subList(fromIndex : Int, toIndex : Int)    => 특정 인덱스의 from 과 to 범위에 있는 요소 목록을 반환한다.

    - 가변형 List 생성
        - arrayListOf() 함수
            - 가변형 헬퍼 함수를 사용하면 손쉽게 요소를 추가하거나 삭제할 수 있다.
            - arrayListOf()는 가변형 List를 생성하지만 반환자료형은 자바의 ArrayList


 */



fun main() {
    // 불변형 List의 사용
    var numbers: List<Int> = listOf(1, 2, 3, 4, 5)
    var names: List<String> = listOf("one", "two", "three")
    var mixed =listOf(1,4,"lala",'c')

    for (name in names) {
        println(name)
    }
    for (num in numbers) print(num)  // 한 줄에서 처리하기
    println() // 내용일 없을 때는 한 줄 내리는 개행

    for (mix in mixed) {
        println(mix)
    }

    val emptyList : List<String> = emptyList<String>()  //빈 리스트를 생성

    val nonNullList : List<Int> =   listOfNotNull(2,45,null,5,null);    //null이 아닌 요소만 골라 컬렉션을 초기화
    for (non in nonNullList) {
        println(non)
    }


    //가변형 List의 사용
    val mutableList : MutableList<String> = mutableListOf<String>("kildong","dooly","chelsu")
    mutableList.add("Ben")
    mutableList.removeAt(1) //인덱스 1 삭제
    mutableList[0]="Sean"   //인덱스 0 을 변경, set과 같은 역할
    println(mutableList)


}