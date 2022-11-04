import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            Solution11054 solution = new Solution11054(N,A);

            int answer = solution.solve();
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

class Solution11054 {

    private final int N;
    private final int[] A, dp1, dp2;

    public Solution11054(int n, int[] a) {
        this.N = n;
        this.A = a;

        this.dp1 = new int[n];
        this.dp2 = new int[n];
    }

    public int solve() {

        for (int i = 0; i < N; i++) {
            dp1[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j] && dp1[i] < dp1[j] + 1) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            dp2[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (A[i] > A[j] && dp2[i] < dp2[j] + 1) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }

        return IntStream.range(0, N)
                .map(i -> dp1[i] + dp2[i] - 1)
                .max()
                .getAsInt();
    }
}