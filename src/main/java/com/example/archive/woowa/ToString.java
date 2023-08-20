package com.example.archive.woowa;

public class ToString {

    // toString 메서드의 일반 규약은 '간결하면서 사람이 읽기 쉬운 형태의 유익한 정보'를 반환하는 것

    // toString 이 잘 구현된 클래스는 사용하기 편하고, 디버깅이 쉽다. 객체를 출력하기만 하면,
    // 객체가 가지고 있는 모든 정보를 확인할 수 있다. 우리가 직접 호출하지 않아도,
    // 로깅을 하거나 에러메세지를 출력할 때에도 유용하게 사용할 수 있다.

    final String name;
    final int position;

    public ToString(String name, int position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format("ex(이름 = %s, 위치 = %s)", name, position);
    }

    // toString 메서드는 언제 사용하는가?
    /*
        Stackoverflow 의 Is it ok to add toString() to ease debugging? 를 읽어보면,
        toString 은 디버깅을 위해 설계된 메소드라고 한다.
        어떤 문제가 발생한 클래스가 toString 이 잘 구현된 클래스일 경우
        스스로를 완벽히 설명하는 문자열이 로깅될 것이고 그렇지 않을 때 보다 훨씬 더 원인을 발견하기 쉬워질 것이다.
     */


}
