package com.algorithm.baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author Minseok
 * @since 2021.09.23
 * 제목: ATM
 * 난이도: 실버3
 * 시간제한 1초
 * 메모리제한 256MB
 */
public class Main_Baekjoon_11399 {

    private static int N;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(br.readLine());
            String line = br.readLine();
            int[] numbers = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(numbers);
            int length = numbers.length;
            for (int i = 1; i < length; i++) {
                numbers[i] += numbers[i-1];
            }

            int answer = Arrays.stream(numbers).sum();

            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

