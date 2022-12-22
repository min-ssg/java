import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt(); // need memory;

            int[] memories = new int[N + 1];
            int[] cache = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                memories[i] = scan.nextInt();
            }


            for (int i = 1; i <= N; i++) {
                cache[i] = scan.nextInt();
            }

            // 반대로 생각해야 함.
            int fullMemory = IntStream.of(cache).sum();

            int[][] dp = new int[N + 1][fullMemory + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j <= fullMemory; j++) {
                    if (j - cache[i] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - cache[i]] + memories[i]);
                    }

                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }


            for (int i = 0; i <= fullMemory; i++) {
                if (dp[N][i] >= M) {
                    System.out.println(i);
                    break;
                }
            }

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
