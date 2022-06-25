import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 상어 잡기
 * R,C 1부터 시작.
 *
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int R = scan.nextInt(); // 2 <= R, C <= 100;
            int C = scan.nextInt();
            int M = scan.nextInt(); // 0 <= M < R * C

            List<Shark> sharks = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                sharks.add(new Shark(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt()));
            }

            Solution17143 solution17143 = new Solution17143();
            int answer = solution17143.solve(R,C,sharks);
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

class Solution17143 {

    private int R,C;
    Shark[][] sharkMap, otherSharkMap;

    public int solve(int r, int c, List<Shark> sharks) {
        initialize(r,c,sharks);
        return getSharkSize();
    }

    private void initialize(int r, int c, List<Shark> sharks) {
        this.R = r;
        this.C = c;
        this.sharkMap = new Shark[r + 1][c + 1];
        this.otherSharkMap = new Shark[r + 1][c + 1];

        sharks.forEach(shark -> {sharkMap[shark.x][shark.y] = shark;});
    }


    private int getSharkSize() {
        int totalSharkSize = 0;
        for (int col = 1; col <= C; col++) {
            totalSharkSize += fishing(col);
            sharkMoving();
            copy();
        }

        return totalSharkSize;
    }

    private void printCurrentMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (sharkMap[i][j] == null) {
                    sb.append("NULL");
                } else {
                    sb.append('S').append(sharkMap[i][j].d);
                }
                sb.append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private int fishing(int col) {
        for (int row = 1; row <= R; row++) {
            if (sharkMap[row][col] == null) continue;

            int catchSharkSize = sharkMap[row][col].z;
            sharkMap[row][col] = null;
            return catchSharkSize;
        }

        return 0;
    }

    private void sharkMoving() {
        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                if (sharkMap[row][col] == null) continue;

                Shark shark = sharkMap[row][col];
                sharkMap[row][col] = null;
                shark.move(R,C);
                if (otherSharkMap[shark.x][shark.y] == null) {
                    otherSharkMap[shark.x][shark.y] = shark;
                } else {
                    if (otherSharkMap[shark.x][shark.y].z < shark.z) {
                        otherSharkMap[shark.x][shark.y] = shark;
                    }
                }
            }
        }
    }

    private void copy() {
        for (int row = 1; row <= R; row++) {
            for (int col = 1; col <= C; col++) {
                sharkMap[row][col] = otherSharkMap[row][col];
                otherSharkMap[row][col] = null;
            }
        }
    }
}

class Shark {
    int x;
    int y;
    int s;
    int d;
    int z;

    public Shark(int x, int y, int s, int d, int z) {
        this.x = x;
        this.y = y;
        this.s = s;
        this.d = d;
        this.z = z;
    }

    public void move(int R, int C) {
        switch (d) {
            case 1: // UP
                moveUp(R);
                break;
            case 2: // DOWN
                moveDown(R);
                break;
            case 3: // RIGHT
                moveRight(C);
                break;
            case 4: // LEFT
                moveLeft(C);
                break;

        }
    }

    private void moveUp(int R) {
        int _s = s % (2 * (R - 1));

        if (_s <= x - 1) {
            x -= _s;
        } else if (_s <= (x - 1) + (R - 1)) {
            x = _s - (x - 1) + 1;
            d = 2;
        } else {
            x = R - (_s - ((x - 1) + (R - 1)));
        }
    }

    private void moveDown(int R) {
        int _s = s % (2 * (R - 1));

        if (_s <= R - x) {
            x += _s;
        } else if (_s <= (R - x) + (R - 1)) {
            x = R - (_s - (R - x));
            d = 1;
        } else {
            x = _s - ((R - x) + (R - 1)) + 1;
        }
    }

    private void moveRight(int C) {
        int _s = s % (2 * (C - 1));

        if (_s <= C - y) {
            y += _s;
        } else if (_s <= (C - y) + (C - 1)) {
            y = C - (_s - (C - y));
            d = 4;
        } else {
            y = _s - ((C - y) + (C - 1)) + 1;
        }
    }

    private void moveLeft(int C) {
        int _s = s % (2 * (C - 1));

        if (_s <= y - 1) {
            y -= _s;
        } else if (_s <= (y - 1) + (C - 1)) {
            y = _s - (y - 1) + 1;
            d = 3;
        } else {
            y = C - (_s - ((y - 1) + (C - 1)));
        }
    }

    @Override
    public String toString() {
        return "Shark{" +
                "x=" + x +
                ", y=" + y +
                ", s=" + s +
                ", d=" + d +
                ", z=" + z +
                '}';
    }
}