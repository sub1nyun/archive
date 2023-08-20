package com.example.archive.woowa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalKeyword {
    // ▼ 클래스의 필드로 선언하기
    // 접근제어자와 static 키워드를 함께 사용한다면 -> 컴파일 타임에 메모리 할당을 단 한번 하기 때문에 효율성 측면에서 사용하기 용이함
    private static final int MOVE_STEP = 1;
    public static void main(String[] args) {
        final int number = 1;
        //number= 2; // Cannot assign a value to final variable 'number'
        // ▲ 위 같이 final로 설정된 number 변수를 재할당 시도 시 컴파일 에러가 발생한다.

        // final 키워드를 사용하여 변수 초기값을 설정하는 방법은 두 가지로 1. 클래스의 필드로 선언 2. 생성자로 선언
        // 인자 자체에 final 키워드를 사용하여, 메소드 내부에서 인자를 변경 할 수 없도록
        // 부모클래스에서 final 키워드로 선언된 메서드는 자식 클래스에서 오버라이딩 할 수 없다.
        // '메서드'에 final 키워드를 사용하는 경우는 코어 부분에서 변경을 원치 않는 메소드를 명시 할 때!
        // '클래스'에 final 키워드를 사용한다면, 상속할 수 없는 클래스가 된다 -> 부모 클래스가 될 수 없음
        // '추상클래스' abstract class 는 상속을 통해 하위 클래스에서 메서드 구현을 강제하는 목적이기에 final 키워드는 서로 상충되기에 동시 사용 불가능

        String f = null; // public final class String... -> 대표적으로 String 클래스가 final 키워드를 사용함

        // 그렇다면 final은 불변을 의미하는가?
        // 완벽한 불변성을 의미하지는 않는다. -> 해당 변수의 재할당만을 막아줄 뿐, 참조하고 있는 객체 내부의 상태가 변하지 않았음은 보장 x

        // 예시
        final List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3));
        System.out.println(numbers); // [1,2,3] 출력

        numbers.add(4);
        System.out.println(numbers); // [1,2,3,4] 출력
        // final 키워드가 사용되었지만, 내부의 값이 변경이 됨 -> final 단독으로는 완벽히 불변성을 지킬 수 없음

    }
}
