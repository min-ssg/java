import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();
            int T = scan.nextInt();
            Solution21396 solution21396 = new Solution21396();

            for (int i = 0; i < T; i++) {
                int N = scan.nextInt();
                int X = scan.nextInt();

                int[] V = new int[N];
                for (int j = 0; j < N; j++) {
                    V[j] = scan.nextInt();
                }
                long answer = solution21396.solve(N,X,V);
                sb.append(answer).append('\n');
            }
            System.out.println(sb.toString());
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

class Solution21396 {

    public long solve(int n, int x, int[] v) {

        HashMap<Integer, Integer> map = new HashMap<>();
        long count = 0;

        for (int i = 0; i < n; i++) {
            int vv = v[i] ^ x;
            if (map.containsKey(vv)) {
                count += map.get(vv);
            }
             

                if (!map.containsKey(v[i])) {
                    map.put(v[i], 1);
                } else {
                    map.replace(v[i], map.get(v[i]) + 1);
                }
            

        }

        return count;
    }
}
