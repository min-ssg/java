import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            // Create Input Object
            FastReader scan = new FastReader(br);

            Direction[] directions = Direction.values();
            Queue<FireBall> fireballs = new LinkedList<>();

            // START input
            int N = scan.nextInt();
            int M = scan.nextInt();
            int K = scan.nextInt();

            for (int i = 0; i < M; i++) {
                int x = scan.nextInt() - 1;
                int y = scan.nextInt() - 1;
                int weight = scan.nextInt();
                int speed = scan.nextInt();
                Direction d = directions[scan.nextInt()];

                fireballs.add(new FireBall(x,y,weight,speed,d));
            }
            // END input
            Solution20056 solution = new Solution20056();
            int answer = solution.solve(N,K,fireballs);
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

        public String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

/**
 * Direction enum
 */
enum Direction {
    UP(0,-1,0),RIGHTUP(1,-1,1), RIGHT(2,0,1), RIGHTDOWN(3,1,1),
    DOWN(4,1,0), LEFTDOWN(5,1,-1), LEFT(6,0,-1), LEFTUP(7,-1,-1);

    private int number;
    private int dx;
    private int dy;

    Direction (int number, int dx, int dy) {
        this.number = number;
        this.dx = dx;
        this.dy = dy;
    }

    public int getX() {
        return dx;
    }

    public int getY()  {
        return dy;
    }

    public int getNumber() {
        return number;
    }
}

/**
 * Define FireBall class
 */
class FireBall {
    int x;
    int y;
    int weight;
    int speed;
    Direction d;

    public FireBall(int x, int y, int weight, int speed, Direction d) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.speed = speed;
        this.d = d;
    }

    @Override
    public String toString() {
        return "FireBall{" +
                "x=" + x +
                ", y=" + y +
                ", weight=" + weight +
                ", speed=" + speed +
                ", d=" + d +
                '}';
    }
}

/**
 * Define Solution class
 */
class Solution20056 {
    private int N;
    private Queue<FireBall> fireballs;
    private List<FireBall>[][] MAP;
    Direction[] D = Direction.values();

    public int solve(int n, int k, Queue<FireBall> fireballs) {
        this.N = n;
        this.fireballs = fireballs;
        this.MAP = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                MAP[i][j] = new ArrayList<FireBall>();
            }
        }
        return process(k);
    }

    private int process(int k) {
        // k th loop;
        for (int i = 0; i < k; i++) {
            moveFireBalls(); // first step;
            spreadFireBalls(); // second step;
//            System.out.println("after spread = " + fireballs);
        }
        return fireballs.stream().mapToInt(fireball -> fireball.weight).sum();
    }

    private void moveFireBalls() {
        while (!fireballs.isEmpty()) {
            FireBall fireball = fireballs.poll();
//            System.out.println(fireball);
            int nx = (fireball.x + fireball.d.getX() * fireball.speed + fireball.speed * N) % N;
            int ny = (fireball.y + fireball.d.getY() * fireball.speed + fireball.speed * N) % N;
//            System.out.println("nx = " + nx + ", ny = " + ny);
//            if (nx < 1) nx += N;
//            if (ny < 1) ny += N;
            fireball.x = nx;
            fireball.y = ny;
            MAP[nx][ny].add(fireball);
        }
    }

    private void spreadFireBalls() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (MAP[i][j].isEmpty()) continue;
                if (MAP[i][j].size() == 1) {
                    fireballs.add(MAP[i][j].get(0));
                } else if (MAP[i][j].size() > 1) {
                    int newWeight = MAP[i][j].stream().mapToInt(fireball -> fireball.weight).sum() / 5;
                    if (newWeight > 0) {
                        int newSpeed = MAP[i][j].stream().mapToInt(fireball -> fireball.speed).sum() / MAP[i][j].size();
                        int index = allOddOrEven(MAP[i][j]) ? 0 : 1;
                        for (int k = index; k < 8; k += 2) {
                            fireballs.add(new FireBall(i,j,newWeight,newSpeed,D[k]));
                        }
                    }
                }
                MAP[i][j].clear();
            }
        }
    }

    private boolean allOddOrEven(List<FireBall> list) {
        boolean isEven = list.stream().allMatch(fireball -> fireball.d.getNumber() % 2 == 0);
        boolean isOdd = list.stream().allMatch(fireball -> fireball.d.getNumber() % 2 != 0);
        return isEven || isOdd;
    }
}