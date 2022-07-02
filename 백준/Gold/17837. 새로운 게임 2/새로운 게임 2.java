import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int K = scan.nextInt(); // 말의 개수

            int[][] map = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int color = scan.nextInt();
                    map[i][j] = color;
                }
            }

            Chessman[] chessmen = new Chessman[K];
            for (int i = 0; i < K; i++) {
                chessmen[i] = new Chessman(i + 1, scan.nextInt(), scan.nextInt(), scan.nextInt());
            }

            Solution17837 solution17837 = new Solution17837();
            int answer = solution17837.solve(N, map, chessmen);
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

class Solution17837 {

    private int N;
    private int[][] MAP;
    private Chessman[] chessmen;
    private Chessman[][] board;
    private boolean quit;

    private int[] dx = {0,0,0,-1,1};
    private int[] dy = {0,1,-1,0,0};

    public int solve(int n, int[][] map, Chessman[] chessmen) {
        initialize(n, map, chessmen);
        return process();
    }

    private void initialize(int n, int[][] map, Chessman[] chessmen) {
        this.N = n;
        this.MAP = map;
        this.chessmen = chessmen;
        this.board = new Chessman[N + 1][N + 1];

        for (Chessman chessman : chessmen) {
            board[chessman.x][chessman.y] = chessman;
        }
    }

    private int process() {
        int turn = 0;

        while (true) {
            if (quit || turn > 1000) break;
            turn += 1;
            for (Chessman chessman : chessmen) {
//                System.out.println("pop = " + chessman);
                int nx = chessman.x + dx[chessman.d];
                int ny = chessman.y + dy[chessman.d];

                moveNext(nx,ny,false, chessman);

                if (quit) break;
            }
        }

        return turn > 1000 ? -1 : turn;
    }

    private void moveNext(int nx, int ny, boolean isSecond, Chessman chessman) {
//        System.out.println("nx = " + nx + ", ny = " + ny + ", isSecond = " + isSecond + ", chessman = " + chessman);
        // 흰색 : 0, 빨간색 : 1, 파랑 : 2
        if (nx < 1 || nx > N
        ||  ny < 1 || ny > N) {
            if (isSecond) return;
            chessman.d = opposite(chessman.d);
            moveNext(chessman.x + dx[chessman.d], chessman.y + dy[chessman.d], true, chessman);
            return;
        }

        if (MAP[nx][ny] == 0) { // 흰색.

            if (chessman.down != null) {
                chessman.down.up = null;
                chessman.down = null;
            } else {
                board[chessman.x][chessman.y] = null;
            }

            if (board[nx][ny] != null) {
                board[nx][ny].addUp(chessman);
            } else {
                chessman.x = nx;
                chessman.y = ny;
                board[nx][ny] = chessman;
            }

            if (board[nx][ny].order() >= 4) {
                quit = true;
            }
        } else if (MAP[nx][ny] == 1) { // 빨강.

            if (chessman.down != null) {
                chessman.down.up = null;
                chessman.down = null;
            } else {
                board[chessman.x][chessman.y] = null;
            }

            chessman = reverse(chessman);

            if (board[nx][ny] != null) {
                board[nx][ny].addUp(chessman);
            } else {
                chessman.x = nx;
                chessman.y = ny;
                board[nx][ny] = chessman;
            }

//            board[chessman.x][chessman.y] = null;
            if (board[nx][ny].order() >= 4) {
                quit = true;
            }
        } else if (MAP[nx][ny] == 2) { // 파랑.
            if (isSecond) return;
            chessman.d = opposite(chessman.d);
            moveNext(chessman.x + dx[chessman.d], chessman.y + dy[chessman.d], true, chessman);
        }
    }

    public Chessman reverse(Chessman chessman) {

        while (true) {
            Chessman temp = chessman.down; // 현재 체스말의 UP을 참조하는 temp;
            chessman.down = chessman.up; // chessman.up을 chessman.down으로 변경
            chessman.up = temp;        // chessman.down = temp;
            if (chessman.down == null) break;
            chessman = chessman.down;
        }
        return chessman;
    }



    private int opposite(int d) {
        int ret = 0;
        switch (d) {
            case 1:
                ret = 2;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 4;
                break;
            case 4:
                ret = 3;
                break;
        }

        return ret;
    }
}

class Chessman {
    int index;
    int x;
    int y;
    int d;

    Chessman down;
    Chessman up;

    public Chessman(int index, int x, int y, int d) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public int order() {
        int count = 1;
//        System.out.println("==================[START : ORDERING]================");
//        System.out.println("This : " + this);
        Chessman chessman = up;
        while(chessman != null) {
            chessman.x = this.x;
            chessman.y = this.y;

//            System.out.println("ORDERING : " + chessman);
            chessman = chessman.up;
            count += 1;
        }
//        System.out.println("==================[END : ORDERING]=================");
        return count;
    }

    @Override
    public String toString() {
        return String.format("(index = %d, x = %d, y = %d, d = %d)",index,x,y,d);
    }

    public void addUp(Chessman chessman) {
        Chessman current = this;
        while (current.up != null) {
            current = current.up;
        }
        chessman.down = current;
        current.up = chessman;
    }
}
