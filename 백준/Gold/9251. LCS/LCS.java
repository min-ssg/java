import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            String str1 = scan.nextLine();
            String str2 = scan.nextLine();

            Solution9251 solution = new Solution9251();

            int answer = solution.solve(str1, str2);
            System.out.println(answer);
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

class Solution9251 {

    public int solve(String str1, String str2) {
        int N = str1.length();
        int M = str2.length();

        int[][] dp = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    continue;
                }

                dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        return dp[N][M];
    }
}