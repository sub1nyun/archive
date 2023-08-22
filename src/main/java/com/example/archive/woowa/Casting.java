package com.example.archive.woowa;

public class Casting {

    public static void main(String[] args) {

        // 타입 캐스팅이란 -> 상대적으로 작은 크기의 타입에 큰 크기의 타입의 데이터를 저장하기 위해 사용
        int intValue = 3;
        long longValue = intValue;
        // int 타입은 long 타입에 비해 저장 용량이 작음 -> 자동으로 변환해줌
        // 반대는 불가능 하기 때문에 캐스팅 연산자 (타입명)을 통해 강제로 형변환 해야한다
        long longValue2 = 3;
        int intvalue2 = (int) longValue2;

        // 큰 상자를 작은 사앚에 집어 넣을 수 없다. -> 큰 상자를 몇조각으로 잘라서 작은상자에 일부분 넣을 수는 있는데 -> 손실이 발생하게 됨
        // int (4byte)의 데이터를 byte 타입(1byte) 로 강제 타입 변환한다면 -> 1byte 단위로 잘라야함
        int intValue3 = 123456780; //00000111 01011011 11001101 00001100
        byte byteValue = (byte) intValue3; // 00001100

        System.out.println(intValue3); // 123456780
        System.out.println(byteValue); // 12

        // 123456780을 이진법으로 표현한다면 0000011101011011110011010000110
        // 1byte 단위로 끊으면, 00000111,
        //01011011
        //01011011,
        //11001101
        //11001101,
        //00001100
        //00001100 이렇게 4개로 분리
        /*
            이 중 가장 뒤에 있는
            00001100
            00001100 (즉 10진법으로는
            12
            12) 가 byteValue 에 할당되어 byteValue 를 출력하면
            12
            12 가 나오는 것을 알 수 있다
            즉, 타입 캐스팅은 잠재적으로 데이터 손실 위험성을 가지고 있음
         */

        // 작은 타입에 저장하기 위해 큰 타입의 변수를 캐스팅 할 때, 큰 타입의 변수가 가지고 있는
        // 데이터의 크기가 작은 타입에도 저장될 수 있는 용량인지 확인해야함

        /*
        기본 타입	최대값 상수	최소값 상수
        byte	Byte.MAX_VALUE	Byte.MIN_VALUE
        short	Short.MAX_VALUE	Short.MIN_VALUE
        int	Integer.MAX_VALUE	Integer.MIN_VALUE
        long	Long.MAX_VALUE	Long.MIN_VALUE
        float	Float.MAX_VALUE	Float.MIN_VALUE
        double	Double.MAX_VALUE	Double.MIN_VALUE
         */

        int intValue4 = 123456780;

        if (intValue4 < Byte.MIN_VALUE || intValue4 > Byte.MAX_VALUE) {
            throw new IllegalArgumentException("byte 타입으로 변환될 수 없습니다.");
        }
        byte byteValue2 = (byte) intValue4;


    }


}
