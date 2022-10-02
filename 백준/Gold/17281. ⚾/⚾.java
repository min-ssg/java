import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();

            int[][] hitters = new int[N][9];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 9; j++) {
                    hitters[i][j] = scan.nextInt();
                }
            }

            Solution17281 solution = new Solution17281();
            int answer = solution.solve(N,hitters);
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

class Solution17281 {
    boolean[] used = new boolean[9];
    int[] ordered = new int[9];
    int N, count = 0, max = Integer.MIN_VALUE;
    int[][] hitters;

    public int solve(int n, int[][] hitters) {
        this.N = n;
        this.hitters = hitters;
        used[3] = true;
        ordered[0] = 3;
        recurrentFunction(1);
        return max;
    }

    private void recurrentFunction(int k) {
        if (k == 9) {
            count++;
            int score = playball();
            max = Math.max(max, score);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (used[i]) continue;
            ordered[k] = i;
            used[i] = true;
            recurrentFunction(k + 1);
            ordered[k] = 0;
            used[i] = false;
        }
    }

    private int playball() {
        int score = 0;
        int start = 0;
        for (int i = 0; i < N; i++) {
            int[] info = inning(start,i);
            score += info[0];
            start = info[1];
        }
        return score;
    }

    private int[] inning(int start, int k) {
        int outCount = 0;
        int[] hitterOrder = new int[9];
        for (int i = 0; i < 9; i++) {
            hitterOrder[ordered[i]] = hitters[k][i];
        }
        int current = start;
        int score = 0;
        int[] alive = new int[3];
        Queue<Integer> queue = new LinkedList<>();
        // 아웃 카운트 확인.
        while (current < 9 && outCount < 3) {
            // 1번부터 9번 까지
            int result = hitterOrder[current++]; // 타자의 결과.
            if (current == 9) current = 0;
            if (result == 0) {
                outCount += 1;
                continue;
            }

            int currentAlive = queue.size();
            for (int i = 0; i < currentAlive; i++) {
                int runner = queue.poll();
                if (runner + result > 3) score += 1;
                else queue.add(runner + result);
            }

            if (result == 4) {
                score += 1;
                continue;
            }
            queue.add(result);

        }
        return new int[]{score, current};
    }
}