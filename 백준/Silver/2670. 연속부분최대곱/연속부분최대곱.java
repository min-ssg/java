import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            double[] A = new double[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextDouble();
            }

            double[] dp = new double[N];
            dp[0] = A[0];

            for (int i = 1; i < N; i++) {
                double v = dp[i - 1] * A[i];
                if (v < A[i]) {
                    dp[i] = A[i];
                    continue;
                }
                dp[i] = v;
            }

            double asDouble = DoubleStream.of(dp).max().getAsDouble();
            System.out.printf("%.3f", Math.round(asDouble * 1000) / 1000.0);


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

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }
    }
}
