package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 연산자 끼워넣기
 * 난이도: 실버1
 * 시간제한: 2초
 * 람다식 사용함.
 */
public class Main_Baekjoon_14888_연산자끼워넣기 {

    static int N, max, min;
    static int[] numbers, operators;
    static Calculation plus = (a,b) -> (a + b);
    static Calculation minus = (a,b) -> (a - b);
    static Calculation product = (a,b) -> (a * b);
    static Calculation division = (a,b) -> (a / b);

    static Calculation[] orders;

    public static void main(String[] args) {
        input();
        solution();
        print();
    }

    private static void print() {
        System.out.println(max);
        System.out.println(min);
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            N = scan.nextInt();
            numbers = new int[N+1];
            operators = new int[5]; // 4가지
            orders = new Calculation[N+1];
            for (int i = 1; i <= N; i++) {
                numbers[i] = scan.nextInt();
            }
            for (int i = 1; i <= 4; i++) {
                operators[i] = scan.nextInt();
            }

            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solution() {

        recurrentFunction(1, numbers[1]);
    }

    private static void recurrentFunction(int k, int value) {

        if (k == N) { // 모든 연산자의 나열이 끝난 경우
            // 정한 연산자 순서대로 계산해서 정답을 갱신한다.
            // 완성된 식에 맞게 계산에 갱신하는 작업
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        // k번째 연산자는 무엇을 선택할 것인가?

        for (int candidate = 1; candidate <= 4; candidate++) {
            if (isAvailable(candidate)) {

                Calculation calculation = getCalculation(candidate);

                operators[candidate]--;
                int next = calculation.calculate(value, numbers[k+1]);
                recurrentFunction(k+1, next);
                operators[candidate]++;

            }
        }

    }

    private static Calculation getCalculation(int candidate) {
        Calculation calculation = null;
        switch (candidate) {
            case 1:
                calculation = plus;
                break;
            case 2:
                calculation = minus;
                break;
            case 3:
                calculation = product;
                break;
            case 4:
                calculation = division;
                break;
        }

        return calculation;
    }

    private static boolean isAvailable(int candidate) {
        return operators[candidate] > 0;
    }

    //완성된 식에 맞게 계산에 갱신하는 작업
    private static int calculate() {
        // numbers, order
        int result = numbers[1];
        for (int i = 1; i <= N-1; i++) {
            result = orders[i].calculate(result, numbers[i+1]);
        }

        return result;
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }

            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

@FunctionalInterface
interface Calculation {
    int calculate(int a, int b);
}
