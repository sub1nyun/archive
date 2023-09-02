package com.example.archive.woowa;

public class Optional {

    // 존재하지 않는 값을 표현하기 위해 null 이라는 개념을 도입하였다.

    // 실제로 스토어에 등록된 상위 1000개 앱의 충돌 원인 2위가 NullPointer 문제!

    public static void main(String[] args) {




}

class Article {
    private String title;
    private String content;
    private User user;

    // 생성자 및 Getter 메소드 생략
}

class User {
    private Name name;

    // 생성자 및 Getter 메소드 생략
}

class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    // 생성자 및 Getter 메소드 생략
}

// 각 middleName이 null 인지 선행으로 검사를 해야한다
    /*

    // if문으로 체크한다면 아래와 같다
    public String getUserMiddleName(article) {
        if (article != null) {
            User user = article.getUser();
            if (user != null) {
                Name name = user.getName();
                if (name != null) {
                    String middleName = name.getMiddleName();
                    if (middleName != null) {
                        return middleName;
                    }
                }
            }
        }

        return "";
    }

}
*/

// 조금 개선한 단계
    /*
public String getUserMiddleName(Article article) {
    if (article == null) {
        return "";
    }

    User user = article.getUser();
    if (user == null) {
        return "";
    }

    Name name = user.getName();
    if (name == null) {
        return "";
    }

    String middleName = name.getMiddleName();
    if (middleName == null) {
        return "";
    }

    return middleName;
}
*/

// Optional을 사용해서 개선해보기
public String getUserMiddleNameWithOptional(Article article) {
    String middleName = Optional.ofNullable(article)
            .map(Article::getUser)
            .map(User::getName)
            .map(Name::getMiddleName)
            .orElse("");

    return middleName;
}

// Optional 이란 null 이 될 수도 있는 객체를 포장하는 Wrapper 클래스로 -> 예상치 못한 NPE가 발생하지 않도록 도와준다.
// 존재할 수도 하지 않을 수도 있는 객체를 표현하기 위한 클래스

// #생성 메서드
// 1. empty()
Optional.empty(); // 아무 값도 들어있지 않는 빈 Optional을 반환한다.

// 2. of(T value)
String text = "Hello, Optional";
    Optional<String> optional = Optional.of(text);

    text = null;
    Optional<String> optionalWithNPE = Optional.of(text);
// java.lang.NullPointerException
// null이 아닌 값을 Optional로 포장해서 반환한다 -> 절대 null 이 되지 않는 값을 전달할 필요가 있다.

// 3. ofNullable(T value)
// null이 될수도 있는 값을 전달하면 Optional로 포장해서 반환 -> of() 와 다르게 null 을 전달해도 NPE를 던지지 않는다.
String text = "Hello, Optional";
Optional<String> optional = Optional.ofNullable(text);

text = null;
Optional<String> optionalWithoutNPE = Optional.ofNullable(text);

// #중간 처리 메서드
// 1. filter(Predicate<? super T> predicate)
// Predicate 를 전달하여, 가지고 있는 값이 조건에 맞지 않는다면 Optional 을 비워버린다.
int adultAge = 25;
    int kidAge = 10;

    Optional<Integer> optional = Optional.ofNullable(adultAge)
            .filter(number -> number > 20);
// Optional[25]

    Optional<Integer> emptyOptional = Optional.ofNullable(kidAge)
            .filter(number -> number > 20);
// Optional.empty
// optional 은 그대로 25라는 값을 가지고 있지만, emptyOptional 은 빈 것을 확인할 수 있다.

// 2. map(Function<? super T, ? extends U> mapper)
// Function 을 전달하여, 가지고 있는 값을 매핑한다. Optional 이 값을 갖고 있지 않다면 아무일도 하지 않는다. 위 예제 코드에서 사용된 메소드이다.
Optional.ofNullable(article)
            .map(Article::getUser)
  .map(User::getName)
  .map(Name::getMiddleName);
// Optional[John]

// 3. flatMap(Function<? super T, ? extends Optional<? extends U>> mapper) Nested 된 Optional 을 평평(Flat) 하게 만들어준다.
Optional<Optional<String>> optional = Optional.of(Optional.of("hello"));

    Optional<Optional<String>> nested = optional.map(it -> it);
    Optional<String> flat = optional.flatMap(it -> it);

// 4. stream() 자바 9부터 지원되는 메소드이다. Optional 이 비어있지 않다면, 해당 값을 가진 Stream 을 생성하고, 비어있다면 빈 Stream 을 생성한다.
User user = new User(name);
    Stream<User> stream = Optional.of(user).stream();


/*
    종단 처리 메소드
// 1. get()

Optional 에 값이 있다면 해당 값을 반환하고, 그렇지 않으면 NoSuchElementException 을 던진다.

Name name = new Name("Jack", "John", "Smith");
User user = new User(name);
Article article = new Article("안녕하세요.", "반갑습니다.", user);

String middleName = Optional.ofNullable(article)
      .map(Article::getUser)
      .map(User::getName)
      .map(Name::getMiddleName)
      .get();
// John
// 2. orElse(T other)

Optional 에 값이 존재하면 해당 값을 반환하고, 없다면 파라미터로 전달된 값을 반환한다.

Name name = new Name("Jack", "John", "Smith");
String middleName = Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .orElse("empty");
// John

Name nameWithoutMiddleName = new Name("Jack", null, "Smith");
String emptyMiddleName = Optional.ofNullable(nameWithoutMiddleName)
      .map(Name::getMiddleName)
      .orElse("empty");
// empty
// 3. orElseGet(Supplier<? extends T> supplier)

Optional 에 값이 존재하면 해당 값을 반환하고, 없다면 파라미터로 전달된 Supplier 의 반환값을 반환한다.

Name name = new Name("Jack", "John", "Smith");
String middleName = Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .orElseGet(() -> "empty");
// John

Name nameWithoutMiddleName = new Name("Jack", null, "Smith");
String emptyMiddleName = Optional.ofNullable(nameWithoutMiddleName)
      .map(Name::getMiddleName)
      .orElseGet(() -> "empty");
// empty
// 4. orElseThrow(Supplier<? extends X> exceptionSupplier)

Optional 에 값이 존재하면 해당 값을 반환하고, 없다면 파라미터로 전달된 Supplier 가 반환하는 예외를 던진다. 파라미터에 아무것도 없다면, NoSuchElementException 를 던진다.

Name name = new Name("Jack", null, "Smith");
String middleName = Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .orElseThrow();
// java.util.NoSuchElementException: No value present
Name name = new Name("Jack", null, "Smith");
String middleName = Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .orElseThrow(() -> new NoSuchElementException("미들네임이 존재하지 않습니다!"));
// java.util.NoSuchElementException: 미들네임이 존재하지 않습니다!

// 5. or(Supplier<? extends Optional<? extends T>> supplier)

자바 9부터 지원되는 메소드이다. orElse, orElseGet 과 비슷한 역할을 하지만 Optional 을 반환한다는 차이점이 있다.

아래 예제는 유저가 이름이 없을 경우 데이터베이스에서 인기 있는 이름을 가져오고, 그것마저 없을 경우 "Jack John Smith" 라는 이름을 반환하는 코드이다.

User user = new User(name);
Name name = Optional.of(user)
      .map(it -> it.getName())
      .or(() -> Optional.of(getPopularNameFromDatabase()))
      .orElse(new Name("Jack", "John", "Smith"));

System.out.println(name);

// 6. ifPresent(Consumer<? super T> consumer)

Optional 에 값이 존재하면 파라미터로 전달된 Consumer 를 호출하고, 그렇지 않으면 아무것도 하지 않는다.

Name name = new Name("Jack", "John", "Smith");
Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .ifPresent(middleName -> System.out.println("미들네임은 " + middleName));
// 미들네임은 John

// 7. ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)

자바 9부터 지원되는 메소드이다.

Optional 에 값이 존재하면 첫번째 파라미터로 전달된 Consumer 를 호출하고, 그렇지 않으면 두번째 파라미터로 전달된 Runnable 을 실행한다.

Name name = new Name("Jack", null, "Smith");
Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .ifPresentOrElse(
              middleName -> System.out.println("미들네임은 " + middleName),
              () -> System.out.println("미들네임이 존재하지 않습니다.")
      );
// 미들네임이 존재하지 않습니다.

// 8. isPresent()

Optional 에 값이 존재하면 true, 그렇지 않으면 false 를 반환한다.

Name name = new Name("Jack", null, "Smith");
Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .isPresent();
// false


// 9. isEmpty()

자바 11부터 제공되는 메소드이다.

Name name = new Name("Jack", null, "Smith");
Optional.ofNullable(name)
      .map(Name::getMiddleName)
      .isEmpty();
// true

 */