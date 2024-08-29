# Command 패턴을 활용한 이벤트 복원
- [이벤트 소싱과 마이크로서비스 아키텍처](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=342975885)
- [코드스피츠 스터디](https://www.youtube.com/@CodeSpitz)

# 참고
## suspend
- 코루틴(Coroutine)에서 사용되는 Kotlin의 키워드로, 함수가 일시 중단(suspend)될 수 있음을 나타냄

## operator
- Kotlin에서 연산자 오버로딩을 가능하게 하는 키워드입니다. 연산자 오버로딩은 객체에 대해 특정 연산자(+, -, *, / 등)를 직접 정의할 수 있게 해줌
```kotlin
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}

fun main() {
    val point1 = Point(1, 2)
    val point2 = Point(3, 4)
    val result = point1 + point2 // plus 함수가 호출됨
    println(result) // Point(x=4, y=6)
}
```