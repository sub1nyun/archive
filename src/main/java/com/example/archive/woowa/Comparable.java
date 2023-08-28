package com.example.archive.woowa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Comparable implements java.lang.Comparable<Number> {

    // Comparable 인터페이스
    // 객체가 임의의 기준으로 정렬될 수 있도록 만들고 싶을 때 사용 -> 객체를 '정렬 가능하도록' 만들기 위해 사용되는 인터페이스
    // 구현하는 클래스는 compareTo 메서드를 오버라이딩 해야하며 -> 인자로 객체를 받는다
    // 현재 객체가 인자로 받아온 객체보다 앞라면 -> 양수 반환
    // 현재 객체가 인자로 받아온 객체보다 뒤라면 -> 음수 반환
    // 둘이 같다면 -> 0 반환

    // Integer, Double, String 은 모두 Comparable을 구현하고 있어 정렬이 가능함

    private final int value;

    public Comparable(int value) {
        this.value = value;
    }

    // 사용자 정의 클래스는 직접 compareTo 메서드를 오버라이드 해야한다.
    @Override
    public int compareTo(Number other) {
        if(this.value > other.intValue()) {
            return 1;
        }
        if(this.value < other.intValue()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {
        List<Comparable> numbers = new ArrayList<>(List.of(new Comparable(5), new Comparable(2), new Comparable(12), new Comparable(22)
        , new Comparable(9), new Comparable(17), new Comparable(6)));

        Collections.sort(numbers);



    }
}

class Number {

    // Comparator 인터페이스

    /*Comparable 은 객체 자신이 정렬 가능하도록 구현하는데 목적이 있다면,
    Comparator 는 타입이 같은 객체 두개를 매개변수로 전달받아 우선순위를 비교할 수 있는 정렬자를 만드는데 그 목적이 있다.
    Comparator 를 구현하기 위해서는 compare(T o1, T o2) 메소드를 오버라이딩 하면 된다. 반환값은 아래의 기준을 따른다.

    이미 객체 자기 자신이 우선순위를 계산할 수 있는 Comparable 인터페이스가 존재하는데,
    제 3자가 둘의 우선순위를 비교하는 기능이 필요할까? 첫번째로는 정렬 대상 클래스의 코드를 수정할 수 없을 경우,
    두번째로는 정렬 대상 클래스에 이미 정의된 compareTo 와 다른 기준으로 정렬하고 싶을 경우, 세번째로 여러 정렬 기준을 적용하고 싶을 경우 사용한다.
     */
    
    /* 내림차순 예시
    class DescendingNumberComparator implements Comparator<Number> {
    @Override
    public int compare(Number number1, Number number2) {
        return number2.getValue() - number1.getValue();
    }
}

     */
}
