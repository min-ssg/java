package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1759_암호만들기 {

    static StringBuilder sb = new StringBuilder();
    static int L,C; // 3 <= L <= C <= 15
    static char[] letters, password;
    static char[] vowel = {'a','e','i','o','u'};

    public static void main(String[] args) {
        input();
        recurrentFunction(1,1);
        System.out.println(sb.toString());
    }

    private static void input() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            FastReader scan = new FastReader(br);

            L = scan.nextInt();
            C = scan.nextInt();

            letters = new char[C+1];
            password = new char[L+1];

            for (int i = 1; i <= C; i++) {
                letters[i] = scan.nextCharacter();
            }

            Arrays.sort(letters, 1, C+1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recurrentFunction(int k, int index) {

        if (k == L+1 ) {

            if (isPossible()) {
                for (int i = 1; i <= L; i++) {
                    sb.append(password[i]);
                }
                sb.append('\n');
            }
            return;
        }

        for (int i = index; i <= C; i++) {
            password[k] = letters[i];
            recurrentFunction(k+1, i+1);
            password[k] = 0;
        }
    }

    private static boolean isPossible() {
        int numberOfVowels = 0;
        int others = 0;

        for (int i = 1; i <= L; i++) {
            if (isVowel(password[i])) {
                numberOfVowels++;
            } else {
                others++;
            }
        }

        return numberOfVowels >=1 && others >= 2;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(BufferedReader br) {
            this.br = br;
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public char nextCharacter() throws IOException {
            return next().charAt(0);
        }
    }
}
