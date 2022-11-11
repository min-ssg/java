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
            int[][] map = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            int[][] inputs = new int[M][4];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < 4; j++) {
                    inputs[i][j] = scan.nextInt();
                }
            }

            Solution11660 solution = new Solution11660();
            solution.solve(N,M,map,inputs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class FastReader {
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

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }
    }
}

class Solution11660 {
    private int N,M;
    private int[][] map, dp;
    StringBuilder sb = new StringBuilder();

    public void solve(int n, int m, int[][] map, int[][] inputs) {
        this.N = n;
        this.M = m;
        this.map = map;
        this.dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = map[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }

        for (int[] input : inputs) {
            int x1 = input[0];
            int y1 = input[1];
            int x2 = input[2];
            int y2 = input[3];

            int answer = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
            sb.append(answer).append('\n');
        }

        System.out.println(sb.toString());
    }
}
