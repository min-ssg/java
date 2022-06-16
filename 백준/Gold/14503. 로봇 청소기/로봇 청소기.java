import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int R = scan.nextInt();
            int C = scan.nextInt();
            int D = scan.nextInt();

            int[][] MAP = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }

            Solution14503 solution14503 = new Solution14503();
            int answer = solution14503.solve(N,M,R,C,D,MAP);
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

class Solution14503 {

    private int N,M;
    private int[][] MAP, D;
    private boolean[][] visited;
    private CleaningBot bot;

    public int solve(int n, int m, int r, int c, int d, int[][] map) {
        initialize(n,m,r,c,d,map);
        return cleaning();
    }

    private int cleaning() {
        int countOfCleaningRoom = 0;

        while (true) {

            if (!visited[bot.x][bot.y]) {
                countOfCleaningRoom += 1;
                visited[bot.x][bot.y] = true;
            }

            boolean isNext = false;
            int nextDirection = bot.direction;
            for (int i = 0; i < 4; i++) {
                nextDirection = getNextDirection(nextDirection);

                if (bot.x + D[nextDirection][0] < 0 || bot.x + D[nextDirection][0] >= N
                ||  bot.y + D[nextDirection][1] < 0 || bot.y + D[nextDirection][1] >= M) continue;
                if (MAP[bot.x + D[nextDirection][0]][bot.y + D[nextDirection][1]] == 1) continue;
                if (visited[bot.x + D[nextDirection][0]][bot.y + D[nextDirection][1]]) continue;

                isNext = true;
                bot.x += D[nextDirection][0];
                bot.y += D[nextDirection][1];
                bot.direction = nextDirection;
                break;
            }

            // 다음으로 넘어 갈 수 없을 경우, 후진이 가능하면 후진, 아니면 종료
            if (!isNext) {
                if (bot.x - D[bot.direction][0] < 0 || bot.x - D[bot.direction][0] >= N
                ||  bot.y - D[bot.direction][1] < 0 || bot.y - D[bot.direction][1] >= M) break;
                if (MAP[bot.x - D[bot.direction][0]][bot.y - D[bot.direction][1]] == 1) break;

                // 후진 가능.
                bot.x -= D[bot.direction][0];
                bot.y -= D[bot.direction][1];
            }
        }

        return countOfCleaningRoom;
    }

    private int getNextDirection(int direction) {
        switch (direction) {
            case 0: // UP
                direction = 3;
                break;
            case 1: // RIGHT
                direction = 0;
                break;
            case 2: // DOWN
                direction = 1;
                break;
            case 3: // LEFT
                direction = 2;
                break;
        }

        return direction;
    }

    private void initialize(int n, int m, int r, int c, int d, int[][] map) {
        this.N = n;
        this.M = m;
        this.MAP = map;

        this.D = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        this.bot = new CleaningBot(r,c,d);
        this.visited = new boolean[n][m];
    }

    private class CleaningBot {
        int x;
        int y;
        int direction;

        CleaningBot(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}