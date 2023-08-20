package com.example.archive.woowa;

import java.util.Map;
import java.util.Objects;

public class FactoryMethod {

    // 정적 팩터리 메서드는 클래스의 정적 메서드를 정의하여, 생성자 대신 객체를 생성할 수 있게 만드는 기법

    public static void main(String[] args) {
        Book book1 = Book.createByIsbn(9788966262281L); // 이펙티브 자바 3판
        Book book2 = Book.createByTitle("객체지향의 사실과 오해");
    }

    // 장점으로는 -> 생성자는 클래스와 이름이 동일하여, 여러 생성자를 오버로딩할 경우 이름이 동일하기 때문에
    // 어떤 생성자를 오버로딩해야 하는지 명확하지 않을 수 있으나
    // 1. 팩터리 메서드에서는 이름을 개발자가 지정할 수 있으므로 역할이나 의미를 더 명확하게 표현할 수 있음
    // 2. 객체 생성 시 어떤 추가 적인 로직이 필요한 경우 이를 팩토리 메서드 내에서 처리가 가능함
    // 3. 생성자는 해당 클래스의 인스턴스를 반환하므로 서브클래스의 객체를 생성하는 것이 어려울 수 있지만
    // 서브클래스의 인스턴스를 반환 할 수 있기때문에 유연한 확장이 가능해짐
    // 4. 생성자를 직접 노출하지 않고 -> 정적 팩토리 메서드를 사용하여 API를 제공하는 유여한 방법
    
}

class Book {
    private String title;
    private long isbn;

    // 생성자에 private 키워드를 통하여 new를 통한 직접 객체를 생성하는 것을 막고
    // 정적 메서드를 통해 Book 객체를 생성한 뒤 반환하기
    private Book(String title, long isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    static Book createByIsbn(long isbn) {
        if (isbn == 9788966262281L) {
            return new Book("Effective Java 3/E", isbn);
        } else if (isbn == 9788998139766L) {
            return new Book("객체지향의 사실과 오해", isbn);
        }

        throw new IllegalArgumentException("일치하는 책이 없습니다.");
    }

    static Book createByTitle(String title) {
        if (title.equals("Effective Java 3/E")) {
            return new Book(title, 8966262287L);
        } else if (title.equals("객체지향의 사실과 오해")) {
            return new Book(title, 9788998139766L);
        }

        throw new IllegalArgumentException("일치하는 책이 없습니다.");
    }
}

// 드론(mdms) 프로젝트에서 사용했던 VO 클래스 활용
class ResultVO {
    private Map<String, Object> data;
    private String message;
    // 생성자를 private 로 선언
    private ResultVO(Map<String, Object> data, String message) {
        this.data = data;
        this.message = message;
    }

    public static ResultVO createWithMap(Map<String, Object> data, String message) {
        return new ResultVO(data, message);
    }
}

