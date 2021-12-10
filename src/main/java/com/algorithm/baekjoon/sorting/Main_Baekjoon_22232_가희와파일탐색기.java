package com.algorithm.baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Baekjoon_22232_가희와파일탐색기 {

    static FastReader scan = new FastReader();
    static int N, M; // 1<= N,M <= 200000
    static File[] files;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        input();
        solution();
    }

    private static void solution() {
        Arrays.sort(files);

        for (File file : files) {
            System.out.println(file.getFilenameWithExtension());
        }
    }

    private static void input() {
        N = scan.nextInt();
        M = scan.nextInt();

        files = new File[N];
        for (int i = 0; i < N; i++) {
            String str = scan.nextLine();
            String[] f = str.split("\\.");
            files[i] = new File(f[0], f[1]);
        }

        for (int i = 0; i < M; i++) {
            String extension = scan.nextLine();
            set.add(extension);
        }
    }

    static class File implements Comparable<File> {
        private String filename;
        private String extension;

        public File(String filename, String extension) {
            this.filename = filename;
            this.extension = extension;
        }

        public String getFilename() {
            return filename;
        }

        public String getExtension() {
            return extension;
        }

        public String getFilenameWithExtension() {
            return filename + "." + extension;
        }

        @Override
        public int compareTo(File other) {
            if (this.filename.compareTo(other.filename) < 0) {
                return -1;
            } else if (this.filename.compareTo(other.filename) == 0) {
                if (set.contains(this.extension)
                &&  !set.contains(other.extension)) {
                    System.out.println("File.compareTo start");
                    System.out.println(this.getFilenameWithExtension());
                    System.out.println(other.getFilenameWithExtension());
                    System.out.println("File.compareTo end");
                    return -1;
                } else if (!set.contains(this.extension)
                &&          set.contains(other.extension)) {
                    return 1;
                } else {
                    return this.extension.compareTo(other.extension);
                }
            }
            return 1;
        }
    }

    static class FastReader {
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

        public String nextLine() {
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
