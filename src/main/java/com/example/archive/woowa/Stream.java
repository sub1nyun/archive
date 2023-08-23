package com.example.archive.woowa;

import java.util.Iterator;
import java.util.List;

public class Stream {

    // Stream 이란 -> JDK 8 버전부터 제공된 컬렉션 혹은 배열에 저장된 요소를 하나씩 참조하여 람다 표현식으로 처리할 수 있는 반복자로
    // 스트림이 존재하기 이전에는 Iterator 인터페이스를 사용했음
    // 컬렉션(리스트, 세트, 맵 등) 다른 데이터 소스의 원소들을 처리하기 위한 고수준의 추상화된 인터페이스

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        Iterator iterator = numbers.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Stream을 이용한 반복 처리
        List<Integer> numbers2 = List.of(1,2,3,4,5,6);
        // 클래스 이름 때문에 따로 임포트 함
        java.util.stream.Stream<Integer> stream = numbers2.stream();
        stream.forEach(number ->System.out.println(number));

        // 스트림은 내부 반복자 (Internal Iterator)를 사용하여 병렬처리에 적합
        // 외부 반복자 (External Iterator)는 개발자가 직접 컬렉션에서 요소를 반복해서 가져오는 코드 패턴을 의미함 hasNext

        // Stream 의 특징
        // 1. 람다 표현식 -> 스트림이 제공하는 대부분의 요소 처리 메서드는 함수형 인터페이스를 사용하므로, 람다식으로 요소 처리 코드를 제공할 수 있음
        // 2. 생성, 중간처리, 최종처리 -> 3단계로 구분
        // 3. 재사용이 불가능 -> 스트림은 생성되고, 중간처리를 거쳐 최종처리까지 완료되면 닫히게 되며 -> 재사용을 할 수 없고 시도하면 예외가 발생 -> 스트림은 일회용

        List<Integer> numbers3 = List.of(10, 20, 25, 15, 30, 35);
        java.util.stream.Stream<Integer> integerStream = numbers3.stream()
                .filter(number -> number > 20);

        integerStream.count();
        integerStream.count();
        // java.lang.IllegalStateException: stream has already been operated upon or closed

        // 4. 원본 데이터를 변경하지 않는다 -> 스트림은 원본 객체의 값을 사용하기만 할 뿐 변경하지 않음 -> 최종 처리를 통해서 원본과 무관한 새로운 객체를 생성한다.

    }
}

// 스트림을 사용하기
// 1. 제일 먼저 스트림을 생성해야 함
// 2. 그 다음 필터링, 매핑, 정렬, 그룹핑 등의 처리 등의 중간 처리를 통해서 데이터를 가공
// 3. 가공된 데이터로부터 합계, 평균, 최대값, 최소값, 카운팅 등 최종 처리를 통해 집계할 수 있음 -> 3단계로 구분 -> 파이프라인으로 처리 -> 최종 스트림의 처리가 실행될 때 까지 모든 중간 스트림은
// Lazy 되는 특징이 있음

class Student {
    private final int grade;
    private final int score;

    public Student(int grade, int score) {
        this.grade = grade;
        this.score = score;
    }

    public int getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    public static void main(String[] args) {

        List<Student> students = List.of(
                new Student(2, 100),
                new Student(3, 50),
                new Student(1, 56),
                new Student(2, 90),
                new Student(3, 90),
                new Student(2, 100),
                new Student(1, 30)
        );

        double averageScore = students.stream() // stream 생성
                .filter(student -> student.getGrade() == 3) // 필터링 (중간)
                .mapToInt(student -> student.getScore()) // 매핑 (중간)
                .average() // 평균 집계 (최종처리)
                .getAsDouble();

        System.out.println("평균 성적: " + averageScore); // 70.0
    }
}
