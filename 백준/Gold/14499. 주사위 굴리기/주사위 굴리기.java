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

            int K = scan.nextInt();

            int[][] MAP = new int[N][M];
            int[] command = new int[K];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    MAP[i][j] = scan.nextInt();
                }
            }

            for (int i = 0; i < K; i++) {
                command[i] = scan.nextInt();
            }

            Solution14499 solution14499 = new Solution14499();
            solution14499.solve(N,M,R,C,MAP,command);
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

class Solution14499 {

    StringBuilder sb = new StringBuilder();

    private int N,M,R,C;
    private int[][] MAP;
    private int[] commands;

    private int[][] dice;

    public void solve(int n, int m, int r, int c, int[][] map, int[] commands) {
        initialize(n,m,r,c,map,commands);

        for (int command : commands) {
            int nx = r;
            int ny = c;
            switch (command) {
                case 1: // 동쪽
                    ny += 1;
                    break;
                case 2: // 서쪽
                    ny -= 1;
                    break;
                case 3: // 북쪽
                    nx -= 1;
                    break;
                case 4: // 남쪽
                    nx += 1;
                    break;
            }

            if (nx < 0 || nx >= N
            ||  ny < 0 || ny >= M) continue;

            r = nx; c = ny;
            moveDice(command);
            if (MAP[r][c] == 0) {
                MAP[r][c] = dice[3][1]; // 주사위 바닥 값 복사.
            } else {
                dice[3][1] = MAP[r][c];
                MAP[r][c] = 0;
            }
            sb.append(dice[1][1]).append('\n');
        }

        System.out.println(sb.toString());
    }

    private void moveDice(int command) {

        int temp = 0;
        switch (command) {
            case 1: // RIGTH
                temp = dice[3][1]; // 바닥.
                dice[3][1] = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = temp;
                break;
            case 2: // LEFT
                temp = dice[3][1]; // 바닥.
                dice[3][1] = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = temp;
                break;
            case 3: // UP
                temp = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = temp;
                break;
            case 4: // DOWN
                temp = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = temp;
                break;
        }
    }

    private void initialize(int n, int m, int r, int c, int[][] map, int[] commands) {
        this.N = n;
        this.M = m;
        this.R = r;
        this.C = c;
        this.MAP = map;
        this.commands = commands;

        this.dice = new int[4][3];
    }
}