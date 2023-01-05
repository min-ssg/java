import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int K = scan.nextInt();

            int row = K / M; int col = K % M - 1;

            if (K % M == 0) {
                row -= 1;
                col = M - 1;
            }

            Solution10164 solution10164 = new Solution10164();

            int answer = solution10164.solve(N, M, K, row, col);

            System.out.println(answer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;


        public FastReader (BufferedReader br) {
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

class Solution10164 {

    private int N,M,row,col;
    private int[][][] dp;
    public int solve(int N, int M, int K, int row, int col) {
        this.N = N;
        this.M = M;
        this.row = row;
        this.col = col;

        this.dp = new int[N][M][2];

        int[] answer = dfs(0,0);

//        printDp();

        return K == 0 ? answer[0] : answer[1];
    }

    private void printDp() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append('[').append(dp[i][j][0]).append(',').append(dp[i][j][1]).append(']').append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private int[] dfs(int x, int y) {

        if (x < 0 || x >= N || y < 0 || y >= M) return new int[]{0,0};
        if (dp[x][y][0] != 0 || dp[x][y][1] != 0) return dp[x][y];
        if (x == N - 1 && y == M - 1) return new int[]{1,0};

        int[] down = dfs(x + 1, y);
        int[] right = dfs(x, y + 1);

        dp[x][y][0] = down[0] + right[0];
        dp[x][y][1] = down[1] + right[1];

        if (x == row && y == col) {
            dp[x][y][1] = dp[x][y][0];
        }

        return dp[x][y];
    }
}
