import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt(); // 1 <= N <= 1_000_000;
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = scan.nextInt();
            }

            int B = scan.nextInt();
            int C = scan.nextInt();

            Solution13458 solution13458 = new Solution13458();
            long answer = solution13458.solve(N,A,B,C);
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

class Solution13458 {

    private int N,B,C;
    private int[] A;
    public long solve(int n, int[] a, int b, int c) {
        initialize(n,a,b,c);
        return getAnswer();
    }

    private long getAnswer() {
        long countOfSupervisor = 0;
        for (int a : A) {

            a -= B;
            countOfSupervisor += 1;

            if (a > 0) {
                countOfSupervisor += a % C == 0 ? a / C : a / C + 1;
            }
        }

        return countOfSupervisor;
    }

    private void initialize(int n, int[] a, int b, int c) {
        this.N = n;
        this.A = a;
        this.B = b;
        this.C = c;
    }
}