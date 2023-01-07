import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            int[] A = new int[N];
            int[][] dp = new int[N][2];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            dp[0][0] = A[0];

            for (int i = 1; i < N; i++) {
                if (dp[i - 1][0] < 0) {
                    dp[i][0] = A[i];
                } else {
                    dp[i][0] = dp[i - 1][0] + A[i];
                }

                dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + A[i]);
            }

            int answer = Stream.of(dp).flatMapToInt(IntStream::of).max().getAsInt();
            answer = answer != 0 ? answer : IntStream.of(A).max().getAsInt();
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
