import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Pipe;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int[][] MAP = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }

            Solution17069 solution = new Solution17069();
            long answer = solution.solve(N,MAP);
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

class Solution17069 {
    private int N;
    int[][] MAP;

    long[][][] COUNTS;

    public long solve(int n, int[][] map) {
        this.N = n;
        this.MAP = map;
        this.COUNTS = new long[N + 1][N + 1][3];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Arrays.fill(COUNTS[i][j], -1);
            }
        }

        long answer = dfs(new Pipe(new Point(1,2),0));
//        printCounts();
        return answer;
    }

    private void printCounts() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(Arrays.toString(COUNTS[i][j])).append('\t');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }


    public long dfs(Pipe pipe) {
        if (pipe.head.x == N && pipe.head.y == N) {
            return 1;
        }

        int type = pipe.type;
        int hx = pipe.head.x;
        int hy = pipe.head.y;

        if (COUNTS[hx][hy][type] != -1) {
            return COUNTS[hx][hy][type];
        }

        COUNTS[hx][hy][type] = 0;

        switch (type) {
            case 0:
                if (hy + 1 <= N && MAP[hx][hy + 1] == 0) {
                    COUNTS[hx][hy][type] += dfs(new Pipe(new Point(hx, hy + 1), 0));
                }

                if (hx + 1 <= N && hy + 1 <= N
                        &&  MAP[hx][hy + 1] == 0
                        &&  MAP[hx + 1][hy + 1] == 0
                        &&  MAP[hx + 1][hy] == 0) {
                    COUNTS[hx][hy][type] += dfs(new Pipe(new Point(hx + 1, hy + 1), 2));
                }
                break;
            case 1:
                if (hx + 1 <= N && MAP[hx + 1][hy] == 0) {
                    COUNTS[hx][hy][type] += dfs(new Pipe(new Point(hx + 1, hy), 1));
                }

                if (hx + 1 <= N && hy + 1 <= N
                        &&  MAP[hx][hy + 1] == 0
                        &&  MAP[hx + 1][hy + 1] == 0
                        &&  MAP[hx + 1][hy] == 0) {
                    COUNTS[hx][hy][type] += dfs(new Pipe(new Point(hx + 1, hy + 1), 2));
                }
                break;
            case 2:

                if (hy + 1 <= N && MAP[hx][hy + 1] == 0) {
                    COUNTS[hx][hy][type] += dfs(new Pipe(new Point(hx, hy + 1), 0));
                }

                if (hx + 1 <= N && MAP[hx + 1][hy] == 0) {
                    COUNTS[hx][hy][type] += dfs(new Pipe(new Point(hx + 1, hy), 1));
                }

                if (hx + 1 <= N && hy + 1 <= N
                        &&  MAP[hx][hy + 1] == 0
                        &&  MAP[hx + 1][hy + 1] == 0
                        &&  MAP[hx + 1][hy] == 0) {
                    COUNTS[hx][hy][type] += dfs(new Pipe(new Point(hx + 1, hy + 1), 2));
                }
                break;
        }

        return COUNTS[hx][hy][type];
    }

    private class Pipe {
        Point head;
        int type;
        public Pipe(Point head, int type) {
            this.head = head;
            this.type = type;
        }
    }

    private class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}