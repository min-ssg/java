import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt(); // 1 <= N,M <= 8
            int M = scan.nextInt();

            int[][] MAP = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }
            Solution15683 solution15683 = new Solution15683();
            int answer = solution15683.solve(N,M,MAP);
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

class Solution15683 {

    StringBuilder sb = new StringBuilder();
    private int N,M, min = Integer.MAX_VALUE;
    private int[][] MAP;
    List<Cctv> cctvs;

    public int solve(int n, int m, int[][] map) {
        initialize(n,m,map);
        return simulation();
    }

    private int simulation() {
        recurrentFunction(0);
        return min;
    }

    private void recurrentFunction(int k) {
        if (k == cctvs.size()) {
            int numberOfUndetectableArea = getUndetectableArea();
            min = Math.min(min, numberOfUndetectableArea);
            return;
        }

        Cctv cctv = cctvs.get(k);

        for (int i = 0; i < 4; i++) {
            cctv.mode = i;
            recurrentFunction(k + 1);
            cctv.mode = 0;
        }
    }

    private int getUndetectableArea() {

        int numberOfUndetectableArea = 0;
        int[][] copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            copyMap[i] = Arrays.copyOf(MAP[i], M);
        }

        cctvs.forEach(cctv -> cctv.detect(copyMap));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) numberOfUndetectableArea += 1;
            }
        }

        return numberOfUndetectableArea;
    }

    private void initialize(int n, int m, int[][] map) {
        this.N = n;
        this.M = m;
        this.MAP = map;

        cctvs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new Cctv(i,j,map[i][j]));
                }
            }
        }
    }

    private class Cctv {
        int x;
        int y;
        int type;
        int mode;

        Cctv(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void detect(int[][] map) {
            if (type == 1) {
                switch (mode) {
                    case 0: // UP
                        up(map);
                        break;
                    case 1: // RIGHT
                        right(map);
                        break;
                    case 2: // DOWN
                        down(map);
                        break;
                    case 3: // LEFT
                        left(map);
                        break;
                }
            } else if (type == 2) {
                switch (mode) {
                    case 0:
                    case 2:
                        up(map);
                        down(map);
                        break;
                    case 1:
                    case 3:
                        right(map);
                        left(map);
                        break;

                }
            } else if (type == 3) {
                switch (mode) {
                    case 0:
                        up(map);
                        right(map);
                        break;
                    case 1:
                        right(map);
                        down(map);
                        break;
                    case 2:
                        down(map);
                        left(map);
                        break;
                    case 3:
                        left(map);
                        up(map);
                        break;
                }
            } else if (type == 4) {
                switch (mode) {
                    case 0:
                        left(map);
                        up(map);
                        right(map);
                        break;
                    case 1:
                        up(map);
                        right(map);
                        down(map);
                        break;
                    case 2:
                        right(map);
                        down(map);
                        left(map);
                        break;
                    case 3:
                        down(map);
                        left(map);
                        up(map);
                        break;
                }
            } else if (type == 5) {
                up(map);
                right(map);
                down(map);
                left(map);
            }
        }

        void up(int[][] map) {
            for (int i = x; i >= 0; i--) {
                if (map[i][y] == 6) break;
                map[i][y] = 9;
            }
        }

        void right(int[][] map) {
            for (int i = y; i < map[0].length; i++) {
                if (map[x][i] == 6) break;
                map[x][i] = 9;
            }
        }

        void down(int[][] map) {
            for (int i = x; i < map.length; i++) {
                if (map[i][y] == 6) break;
                map[i][y] = 9;
            }
        }

        void left(int[][] map) {
            for (int i = y; i >= 0; i--) {
                if (map[x][i] == 6) break;
                map[x][i] = 9;
            }
        }

        @Override
        public String toString() {
            return "(" + type + "," + mode + ")";
        }
    }
}
