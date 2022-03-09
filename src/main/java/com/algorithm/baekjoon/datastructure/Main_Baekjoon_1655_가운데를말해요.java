package com.algorithm.baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_Baekjoon_1655_가운데를말해요 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            Solution1655 solution1655 = new Solution1655();
            solution1655.solve(N,A);
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

class Solution1655 {

    PriorityQueue<Integer> MAX_HEAP = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> MIN_HEAP = new PriorityQueue<>();

    StringBuilder sb = new StringBuilder();

    private int N;
    private int[] A;

    public void solve(int n, int[] a) {
        this.N = n;
        this.A = a.clone();

        for (int number : A) {
            if (MAX_HEAP.size() == MIN_HEAP.size()) {
                MAX_HEAP.add(number);
            } else {
                MIN_HEAP.add(number);
            }

            if (MIN_HEAP.isEmpty()) {
                sb.append(MAX_HEAP.peek()).append('\n');
                continue;
            }

            if (MAX_HEAP.peek() > MIN_HEAP.peek()) {
                int maxHeapPeek = MAX_HEAP.poll();
                int minHeapPeek = MIN_HEAP.poll();

                MAX_HEAP.add(minHeapPeek);
                MIN_HEAP.add(maxHeapPeek);
            }

            sb.append(MAX_HEAP.peek()).append('\n');
        }

        System.out.println(sb.toString());
    }
}
