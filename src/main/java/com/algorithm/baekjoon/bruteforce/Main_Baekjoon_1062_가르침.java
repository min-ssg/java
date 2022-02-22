package com.algorithm.baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Baekjoon_1062_가르침 {

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int K = scan.nextInt();
            String[] words = new String[N];
            for (int i = 0; i < N; i++) {
                words[i] = scan.nextLine();
                words[i] = words[i].substring(4,words[i].length() - 4);
            }

            Solution1062 solution1062 = new Solution1062(N,K,words);
            int answer = solution1062.solve();
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
                st = new StringTokenizer(nextLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution1062 {

    StringBuilder sb = new StringBuilder();
    private int N,K, max;
    private String[] words;
    private boolean[] A, used; // 알파벳 26자리

    public Solution1062(int N, int K, String[] words) {
        this.N = N;
        this.K = K;
        this.words = words.clone();
    }


    public int solve() {
        if (K < 5) {
            return 0;
        }

        if (K == 26) {
            return N;
        }
        K -= 5;

        used = new boolean[26];

        used['a'-'a'] = true;
        used['c'-'a'] = true;
        used['i'-'a'] = true;
        used['n'-'a'] = true;
        used['t'-'a'] = true;

        recurrentFunction(1,0);
        return max;
    }

    private void recurrentFunction(int k, int index) {
        if (k == K+1) {
            int count = getReadableWordCount();
            max = Math.max(max, count);
            return;
        }

        for (int i = index; i < 26; i++) {
            if (used[i]) continue;

            used[i] = true;
            recurrentFunction(k+1, i+1);
            used[i] = false;
        }
    }

    private int getReadableWordCount() {
        int count = 0;
        for (String word : words) {
            if (isReadable(word)) {
                count++;
            }
        }

        return count;
    }

    private boolean isReadable(String word) {

        for (char c : word.toCharArray()) {
            if (!used[c - 'a']) {
                return false;
            }
        }

        return true;
    }
}
