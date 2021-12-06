package com.algorithm.baekjoon.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * N = 5000
 * 점 두 개, 0 ~ 100,000
 * 코딩하기전, 시간복잡도를 고려해봐야함.
 * 정렬 특성: 가장 가까운 원소는 자신의 양 옆 중에 있다.
 */
public class Main_Baekjoon_15970_화살표그리기 {

    private static FastReader scan = new FastReader();
    private static int N;
    private static ArrayList<Integer>[] LIST;

    public static void main(String[] args) {
        input();
        int answer = solution();

        System.out.println(answer);
        exit();
    }

    private static void exit() {
        scan.close();
    }

    private static void input() {
        N = scan.nextInt();
        LIST = new ArrayList[N+1];
        // 각 색깔마다 리스트 객체 생성
        for (int color = 1; color <= N; color++) {
            LIST[color] = new ArrayList<>();
        }
        // 리스트 초기화
        for (int i = 0; i < N; i++) {
            int coord = scan.nextInt();
            int color = scan.nextInt();
            LIST[color].add(coord);
        }
    }

    private static int solution() {
        for (int color = 1; color <= N; color++) {
            Collections.sort(LIST[color]);
        }

        int answer = 0;

        for (int color = 1; color <= N; color++) {
            int size = LIST[color].size();
            for (int index = 0; index < size; index++) {
                int left = leftDistance(color, index);
                int right = rightDistance(color, index);
                answer += Math.min(left, right);
            }
        }

        return answer;
    }

    private static int leftDistance(int color, int index) {
        if (index == 0) {
            return Integer.MAX_VALUE;
        }

        return LIST[color].get(index) - LIST[color].get(index - 1);
    }

    private static int rightDistance(int color, int index) {
        if (index + 1 == LIST[color].size()) {
            return Integer.MAX_VALUE;
        }

        return LIST[color].get(index + 1) - LIST[color].get(index);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String path) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(path)));
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }

        void close() {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
