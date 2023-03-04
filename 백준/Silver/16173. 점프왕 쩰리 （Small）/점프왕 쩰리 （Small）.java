import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();

            int[][] map = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Solution16173 solution = new Solution16173();

            String answer = solution.solve(N, map);

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

class Solution16173 {

    private int N;
    private int[][] map;

    public String solve(int N, int[][] map) {

        this.N = N;
        this.map = map;

        boolean isPossible = dfs(0,0,map[0][0]);

        return isPossible ? "HaruHaru" : "Hing";
    }

    private boolean dfs(int x, int y, int d) {
        if (d == 0) return false;
        if (map[x][y] == -1) return true;

        boolean bx = false, by = false;
        if (x + d < N) bx = dfs(x + d, y, map[x + d][y]);
        if (y + d < N) by = dfs(x, y + d, map[x][y + d]);
        return bx || by;
    }
}
