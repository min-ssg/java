import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int[][] map = new int[10][10];

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Solution17136 solution = new Solution17136();
            int answer = solution.solve(map);
            System.out.println(answer);
        } catch (IOException ex) {
            ex.printStackTrace();
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

class Solution17136 {

    private static final int SIZE = 10;
    private int minPapers = Integer.MAX_VALUE;
    private int[] papers;
    private int[][] MAP;

    public int solve(int[][] map) {
        this.papers = new int[6];
        this.MAP = map;

        Arrays.fill(papers, 5);

        dfs(0,0,0);

        return minPapers == Integer.MAX_VALUE ? -1 : minPapers;
    }

    private void dfs(int x, int y, int attachedPapers) {
        while (MAP[x][y] == 0) {
            if (++y >= SIZE) {
                if (++x >= SIZE) {
                    minPapers = Math.min(minPapers, attachedPapers);
                    return;
                }
                y = 0;
            }
        }

        if (minPapers <= attachedPapers) return;
        for (int n = 5; n >= 1; n--) {
            if (x + n > SIZE || y + n > SIZE || papers[n] == 0) continue;

            if (isCovered(x,y,n)) {
                // update map
                updateMap(x,y,n,0);
                papers[n] -= 1;
                dfs(x,y,attachedPapers + 1);
                updateMap(x,y,n,1);
                papers[n] += 1;
            }
        }
    }

    private void updateMap(int x, int y, int n, int value) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                MAP[x + i][y + j] = value;
            }
        }
    }

    private boolean isCovered(int x, int y, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (MAP[x + i][y + j] == 0) return false;
            }
        }
        return true;
    }
}
