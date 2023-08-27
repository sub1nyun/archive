package com.example.archive.woowa.functionInterface;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Consumer {

    // 자바 8의 표준 api 에서 제공하는 함수형 인터페이스들 알기
    // java.util.function에서 표준 api 패키지로 제공하며
    // 크게 5가지로 Consumer, Supplier, Function, Operator, Predicate가 있다.

    // 1. Consumer 계열
    // 매개값을 전달받아 사용하고 아무것도 반환하지 않을 때 사용 -> 소비(Consume) 한다고 표현하며, accept 추상 메서드를 가지고 있다.

    public static void main(String[] args) {
        // 대표적으로 Stream의 forEach 메소드의 매개변수 타입이 Consumer
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        numbers.stream().forEach(number -> System.out.println(number));
        // 매개 값으로 number를 받고 람다 표현식 내부에서 사용되기만 할 뿐 -> 아무것도 반환하지 않는 것

        // 또한 Map의 forEach 메소드는 Biconsumer 타입을 매개변수로 받는다
        Map<String, Integer> map = Map.of("hudi", 25, "baby", 1);
        map.forEach((name, number) -> System.out.println(name + "는" + number + "살"));



    }
}

class Supplier {

    // 2. Supplier 계열

    // 매개값은 없고 반환값은 있음 -> 실행 후 호출한 곳으로 데이터를 공급(Supply) 한다 -> getxxx 추상 메서드를 가지고 있다.

    public static void main(String[] args) {
        Stream.generate(() -> "Infinite Stream!") // Supplier 전달
                .limit(5)
                .forEach(System.out::println);
        // :: 표현식 -> 메서드 레퍼런스를 사용한 람다 표현식

         }
}

class Function {

    // 3. Function 계열

    // 매개값도 있고, 리턴값도 있다. 주로 매개값을 반환값으로 매핑할 때 즉, 타입 변환이 목적일 때 사용한다. -> applyxxx 추상 메서드를 가지고 있다.

    public static void main(String[] args) {
        // 인터페이스 선언했다고 가정
        //List<Number> numbers = IntStream.rangeClosed(0, 10)
                //.mapToObj(number -> new Number(number)) // IntFunction 전달됨
                //.collect(Collectors.toList());

    }
}

class Operator {

    // 4. Operator 계열

    // Function 과 마찬가지로, 매개값도 있고, 반환값도 있음
    // 주로 매개값은 연산(Operation)하여 결과를 반환할 때 사용하며, Fuction과 마찬가지로 applyxxx 추상 메서드를 가지고있다.

    public static void main(String[] args) {

        // 아래는 BinaryOperator 를 활용하여 컬렉션의 모든 수를 더하는 예시이다.
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        Integer sum = numbers.stream().reduce((acc, cur) -> acc + cur) // BinaryOperator 전달됨
                .get();
    }
}

class Predicate {

    // 5. Predicate 계열

    // 매개값은 있고, 반환 타입은 boolean 이다. 매개값을 받아 검사하고 true 혹은 false를 반환할 때 사용하며 test 추상 메서드를 가지고있다.

    public static void main(String[] args) {
        // Stream의 allMatch 메서드는 매개변수로 Predicate 타입을 전달받아, 컬렉션의 모든 요소가 주어진 조건에 모두일치한다면 true를 반환한다.
        List<Integer> numbers = List.of(10, 20, 25, 15, 30, 35);
        boolean allMatched = numbers.stream().allMatch(number -> number > 5);
    }
}

