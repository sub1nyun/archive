package com.example.archive.woowa;

public class StrategyPattern {

    // 전략패턴이란,
    // 비슷한 동작을 하지만 다르게 구현되어 있는 행위(전략)들을 공통의 인터페이스를 구현하는 각각의 클래스로 구현하고,
    // 동적으로 바꿀 수 있도록 하는 패턴 -> 직접 행위에 대한 코드를 수정할 필요 없이 전략만 변경하여 유연 하게 확장할 수 있다.

    public static void main(String[] args) {
        // 밑에서 생성한 인터페이스 과정을 거쳐 코드를 개선
        Character2 warrior = new Character2(new WarriorAttackStrategy());
        warrior.attack();
    }

}

class Character {
    private final String job;

    Character(String job) {
        this.job = job;
    }

    void attack() {
        if (job.equals("warrior")) {
            System.out.println("커다란 대검을 휘두른다!");
        } else if (job.equals("thief")) {
            System.out.println("작은 단도로 빠르게 두번 찌른다!");
        } else if (job.equals("magician")) {
            System.out.println("파이어볼을 발사한다!");
        }
    }
    // 궁수 직업군이 추가되면 아래와 같이 메서드가 변경이 되어야함 ▼
    void attackUpdate() {
        if (job.equals("warrior")) {
            System.out.println("커다란 대검을 휘두른다!");
        } else if (job.equals("thief")) {
            System.out.println("작은 단도로 빠르게 두번 찌른다!");
        } else if (job.equals("magician")) {
            System.out.println("파이어볼을 발사한다!");
        } else if (job.equals("archer")) {
            System.out.println("활을 발사한다!");
        }
    }
    // 이와 같은 구조는 기능을 확장하기 위해서 -> 수정이 필요한 구조
    // 객체지향 5원칙인 SOLID 중 개방-폐쇄 원칙(OCP: Open-Closed Principle)의 위배
}

// 전략 패턴을 사용하기 위해서 전략에 대한 인터페이스를 먼저 구성하기
interface AttackStrategy {
    String getAttackMessage();
}

// 각 직업군 별 공격 전략을 클래스로 구현 -> 모두 AttackStrategy 구현체
class WarriorAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "커다란 대검을 휘두른다!";
    }
}

class ThiefAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "작은 단도로 빠르게 두번 찌른다!";
    }
}

class MagicianAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "파이어볼을 발사한다!";
    }
}

class ArcherAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "활을 발사한다!";
    }
}
// 마지막 -> 새로운 직업군으로 해적이 추가 됐다면?
class PirateAttackStrategy implements AttackStrategy {
    public String getAttackMessage() {
        return "총을 발사한다!";
    }
}

// 전략에 대한 구현체를 작성했다면 아래와 같이 변경됨
class Character2 {
    private final AttackStrategy attackStrategy;

    public Character2(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
    // Character2의 인스턴스가 생성될 때, 인자를 통해 외부로부터 전략을 주입받고, attack 메서드에서 AttackStrategy 인터페이스가 제공하는 getAttackMessage 메서드를 호출하여
    // 공격메세지를 받아올 수 있다 -> 이를 의존성 주입 (Dependency Injection) 이라고 한다.
    void attack() {
        System.out.println(attackStrategy.getAttackMessage());
    }
}


