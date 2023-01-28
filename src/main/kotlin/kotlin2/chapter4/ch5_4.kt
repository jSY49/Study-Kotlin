package kotlin2.chapter4

/*
    컬렉션 확장 함수
        - 연산자(operators) : 더하고 빼는 등의 합의
        - 집계(aggregators) : 최대, 최소, 집합, 총합 등의 계산
        - 검사(checks) : 요소를 검사하고 순환하기 위한 기능
        - 필터(filtering) : 원하는 요소를 골라내기 위한 기능
        - 변환(transformers) : 뒤집기, 정렬, 자르기 등의 변환 가능

    요소처리를 위한 집계
        - forEach, forEachIndexed, onEach, count, max, min, maxBy, minBy, fold, reduce, sumBy
        - forEach : 각 요소를 람다식을 처리한 후 컬렉션을 반환하지 않음
        - onEach : 각 요소를 람다식을 처리한 후 컬렉션을 반환 받음.

    fold와 reduce    : 초기값과 정해진 식에 따라 요소를 처리하기 위해
        - fold : 초기값과 정해진 식에 따라 처음 요소부터 끝 요소에 적용해 값 반환
        - reduce : fold와 동일하지만 초기값을 사용하지 않는다.
        -foldRight, reduceRight : 위 개념과 동일하지만 요ㅗㅅ의 마지막 요소에서 처음 요소로 적용

    map()
        - 주어진 컬렉션의 요소를 일괄적으로 모든 요소에 식을 적용해 새로운 컬랙션을 만듦
        - forEach와는 다르게 주어진 컬렉션을 거드리지 않는다.

    정렬 연산
        - reveres()
        - sorted() / sortedDescending()

    sequence
        - 순차적으로 요소의 크기를 특정하지 않고 추후에 결정하는 특수한 컬렉션  (예 : 로그 파일)
        - 예를 드어 특정 파일에서 줄 단위로 읽어서 요소를 만들 때
        - 시퀀스는 처리 중에는 계산하고 이지 않다가 toList()나 count()같은 최종 연산에 의해 결정

    asSequence
        - 중간 연산 결과 없이 한 번에 끝까지 여난한 후 결과를 반환
            - filter나 map을 메서드 체이닝해서 사용할 경우 순차적 연산이기 때문에 시간이 많이 걸릴 수 있지만 asSequence()를 사용하면 병렬처리되기 때문에 성능이 좋아짐
 */



fun main() {

    val list1 = listOf(1, 2, 3, 4, 5, 6)
    val list2 = listOf("하나","둘","셋")
    val listPair = listOf(Pair("A", 300), Pair("B", 200), Pair("C", 100))
    val map = mapOf(11 to "Java", 22 to "Kotlin", 33 to "C++")
    val listWithNull = listOf(1, null, 3, null, 5, 6)
    val listRepeated = listOf(2,2,3,4,5,5,6)


    //연산자
    println("======연산자======")
    println(list1 + 7)
    println(list2 + "넷")
    println(list1 -1)   //요소의 제거
    println(list1 + list2)
    println(list1 + listOf(9,10))   //일치하는 요소의 제거
    println(map + Pair(55,"Bye"))   //Pair()를 사용한 Map의 요소 추가
    println(map + mapOf(44 to "Apple", 99 to "Orange")) //Map의 병합
    println(map - listOf(22,"Java"))  //List에 일치하는 값을 Map 에서 제거(키를 사용)

    //집계
    println("======집계======")
    list1.forEach{
        print("$it ")
    }
    println()
    list1.forEachIndexed{index, i ->
        println("index[$index]: $i")
    }

    val returedList = list1.onEach { print(it) }
    println()
    val returnedMap = map.onEach { println("key : ${it.key}, value : ${it.value}") }
    println("returnedList = $returedList")
    println("returnedMap = $returnedMap")

    //fold와 reduce
    println("======fold와 reduce======")
    println(list1.fold(4){total,next -> total+next})    //4+1+...+6=25
    println(list1.fold(1){total,next -> total*next})    //1*1*2*3*...*6=720
    println(list1.foldRight(4){total,next -> total+next})
    println(list1.foldRight(1){total,next -> total*next})

    println(list1.reduce{total,next -> total+next})    //1+...+6=25
    println(list1.reduceRight{total,next -> total*next})    //1*2*3*...*6=720



    //map()
    println("======map()======")
    println("Base list = $list1")
    // map: 컬렉션에 주어진 식을 적용해 새로운 컬렉션을 반환
    print("map() = ")
    println(list1.map { it * 2 })
    // mapIndexed:  컬렉션에 인덱스를 포함해 주어진 식을 적용해 새로운 컬렉션 반환
    print("mapIndexd = ")
    val mapIndexed = list1.mapIndexed { index, it -> index * it }
    println(mapIndexed)
    // mapNotNull: null을 제외하고 식을 적용해 새로운 컬렉션 반환
    print("mapNotNull = ")
    println(listWithNull.mapNotNull { it?.times(2) })
    //flatMap() : 각 요소에 식을 적용한 후 다시 합쳐 새로운 컬렉션을 반환
    print("flatMap() = ")
    println(list1.flatMap { listOf(it,'A') })
    val res = listOf("abc","12").flatMap { it.toList() }    //분해하는 것
    println(res)    //=>[a, b, c, 1, 2]
    print("groupBy() = ")
    val grpMap = list1.groupBy { if(it%2==0) "even" else "odd"}
    println(grpMap) ///=>  {odd=[1, 3, 5], even=[2, 4, 6]} even과 odd 라는 Map이 만들어 지는 것


    //elemet
    println("======elemet======")
    println("elementAt: "+list1.elementAt(1))   //인덱스에 해당하는 요소만 반환
    println("elementAtOrElse: "+list1.elementAtOrElse(10,{2*it}))   //주어진 값이 인덱스를 벗어나는 경우 식에 따라 결과 반환 아니면 요소 반환
    println("elementAtOrNull: "+list1.elementAtOrNull(10))   //주어진 값이 인덱스를 벗어나는 경우 null 반환


    //정렬
    println("======정렬======")
    println("Base list = $list1")
    println("reversed= "+list1.reversed())
    println("sortedBy= "+list1.sortedBy { it%3 })   //수식에 따라 정렬

    //sequence
    println("======sequence======")
    val nums : Sequence<Int> = generateSequence(1) {it+1}   //시드값1을 시작으로 1씩 증가하는 시퀀스 정의
    println(nums.take(10).toList()) //take를 사용해 원하는 요소 개수만큼 획득하고, toList로 List컬랙션 반환

    val squares = generateSequence(1){it+1}.map { it*it }
    println(squares.take(10).toList())

    val oddSquares = squares.filter { it%2!=0 } //조건에 해당하는 요소만 골라내기
    println(oddSquares.take(5).toList())

    //asSequence
    val listDefualt = list1
        .map { println("map($it)"); it *it }    //요소들을 출력하고 제곱해줌
        .filter { println("filter($it)"); it%2==0 } //요소들을 출력하고 짝수만 골라내기
    println(listDefualt)

    // filter: 식에 따라 요소를 골라내기
    println("======filter======")
    println(list1.filter { it % 2 == 0 }) // 짝수만 골라내기
    println(list1.filterNot { it % 2 == 0 }) // 식 이외에 요소
    println(listWithNull.filterNotNull()) // null을 제외

    // slice: 특정 인덱스의 요소들을 잘라서 반환하기
    println("======slice======")
    println("slice: " + list1.slice(listOf(0, 1, 2)))
}