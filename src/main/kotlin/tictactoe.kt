import java.io.BufferedReader
import java.io.InputStreamReader

const val Row=3
const val Col=3

var board = Array(Row,{ CharArray(Col,{' '})})  //보드 2차원 배열
var input = IntArray(2, { 0 })
var br= BufferedReader(InputStreamReader(System.`in`))
var tempP:Int=1

fun main(){
    /*
    // 정수형 배열을 선언하고 초기화 하기
    val nums = arrayOf(12, 23, 52)
    // 특정 자료형이 명시된 배열 만들기
    var a: IntArray = intArrayOf(1, 2, 3)
    var b: CharArray = charArrayOf('a', 'b', 'c')
    //람다식을 통한 초기화
    val a = Array(5, { x -> (x * x) }) // 0, 1, 4, 9, 16
    //제네릭 <T> 표현에 자료형을 사용하면 특정 자료형으로 제한하게 됩니다.
    var arr1 = Array<Int>(4, {0} )
    var arr2 = Array<String>(3, {" "} )
     */
/*
    for ((i, row) in board.withIndex()){
        for ((j,column) in row.withIndex()){
            print("[$i,$j] => $column\t")
        }
        println()
    }
*/
    var i=1
    while (true){
        println("<<$i 번째 턴>>")
        printBoard()            // 보드 출력
        PlayerInput()           //입력받기

        if(isWin( )){
            println("Player${tempP} 승리!")
            break
        }
        if(tempP==1)
            tempP=2
        else
            tempP=1
        i++;
    }
    br.close()
}

fun printBoard(){
    print("  ")
    for(i in 0..Row-1){
        print("$i ")
    }
    println()
    for((i,row) in board.withIndex()){
        print("$i ")
        for((j,column) in row.withIndex()){
            print("${board[i][j]}")
            if(j<Col-1) print("|")
        }
        println()
        if(i<Row-1){
            print("  -")
            for(i in 0..Col-2){
                print("+-")
            }
        }
        println()

    }
}
fun PlayerInput(){
    var temp:String;
    println("Player${tempP} 입력(줄,칸): ")
    var(n,m)=br.readLine().split(",").map { it.toInt() }
    isInRange(n,m)  //입력 검사
}

fun isInRange(a:Int,b:Int) {
    if (a < Row && b < Col) {
        input[0] = a
        input[1] = b
        isValid()
    } else {
        println("옳바르지 않은 범위 입니다.")
        PlayerInput()
    }
}

fun isValid() {
    if(board[input[0]][input[1]]==' ') {
        if(tempP==1)
            board[input[0]][input[1]]='O'
        else
            board[input[0]][input[1]]='X'

    }else{
        println("비어있지 않은 칸 입니다. 다시 선택하십시오")
        PlayerInput()
    }
}


fun isWin() :Boolean {
    var NowRes=1
    for(i in 0 until Row){
        for(j in 1 until Col){
            if(board[i][j-1]!=' '&&board[i][j]!=' '){//가로 줄 검사
                if(board[i][j-1]==board[i][j]) {
                    NowRes+=1
                }else {
                    NowRes=0
                    break
                }

//                if(board[j-1][i]==board[j][i]) {//세로 줄 검사
//                    NowRes+=1
//                    println("NowRes = $NowRes")
//                }else {
//                    NowRes=0
//                    break
//                }
            }

        }
        if(NowRes<=Row){   //대각
            //if()
        }else{
            break
        }
    }

    if(NowRes==Row){
        println("최종 결과!")
        printBoard()
        return true //승리한 사람 있으면
    }else{
        return false
    }


}
