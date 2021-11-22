package com.algorithm.baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_Baekjoon_11652_카드 {

    static FastReader scan = new FastReader();

    public static void main(String[] args) {
        input();
        solution();
    }

    static int N;

    static Map<Long, Integer> numberOfCard = new HashMap<>();
    private static void input() {
        N = scan.nextInt();

        for (int i = 0; i < N; i++) {
            long card = scan.nextLong();

            if (numberOfCard.containsKey(card)) {
                numberOfCard.put(card, numberOfCard.get(card) + 1);
                continue;
            }

            numberOfCard.put(card, 1);
        }
    }

//    static class Element implements Comparable<Element> {
//        long key;
//        int count;
//
//        @Override
//        public int compareTo(Element other) {
//            return other.count - this.count;
//        }
//
//    }

    private static void solution() {

        long card = numberOfCard.entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getKey)).max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();

        System.out.println(card);
    }
    private static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
    }
}
