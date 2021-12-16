package com.algorithm.baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class Main_Baekjoon_13144_ListOfUniqueNumbers {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;
    static Set<Integer> set = new HashSet<>();

    static void input() {
        N = scan.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static void solution() {
        long answer = 0;

        set.add(A[0]);
        for (int L=0,R=1; L < N; L++) {
            System.out.println("L = " + L);
            while (R < N && !set.contains(A[R])) {
                set.add(A[R++]);
                System.out.println("R = " + R);
            }
            answer += R - L;
            set.remove(A[L]);
            System.out.println("===============");
        }
        System.out.println(answer);
    }

    public static void main(String[] args) {
        input();
        solution();
    }


    static class FastReader {
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