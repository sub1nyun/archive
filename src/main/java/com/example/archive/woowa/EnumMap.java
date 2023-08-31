package com.example.archive.woowa;

public class EnumMap {

    /* 키(Key) 를 Enum 타입만으로 사용하는 Map 인터페이스의 구현체이다.
        Map 인터페이스를 구현하므로, 일반적인 Map의 메서드를 전부 사용할 수 있다.
        또한 Enum 을 키로 사용하는 맵이 있다면, 성능상 이점을 위해 HashMap 대신
        EnumMap 을 사용하라는 이야기가 있을 정도
     */

    // Enum 은 상수의 선언 순서대로 일정한 순번 값을 갖는다. rodinal() -> 해당 객체의 순번 가져옴
    // 겹치지 않는 일정한 순번을 가지고 있으니, 해싱을 할 필요도, 해시 충동을 처리할 필요도 없음
    // EnumMap 의 경우 전달된 Enum 타입의 상수 개수만큼만 저장공간을 확보 하기때문에
    // 리사이징이 필요없다.

    public static void main(String[] args) {

        // EnumMap 생성하기
        // EnumMap<Week, String> enumMap = new EnumMap<>(Week.class);
        // EnumMap 은 일반 Map 과 다르게, 생성자에 Enum 타입을 직접 전달해야한다.

        // EnumMap, HashMap -> 21억번 반복했을때 실행 시간 차이

        //EnumMap 소요시간 (초) : 6.964324458
        //HashMap 소요시간 (초) : 16.0586595

        /*
            EnumMap 이 2배 ~ 3배 가량 빠른 속도를 가지고 있는 것을 알 수 있었다.
            따라서 Enum 을 키로 갖는 Map 을 사용할 일이 있다면,
            무조건 EnumMap 을 사용하는 것이 성능상으로 이점이란 것을 알 수 있다
         */

    }


}

public enum Week {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
// Enum 타입의 각 열거 객체는 선언 순서대로 고유의 순번을 갖게 됨
// HashMap 은 키의 순서가 보장되지 않음

/*
    EnumMap<Week, String> enumMap = new EnumMap<>(Week.class);
    HashMap<Week, String> hashMap = new HashMap<>();

    enumMap.put(Week.WEDNESDAY, "수요일");
    hashMap.put(Week.WEDNESDAY, "수요일");

    enumMap.put(Week.MONDAY, "월요일");
    hashMap.put(Week.MONDAY, "월요일");

    enumMap.put(Week.SUNDAY, "일요일");
    hashMap.put(Week.SUNDAY, "일요일");

    enumMap.put(Week.FRIDAY, "금요일");
    hashMap.put(Week.FRIDAY, "금요일");

    enumMap.put(Week.TUESDAY, "화요일");
    hashMap.put(Week.TUESDAY, "화요일");

    System.out.println("EnumMap : " + enumMap);
    System.out.println("HashMap : " + hashMap);
 */

// 실행결과
/*
    EnumMap : {MONDAY=월요일, TUESDAY=화요일, WEDNESDAY=수요일, FRIDAY=금요일, SUNDAY=일요일}
    HashMap : {WEDNESDAY=수요일, FRIDAY=금요일, TUESDAY=화요일, MONDAY=월요일, SUNDAY=일요일}

    EnumMap 은 Enum 클래스에 선언된 순서대로 정렬되는 것을 확인할 수 있지만,
    HashMap 은 입력 순서도, Enum 선언 순서도 아닌 기준으로 정렬되었다.
 */

