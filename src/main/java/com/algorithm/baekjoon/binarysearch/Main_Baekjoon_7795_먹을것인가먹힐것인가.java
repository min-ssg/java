package com.algorithm.baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준: 먹을것인가 먹힐 것인가
 * 난이도: 실버3
 * 시간제한: 1초
 * 메모리제한: 256MB
 */
public class Main_Baekjoon_7795_먹을것인가먹힐것인가 {

    static int N, M; // 1 <= N,M <= 20000

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            int answer = 0;
            for (int i = 0; i < T; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                int[] A = new int[N];
                int[] B = new int[M];

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    A[j] = Integer.parseInt(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());

                for (int  j = 0; j < M; j++) {
                    B[j] = Integer.parseInt(st.nextToken());
                }

                Arrays.sort(B);

                answer = solution(A, B);
                System.out.println("answer = " + answer);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int solution(int[] A, int[] B) {

        int answer = 0;

        for (int number : A) {
            answer += binarySearch(number, B);
        }

        return answer;
    }

    private static int binarySearch(int number, int[] b) {

        int left = 0;
        int right = M - 1;
        int result = 0;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (b[middle] < number) {
                left = middle + 1;
                result = left;
            } else {
                right = middle - 1;
            }

        }
        return result;
    }

}
