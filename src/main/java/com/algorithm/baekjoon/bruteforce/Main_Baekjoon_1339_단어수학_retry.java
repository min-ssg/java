package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_1339_단어수학_retry {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            String[] words = new String[N];

            for (int i = 0; i < N; i++) {
                words[i] = scan.nextLine();
            }

            Solution1339Retry solution1339Retry = new Solution1339Retry();
            int answer = solution1339Retry.solve(N,words);
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

        public String nextLine() throws IOException {
            return br.readLine();
        }
    }
}

class Solution1339Retry {

    Map<Character, Integer> map = new HashMap<>();

    public int solve(int n, String[] words) {

        int answer = 0;

        for (String word : words) {
            int length = word.length();

            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                int value = (int) (Math.pow(10, length - i - 1));

                if (map.containsKey(c)) {
                    map.replace(c, map.get(c) + value);
                    continue;
                }

                map.put(c, value);
            }
        }

        List<Map.Entry<Character, Integer>> entries = new LinkedList<>(map.entrySet());

        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        int number = 9;

        for (Map.Entry<Character, Integer> entry : entries) {
            answer += entry.getValue() * number--;
        }

        return answer;
    }
}
