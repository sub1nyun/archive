package com.example.archive.woowa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionCopy {
    private int number;

    public CollectionCopy(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CollectionCopy{" +
                "number=" + number +
                '}';
    }

    public static void main(String[] args) {

        List<CollectionCopy> original = new ArrayList<>();
        original.add(new CollectionCopy(1));
        original.add(new CollectionCopy(2));

        // 주소값만 복사
        List<CollectionCopy> copy1 = original;
        // ▲ 위 코드는 복사라고 보기에는 어려움 -> copy1과 original은 동일한 주소값을 바라보기 때문에
        // original의 변경이 가해지면 copy1 에도 바로 영향이 오게 됨

        // 생성자를 통한 복사
        List<CollectionCopy> copy2 = new ArrayList<>(original);
        // 생성자에 original을 넣어서 가지고있는 요소와 동일한 copy2를 만들 수 있음

        // addAll()을 통한 복사
        List<CollectionCopy> copy3 = new ArrayList<>();
        copy3.addAll(original);
        // 생성자 복사와 유사하며 -> 비어있는 copy3 컬렉션을 만들고, original의 모든 요소를 추가함

        // stream을 통한 복사
        List<CollectionCopy> copy4 = original.stream().collect(Collectors.toList());
        // 생성자 복사와 비슷함 -> 자바8 부터 등장한 스트림과 Collectors를 사용하여 -> original과 같은 요소를 갖은 copy4를 생성

        // unmodifiable 을 통한 복사
        List<CollectionCopy> copy5 = Collections.unmodifiableList(original);
        // unmodifiable은 매개변수로 제공된 컬렉션의 읽기 전용인 컬렉션을 반환 -> 수정하면 예외가 발생
        // copy5.add(new CollectionCopy(3)); -> UnsupportedOperationException 발생
        //unmodifiableList 뿐 아니라, unmodifiableSet, unmodifiableMap 도 사용 가능하다.

        // copyOf 를 통한 복사
        List<CollectionCopy> copy6 = List.copyOf(original);
        // copyOf 또한 unmodifiable 과 똑같이 읽기 전용 컬렉션을 반환 -> 무엇이 다를까?

        // 먼저 해시코드 살펴보기
        System.out.println("원본: " + System.identityHashCode(original));
        System.out.println("주소값만 복사: " + System.identityHashCode(copy1));
        System.out.println("생성자 복사: " + System.identityHashCode(copy2));
        System.out.println("addAll: " + System.identityHashCode(copy3));
        System.out.println("stream: " + System.identityHashCode(copy4));
        System.out.println("Collections.unmodifiableList: " + System.identityHashCode(copy5));
        System.out.println("List.copyOf: " + System.identityHashCode(copy6));
        // identityHashCode 는 hashCode와 다르게 오버라이드가 불가능 -> 고유한 해시코드를 리턴하는것을 보장

        /*
            원본: 1927950199
            주소값만 복사: 1927950199
            생성자 복사: 1044036744
            addAll: 1826771953
            stream: 1406718218
            Collections.unmodifiableList: 245257410
            List.copyOf: 1705736037
         */

        original.get(1).setNumber(100);
        // 원본 컬렉션 요소 하나를 임의로 변경

        System.out.println("주소값만 복사: " + copy1.get(1));
        System.out.println("생성자 복사: " + copy2.get(1));
        System.out.println("addAll: " + copy3.get(1));
        System.out.println("stream: " + copy4.get(1));
        System.out.println("Collections.unmodifiableList: " + copy5.get(1));
        System.out.println("List.copyOf: " + copy6.get(1));
        // 모두 number = 100 출력 -> 모든 복사본이 영향을 받음 -> 기본 제공되는 api를 사용하여 컬렉션을 복사한 경우
        // 변경에 취약하지 않은 컬렉션을 만들기 위해서는 내부 요소가 불변 객체여야 한다.
        // 또는 직접 내부 요소까지 복사하는 코드를 직접 작성해야 한다

        // 원본 컬렉션이 바뀐다면?
        // 복사본 컬렉션이 원본 컬렉션과 참조가 완전히 끊겼다는 거슬 확신할 수 있을까?
        original.add(new CollectionCopy(3)); // 원본에 요소 추가 -> 원본 컬렉션 변경
        System.out.println("---------------------------------");
        System.out.println("주소값만 복사: " + copy1);
        // [CollectionCopy{number=1}, CollectionCopy{number=100}, CollectionCopy{number=3}]
        System.out.println("생성자 복사: " + copy2);
        System.out.println("addAll: " + copy3);
        System.out.println("stream: " + copy4);
        /*
          생성자 복사: [CollectionCopy{number=1}, CollectionCopy{number=2}]
          addAll: [CollectionCopy{number=1}, CollectionCopy{number=2}]
          stream: [CollectionCopy{number=1}, CollectionCopy{number=2}]
          원본 요소를 하나씩 꺼내서 새로운 컬렉션으로 만들기 때문에 원본 컬렉션의 변화에 지장을 받지 않는다.
          참조는 같으나, 적어도 복사본 컬렉션 자체는 원본 컬렉션과 관련이 없음
        */

        System.out.println("Collections.unmodifiableList: " + copy5);
        //[CollectionCopy{number=1}, CollectionCopy{number=100}, CollectionCopy{number=3}]
        // 원본 컬렉션의 변화에 영향을 받아 -> number=3가 추가 되었음
        // unmodifiableList 은 원본 객체와의 참조를 끊지 않음 -> 값을 변경할 수 있는 수단을 제공하지 않아서 read-only가 된 것뿐 -> 불변함 보장 x

        System.out.println("List.copyOf: " + copy6);
        // [CollectionCopy{number=1}, CollectionCopy{number=100}]
        // 변화가 없는 것으로 -> 원본 컬렉션과의 참조가 끊긴 상황 -> copyOf()는 얕은 복사를 수행하고 -> unmodifiable로 만들어서 반환 -> 정확히는 ImmutableCollection 을 반환

        // 방어적 복사를 수행하기 위해서는 unmodifiable 대신 copyOf 를 사용해야 완전한 불변함을 보장할 수 있다
    }
}
