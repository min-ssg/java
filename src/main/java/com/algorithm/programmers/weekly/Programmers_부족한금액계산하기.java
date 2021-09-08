package com.algorithm.programmers.weekly;
/**
 * @since 2021.09.07
 * 1주차 부족한 금액 계산하기
 */
public class Programmers_부족한금액계산하기 {

    public long solution (int price, int money, int count) {

        long temp = price * ((count + 1) * count / 2L);
        System.out.println(temp);
        long answer = temp - money;
        if (answer < 0) {
            answer = 0;
        }

        return answer;
    }
}
