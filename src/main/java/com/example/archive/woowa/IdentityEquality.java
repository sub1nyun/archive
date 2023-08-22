package com.example.archive.woowa;

import java.util.Objects;

public class IdentityEquality {

    // 동일성 - Identity
    // 동등성 - Equality

    private int number;

    public IdentityEquality(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentityEquality that = (IdentityEquality) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public static void main(String[] args) {
        // 동일성은 비교 대상의 두 객체의 메모리 주소가 같음을 의미하는 것 -> == (비교연산자)로 확인할 수 있음
        IdentityEquality number1 = new IdentityEquality(1);
        IdentityEquality number2 = number1;

        System.out.println(number1 == number2); // true
        // number2는 IdentityEquality의 인스턴스를 새로 생성하지 않고, number1 을 대입받음
        // 즉, number1과 number2는 같은 메모리에 위치한 객체를 바라보고 있음

        // 동등성은 비교 대상의 두 객체가 논리적으로 동일한 값을 나타내고 있는지를 검사한다.
        // 자바에서는 동등성을 비교하기 위해 equls와 hashCode를 오버라이드해서 사용
        // 동등성에서는 두 객체의 메모리 주소는 중요하지 않음
        IdentityEquality number3 = new IdentityEquality(1);
        System.out.println(number1.equals(number3)); // true

        // number1과 number3는 각각 다른 메모리 주소를 가르키지만 두 객체가 같은 값을 의미하고 있기때문에 true를 반환함
        System.out.println(number1 == number3); // false
        // 같은 객체가 아니기 때문에 false






    }



}
