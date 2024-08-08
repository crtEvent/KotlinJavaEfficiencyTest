# 이중 상속
- interface에 default 매소드로 메서드 내부를 구현할 수 있다
- 같은 시그니처의 default 메서드를 이중 상속하게 되면 어떻게 될까?
- 당연 에러남 `multiple_inheritance.MIChild inherits unrelated defaults for doSomething() from types multiple_inheritance.MIParent1 and multiple_inheritance.MIParent2`

```java
class MIChild implements MIParent1, MIParent2 {

    @Override
    public void doSomething() {
        MIParent1.super.doSomething();
        MIParent2.super.doSomething();
    }
}
```
- 오버라이딩 해서 어느 인터페이스의 default 메서드를 쓸 건지 정해줄 수 있다.
- 템플릿 메서드 패턴도 인터페이스로 구현 가능할 듯
- 추상 클래스와의 차이는
  - 인터페이스는 public 이외의 필드(인스턴스 변수)를 가질 수 없음
    (인터페이스의 모든 멤버변수는 public static final 만 선언할 수 있다. 생략 가능함)
  - 추상 클래스는 다중 상속 불가능
