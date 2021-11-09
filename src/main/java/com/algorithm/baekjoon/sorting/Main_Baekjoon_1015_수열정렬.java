package com.algorithm.baekjoon.sorting;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_Baekjoon_1015_수열정렬 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        input();
        solution();
    }

    private static class Element implements Comparable<Element> {
        public int num, idx;

        @Override
        public int compareTo(Element other) {
            return this.num - other.num;
        }
    }

    static int N;
    static int[] P;
    static Element[] B;
    private static void input() {
        N = scan.nextInt();
        B = new Element[N];
        P = new int[N];

        for (int i = 0; i < N; i++) {
            B[i] = new Element();
            B[i].num = scan.nextInt();
            B[i].idx = i;
        }
    }

    private static void solution() {
        // B배열 SORT
        Arrays.sort(B); // B는 Object배열이므로 Tim sort
        for (int i = 0; i < N; i++) {
            P[B[i].idx] = i;
        }

        for (int i = 0; i < N; i++) {
            sb.append(P[i]).append(' ');
        }
        System.out.println(sb.toString());
    }

    private static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
