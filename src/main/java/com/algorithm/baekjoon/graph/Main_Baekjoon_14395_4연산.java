package com.algorithm.baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_14395_4연산 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int s = scan.nextInt();
            int t = scan.nextInt();

            Solution14395 solution14395 = new Solution14395();
            String answer = solution14395.solve(s,t);
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

class Solution14395 {

    private int S,T;

    public String solve(int s, int t) {
        if (s == t) return "0";
        if (t == 0) return "-";
        if (t == 1) return "/";

        this.S = s;
        this.T = t;

        return bfs();
    }

    private String bfs() {
        Queue<Long> queue = new LinkedList<>();
        Queue<String> sQueue = new LinkedList<>();
        Set<Long> set = new HashSet<>();

        sQueue.add("");
        sQueue.add("/");
        queue.add((long) S);
        queue.add(1L);

        while (!queue.isEmpty()) {
            long x = queue.poll();
            String expr = sQueue.poll();
            if ( 0 <= (long) (x * x) && (long) (x * x) <= T && !set.contains(x * x)) {
                if (x * x == T) return expr + "*";
                set.add(x * x);
                queue.add(x * x);
                sQueue.add(expr + "*");
            }

            if ( 0 <= (long) (x + x) && (long) (x + x) <= T && !set.contains(x + x)) {
                if (x + x == T) return expr + "+";
                set.add(x + x);
                queue.add(x + x);
                sQueue.add(expr + "+");
            }
        }

        return "-1";
    }
}
