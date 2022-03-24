package com.algorithm.baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_10799_쇠막대기 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            String laser = scan.nextLine();

            Solution10799 solution10799 = new Solution10799();
            int answer = solution10799.solve(laser);
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

        public String nextLine() throws IOException {
            return br.readLine();
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

class Solution10799 {

    public int solve(String laser) {

        int answer = 0;
        int count = 0;

        for (int i = 0; i < laser.length(); i++) {
            if (laser.charAt(i) == '(') {
                count += 1;
            } else if (laser.charAt(i) == ')') {
                count -= 1;
                if (laser.charAt(i-1) == ')') {
                    answer += 1;
                } else {
                    answer += count;
                }
            }
        }

        return answer;
    }
}
