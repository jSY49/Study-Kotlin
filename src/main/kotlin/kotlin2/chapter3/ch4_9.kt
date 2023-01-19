package kotlin2.chapter3

/*
    - escape 문자
        \t  탭(tab)
        \b  백스페이스(backspace)
        \n  개행(newline)
        \r  리턴(carriage return)
        *윈도우 : \r\n, 맥: \r, unix : \r
        \'  작은따옴표(single quote)
        \"  큰따옴표(double quote)
        \\  슬래시(slash)
        \$  달러 기호(dollar)
        /uHHHH 유니코드문자       //"\uAc00" 이렇게 사용 가능 (**한글은 AC00~D7AF)

    - 형식문자
        %b 참과 거짓의 Boolean 유형
        %c 문자
        %d 부호있는 정수
        %e E 표기법의 실수
        %f 10진 실수
        %g 10진 혹은 E 표기법의 실수
        %h 해시코드
        %n 줄 구분
        %o 8진 정수
        %s 문자열
        %t 날짜나 시간
        %x 16진 정수

 */

fun main(){

    val text="""
        |Tell me and I forgot.
        |Teach me and I remember.
                 |Invlovr me and I Learn.
        |(Benjamin Franklin)
    """.trimMargin()        //기본|를  기준으로 공백 제거 해서 보여줌 (특정 문자를 지정해 주고 싶으면 괄호에 넣어 지정해주면됨 )
    println(text)

    //format()사용한 형식 문자
    val pi =3.141592
    val dec = 10
    val s ="hello"
    println("pi = %.2f,%3d, %s".format(pi,dec,s))


}