package com.algorithm.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_Codeforces_2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            StringBuilder sb = new StringBuilder();
            int T = scan.nextInt(); // 1<= T <= 10000
            Solution2 solution = new Solution2();
            for (int i = 0; i < T; i++) {

                int N = scan.nextInt();
                int[] A = new int[N];
                for (int j = 0; j < N; j++) {
                    A[j] = scan.nextInt();
                }
                int answer = solution.solve(N,A);
                sb.append(answer).append('\n');
            }

            System.out.println(sb.toString());

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

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution2 {

    public int solve(int N, int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            if (map.containsKey(a)) {
                if (map.get(a) + 1 == 3) {
                    return a;
                }
                map.replace(a, map.get(a) + 1);
            } else {
                map.put(a, 1);
            }
        }

        return -1;
    }
}