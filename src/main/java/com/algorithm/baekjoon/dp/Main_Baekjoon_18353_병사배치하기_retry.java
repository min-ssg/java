package com.algorithm.baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Baekjoon_18353_병사배치하기_retry {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[] A = new int[N];

            for (int i = N-1; i >= 0; i--) { // 반대로 입력
                A[i] = scan.nextInt();
            }

            Solution18353retry solution18353retry = new Solution18353retry();
            int answer = solution18353retry.solve(N,A);
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

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
    }
}

class Solution18353retry {


    List<Integer> dp = new ArrayList<>();

    public int solve(int n, int[] soldiers) {

        for (int soldier : soldiers) {
            System.out.println(dp.toString());
            if (dp.isEmpty() || dp.get(dp.size() - 1) < soldier) {
                dp.add(soldier);
            } else {
                int L = binarySearch(soldier);
                System.out.println(L);
                dp.remove(L);
                dp.add(L, soldier);
            }
        }

        return n - dp.size();
    }

    private Integer binarySearch(int soldier) {

        int L = 0;
        int R = dp.size() - 1;

        while (L <= R) {
            int middle = (L + R) / 2;

            if (dp.get(middle) >= soldier) {
                R = middle - 1;
            } else {
                L = middle + 1;
            }
        }

        return L;
    }

    private class Soldier implements Comparable<Soldier> {
        protected int index;
        protected int combatPower;

        public Soldier(int index, int combatPower) {
            this.index = index;
            this.combatPower = combatPower;
        }

        @Override
        public String toString() {
            return "index: " + index + ", combatPower: " + combatPower;
        }

        @Override
        public int compareTo(Soldier soldier) {
            return Integer.compare(soldier.combatPower, this.combatPower);
        }
    }
}
