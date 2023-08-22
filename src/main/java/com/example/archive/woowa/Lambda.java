package com.example.archive.woowa;

public class Lambda {

    // 인터페이스를 사용할 때 자바스크립트의 화살표 함수와 비슷한 문법을 사용했다.
    // 자바도 자바스크립트처럼 함수 자체가 객체일 수 있는 것일까?

    // (매개변수) -> { 실행 코드 }
    // 자바의 메소드는 무조건 클래스의 구성 멤버여야 하는데 -> 실제로 람다 표현식을 작성하면
    // 메소드가 독립적으로 생성되는 것이 아니라, 런타임 시 메소드를 하나만 가지고 있는 '익명 객체'가 생성이 되는 것

    // 1. 기본 형태
    // (int x, int y) -> {return x+y;}
    // () 괄호 안에는 타입과 매개변수를 나열하고 -> {} 안에 실행될 코드를 작성한다

    // 2. 타입 생략
    // (x, y) -> {return x+y;}
    // 보통의 경우 대입되는 시점에서 매개변수의 타입을 추론할 수 있으므로, 타입을 생략해서 사용이 가능하다

    // 3. 매개변수가 없을 경우
    // () -> {System.out.println("Hello World");}
    // 매개변수가 없을경우 반드시 아래와 같이 빈 괄호() 를 사용해야한다

    // 4. 하나의 매개변수만 있는 경우
    // x -> {System.out.println(x);}
    // 하나의 매개변수만 있는 경우에는 아래와 같이 괄호를 생략하여 작성할 수 있다.

    // 5. 한줄의 실행 코드만 있는 경우
    // () -> System.out.println("Hello World");
    // 실행 코드가 한 줄만 있는 경우 중괄호를 생략하여 작성할 수 있다.

    // 6. 실행 코드에 반환문만 있는 경우
    // (x,y) -> x + y
    // 실행 코드에 반환문이 하나만 존재하는 경우 중괄호와 return 키워드를 생략할 수 있다.

    public static void main(String[] args) {
        SomeClass some = new SomeClass();
        some.someMethod(new OtherClass());
        some.someMethod(() -> {
            System.out.println("넹?");
        });
    }

}
// 함수형 인터페이스란 -> 한개의 추상 메소드가 정의된 인터페이스를 의미한다.
interface FiInterface {
    public void run();
}
// ▲ 함수형 인터페이스에서 run 이라는 메서드가 하나만 정의되어 있다면?
class SomeClass {
    public void someMethod(FiInterface fi) {
        fi.run();
    }
}
// SomeClass의 메서드는 FiInterface 타입의 fi 변수를 받아온뒤 사용해보기
class OtherClass implements FiInterface {

    @Override
    public void run() {
        System.out.println("실행됨");

    }
}

/*
두개 이상의 추상 메소드가 선언된 인터페이스는 람다식으로 구현 객체를 만들 수 없다.
즉, 함수형 인터페이스를 작성하려면 인터페이스에 하나의 추상 메소드만 정의해야한다.
그런데, 함수형 인터페이스로 사용되던 인터페이스에 누군가 실수로 추상 메소드를 하나 더 정의했다면 문제가 생길 것 이다.
이런 문제를 컴파일러가 체킹할 수 있도록 만들어주는 어노테이션이 존재하는데 그것이 바로 @FunctionalInterface 어노테이션이다.
인터페이스에 해당 어노테이션을 달아주면, 해당 인터페이스는 두개 이상의 추상 메소드를 가질 수 없다.

@FunctionalInterface
public interface FiInterface {
    public void run();
    public void secondMethod(); // 컴파일 에러
}
 */





