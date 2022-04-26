import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = scan.nextInt();
            int M = scan.nextInt();

            char[][] map = new char[N][M];
            for (int i = 0; i < N; i++) {
                String str = scan.nextLine();
                map[i] = str.toCharArray();
            }

            Solution13460 solution = new Solution13460();
            int answer = solution.solve(N,M,map);
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

        public String nextLine() throws IOException  {
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

class Solution13460 {
    private int N,M, min = Integer.MAX_VALUE;

    int HX, HY;

    char[][] MAP;

    int[] selected = new int[10];

    public int solve(int n, int m, char[][] map) {
        this.N = n;
        this.M = m;
        this.MAP = map;

        Arrays.fill(selected, -1);

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (MAP[i][j] == 'R') {
                    rx = i;
                    ry = j;
                } else if (map[i][j] == 'B') {
                    bx = i;
                    by = j;
                } else if (map[i][j] == 'O') {
                    HX = i;
                    HY = j;
                }
            }
        }

        recurrentFunction(0, new Ball(rx, ry), new Ball(bx, by));

        if (min == Integer.MAX_VALUE) return -1;

        return min;
    }

    private void showPrintMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(MAP[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    private void recurrentFunction(int k, Ball redBall, Ball blueBall) {
        if (k == 10) {
            return;
        }

        int rx = redBall.x;
        int ry = redBall.y;
        int bx = blueBall.x;
        int by = blueBall.y;

        for (int command = 0; command < 4; command++) {
            int result = move(command, redBall, blueBall);

            if (result == 1) {
                min = Math.min(min, k + 1);
                break;
            }

            if (result == 0) {
                recurrentFunction(k + 1, redBall, blueBall);
            }

//            if (result == -1) {
//                System.out.println("failed = " + Arrays.toString(selected) + " = " + (k + 1));
//            }

            redBall.x = rx;
            redBall.y = ry;
            blueBall.x = bx;
            blueBall.y = by;
        }
    }

    private int move(int command, Ball redBall, Ball blueBall) {
        switch (command) {
            case 0: // 왼쪽.
                rollingLeft(redBall, blueBall);
                break;
            case 1: // 오른쪽.
                rollingRight(redBall, blueBall);
                break;
            case 2: // 위로
                rollingUp(redBall, blueBall);
                break;
            case 3: // 아래.
                rollingDown(redBall, blueBall);
                break;
        }

        if (redBall.x == HX && redBall.y == HY && (redBall.x != blueBall.x || redBall.y != blueBall.y)) {
            return 1;
        }

        if (blueBall.x == HX && blueBall.y == HY) {
            return -1;
        }

        return 0;
    }

    private void rollingRight(Ball redBall, Ball blueBall) {
        if (redBall.y < blueBall.y) {
            while (blueBall.y + 1 <= M - 2) {
                if (MAP[blueBall.x][blueBall.y + 1] == '#') break;
                if (redBall.x == blueBall.x && redBall.y == blueBall.y + 1) break;
                blueBall.y += 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }

            while (redBall.y + 1 <= M - 2) {
                if (MAP[redBall.x][redBall.y + 1] == '#') break;
                if (redBall.x == blueBall.x && redBall.y + 1 == blueBall.y) break;
                redBall.y += 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }
        } else {
            while (redBall.y + 1 <= M - 2) {
                if (MAP[redBall.x][redBall.y + 1] == '#') break;
                if (redBall.x == blueBall.x && redBall.y + 1 == blueBall.y) break;
                redBall.y += 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }

            while (blueBall.y + 1 <= M - 2) {
                if (MAP[blueBall.x][blueBall.y + 1] == '#') break;
                if (MAP[redBall.x][redBall.y] != 'O' && redBall.x == blueBall.x && redBall.y == blueBall.y + 1) break;
                blueBall.y += 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }
        }
    }

    private void rollingLeft(Ball redBall, Ball blueBall) {
        if (redBall.y < blueBall.y) {
            while (redBall.y - 1 >= 1) {
                if (MAP[redBall.x][redBall.y - 1] == '#') break;
                if (redBall.x == blueBall.x && redBall.y - 1 == blueBall.y) break;
                redBall.y -= 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }

            while (blueBall.y - 1 >= 1) {
                if (MAP[blueBall.x][blueBall.y - 1] == '#') break;
                if (MAP[redBall.x][redBall.y] != 'O' && redBall.x == blueBall.x && redBall.y == blueBall.y - 1) break;
                blueBall.y -= 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }

        } else {
            while (blueBall.y - 1 >= 1) {
                if (MAP[blueBall.x][blueBall.y - 1] == '#') break;
                if (redBall.x == blueBall.x && redBall.y == blueBall.y - 1) break;
                blueBall.y -= 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }

            while (redBall.y - 1 >= 1) {
                if (MAP[redBall.x][redBall.y - 1] == '#') break;
                if (redBall.x == blueBall.x && redBall.y - 1 == blueBall.y) break;
                redBall.y -= 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }
        }
    }

    private void rollingUp(Ball redBall, Ball blueBall) {
        if (redBall.x < blueBall.x) {
            while (redBall.x - 1 >= 1) {
                if (MAP[redBall.x - 1][redBall.y] == '#') break;
                if (redBall.x - 1 == blueBall.x && redBall.y == blueBall.y) break;
                redBall.x -= 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }

            while (blueBall.x - 1 >= 1) {
                if (MAP[blueBall.x - 1][blueBall.y] == '#') break;
                if (MAP[redBall.x][redBall.y] != 'O' && redBall.x == blueBall.x - 1 && redBall.y == blueBall.y) break;
                blueBall.x -= 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }
        } else {
            while (blueBall.x - 1 >= 1) {
                if (MAP[blueBall.x - 1][blueBall.y] == '#') break;
                if (redBall.x == blueBall.x - 1 && redBall.y == blueBall.y) break;
                blueBall.x -= 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }

            while (redBall.x - 1 >= 1) {
                if (MAP[redBall.x - 1][redBall.y] == '#') break;
                if (redBall.x - 1 == blueBall.x && redBall.y == blueBall.y) break;
                redBall.x -= 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }
        }


    }

    private void rollingDown(Ball redBall, Ball blueBall) {
        if (redBall.x < blueBall.x) {
            while (blueBall.x + 1 <= N - 2) {
                if (MAP[blueBall.x + 1][blueBall.y] == '#') break;
                if (redBall.x == blueBall.x + 1 && redBall.y == blueBall.y) break;
                blueBall.x += 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }

            while (redBall.x + 1 <= N - 2) {
                if (MAP[redBall.x + 1][redBall.y] == '#') break;
                if (redBall.x + 1 == blueBall.x && redBall.y == blueBall.y) break;
                redBall.x += 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }
        } else {
            while (redBall.x + 1 <= N - 2) {
                if (MAP[redBall.x + 1][redBall.y] == '#') break;
                if (redBall.x + 1 == blueBall.x && redBall.y == blueBall.y) break;
                redBall.x += 1;
                if (MAP[redBall.x][redBall.y] == 'O') break;
            }

            while (blueBall.x + 1 <= N - 2) {
                if (MAP[blueBall.x + 1][blueBall.y] == '#') break;
                if (MAP[redBall.x][redBall.y] != 'O' && redBall.x == blueBall.x + 1 && redBall.y == blueBall.y) break;
                blueBall.x += 1;
                if (MAP[blueBall.x][blueBall.y] == 'O') break;
            }
        }
    }

    private class Ball {
        int x;
        int y;

        public Ball(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}