package com.algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Minseok
 * @since 2021.09.23
 * 제목: 거스름돈
 * 난이도: 실버5
 * 시간제한: 2초
 * 메모리제한: 512MB
 */
public class Main_Baekjoon_14916 {

    private static int N; // 1 <= n <= 100_000

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(br.readLine());
            int answer = getAnswer(N);

            System.out.println("answer = " + answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getAnswer(int n) {

        int division = n / 5;

        if (n % 5 == 0) {
            return division;
        }

        int answer = 0;
        int target = n;
        while (division > 0) {
            target = n - division * 5;

            if (target % 2 == 0) {
                answer += target / 2;
                target = 0;
                break;
            }

            division -= 1;
        }

        answer += division;

        if (target > 0) {
            if (n % 2 == 0) {
                answer = n / 2;
            } else {
                answer = -1;
            }
        }

        return answer;
    }
}
