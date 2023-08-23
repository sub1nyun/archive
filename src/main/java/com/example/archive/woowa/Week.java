package com.example.archive.woowa;

public enum Week {

    // 열거 타입
    // -> 한정된 값만을 갖는 데이터 타입을 의미한다. -> 서로 연관있는 상수를 편리하게 관리하기 위해 사용한다.

    // 열거 타입의 객체도 인스턴스이므로 -> 필드를 가질 수 있음
    MONDAY("월요일", "月"),
    TUESDAY("화요일", "火"),
    WEDNESDAY("수요일", "水"),
    THURSDAY("목요일", "木"),
    FRIDAY("금요일", "金"),
    SATURDAY("토요일", "土"),
    SUNDAY("일요일", "日");
    // 요일을 열거 타입으로 상수화 해놓은 예시로 -> '열거 상수'라고 표현한다.

    public String korean;
    public String chinese;

    Week(String korean, String chinese) {
        this.korean = korean;
        this.chinese = chinese;
    }

    // 자바에서의 열거 타입은 일종의 클래스로 -> 상수 하나당 인스턴스를 하나씩 만들어서 public static final 필드로 공개한다.
    // 열거 타입의 인스턴스는 클라이언트가 직접 생성할 수 없고, 런타임에 단 한번만 생성된다. -> 싱글톤 보장

    // 모든 필드가 public 이기때문에 = "~" 할당을 통해서 수정이 가능함

    // 메소드를 지원하기때문에 -> 필드를 private로 닫고 Getter를 제공하여 .getKorean() 호출이 가능함
    public String getKorean() {
        return korean;
    }

    public String getChinese() {
        return chinese;
    }
}

class Some {

    Week today = Week.MONDAY; // 열거 타입도 데이터 타입의 일종으로 변수를 선언하고 대입할 수 있음
    Week holiday = null; // null을 저장할 수 있는데 -> 열거 타입 또한 참조 타입이기 때문

    // 참조 타입은 객체를 참조하는 변수 -> 열거 상수는 '열거 객체'로 생성됨을 알 수 있음
    public static void main(String[] args) {

        Week today2 = Week.MONDAY;
        System.out.println(today2.name());

        // ordinal -> 해당 열거 객체가 몇 번째 순번인지를 반환한다 -> 0 부터 시작
        System.out.println(Week.MONDAY.ordinal()); // 0
        System.out.println(Week.SUNDAY.ordinal()); // 6

        // compareTo -> 두 열거 객체간의 순번을 비교하여 상대적 순번 차이를 반환
        System.out.println(Week.MONDAY.compareTo(Week.SUNDAY)); // -6
        System.out.println(Week.SATURDAY.compareTo(Week.WEDNESDAY)); // 3

        // valueOf -> 상수명과 동일한 문자열을 입력받아 일치하는 열거 객체를 반환
        Week yesterday = Week.valueOf("WEDNESDAY");
        System.out.println(yesterday); // WEDNESDAY

        // values -> 열거 타입에 선언된 모든 열거 객체를 순번 순서대로 배열을 만듬
        Week[] days = Week.values();
        for(Week day : days) {
            System.out.println(day);
        }
        /*
            MONDAY
            TUESDAY
            WEDNESDAY
            THURSDAY
            FRIDAY
            SATURDAY
            SUNDAY
         */

        Week[] days2 = Week.values();
        for (Week day : days2) {
            System.out.println(day.korean + " (" + day.chinese + ")");
        }
        /*
            월요일 (月)
            화요일 (火)
            수요일 (水)
            목요일 (木)
            금요일 (金)
            토요일 (土)
            일요일 (日)
        */

    }
}
