    package com.algorithm.baekjoon.sorting;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.StringTokenizer;

    public class Main_Baekjoon_20291_파일정리 {
        public static void main(String[] args) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                FastReader scan = new FastReader(br);

                int N = scan.nextInt();
                String[] files = new String[N];

                for (int i = 0; i < N; i++) {
                    files[i] = scan.nextLine();
                }

                Solution20291 solution20291 = new Solution20291();
                solution20291.solve(N,files);
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

    class Solution20291 {

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> extension = new HashMap<>();

        public void solve(int n, String[] files) {

            for (String file : files) {
                System.out.println(file);
                String ext = file.split("\\.")[1];

                extension.put(ext, extension.getOrDefault(ext, 0) + 1);
            }

            extension.entrySet().stream().sorted(comparingByKey()).forEach((entry) -> sb.append(entry.getKey()).append(' ').append(entry.getValue()).append('\n'));

            System.out.println(sb.toString());
        }

        private Comparator<? super Map.Entry<String, Integer>> comparingByKey() {
            return (entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey());
        }
    }