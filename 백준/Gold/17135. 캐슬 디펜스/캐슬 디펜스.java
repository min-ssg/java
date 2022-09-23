import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int D = scan.nextInt();

            int[][] map = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            Solution17135 solution = new Solution17135();
            int answer = solution.solve(N,M,D,map);
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

class Solution17135 {

    class Unit {
        int x;
        int y;

        public Unit(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Enemy extends Unit {
        int hp;
        Enemy (int x, int y, int hp) {
            super(x, y);
            this.hp = hp;
        }

        public Enemy(Enemy enemy) {
            super(enemy.x, enemy.y);
            this.hp = enemy.hp;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + "," + hp + ")";
        }
    }

    class Archer extends Unit {
        int distance;
        Archer (int x, int y, int distance) {
            super(x,y);
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + "," + distance + ")";
        }
    }

    private int N,M,D, max = Integer.MIN_VALUE;
    Set<Enemy> enemies;
    Set<Archer> archers;

    public int solve(int n, int m, int d, int[][] map) {
        this.N = n;
        this.M = m;
        this.D = d;
        this.enemies = new HashSet<>();
        this.archers = new HashSet<>();

        // Enemy Map initialize
        for (int i = 0; i < n; i++) { // 5
            for (int j = 0; j < m; j++) { // 5
                if (map[i][j] == 1) {
                    enemies.add(new Enemy(i,j,1));
                }
            }
        }

        recurrentFunction(0);

        return max;
    }

    private void recurrentFunction(int start) {
        if (archers.size() == 3) {
            int count = gameStart();
            max = Math.max(max,count);
            return;
        }

        for (int i = start; i < M; i++) {
            Archer archer = new Archer(N, i, D);
            archers.add(archer);
            recurrentFunction(i + 1);
            archers.remove(archer);
        }
    }

    private int gameStart() {
        Set<Enemy> copyOfEnemies = new HashSet<>();
        for (Enemy enemy : enemies) {
            copyOfEnemies.add(new Enemy(enemy));
        }
        int count = 0;
        while (!copyOfEnemies.isEmpty()) {
            count += attacked(copyOfEnemies);
            if (copyOfEnemies.isEmpty()) break;
            moveEnemies(copyOfEnemies);
        }
        return count;
    }

    private int attacked(Set<Enemy> copyOfEnemies) {
        int count = 0;
        Queue<Enemy> attackedEnemies = new LinkedList<>();
        for (Archer archer : archers) {
            Enemy attackedEnemy = null;
            int min = Integer.MAX_VALUE;
            for (Enemy enemy : copyOfEnemies) {
                int diff = getDistanceDiff(archer, enemy);
                if (diff > archer.distance) continue; // 궁수거리보다 길 경우, 적을 못잡음.

                if (min > diff) {
                    min = diff;
                    attackedEnemy = enemy;
                } else if (min == diff) {
                    attackedEnemy = attackedEnemy.y > enemy.y ? enemy : attackedEnemy;
                }
            }

            if (attackedEnemy != null) {
                attackedEnemies.add(attackedEnemy);
            }
        }

        while (!attackedEnemies.isEmpty()) {
            Enemy enemy = attackedEnemies.poll();
            if (copyOfEnemies.contains(enemy)) {
                count += 1;
                copyOfEnemies.remove(enemy);
            }
        }

        return count;
    }

    private void moveEnemies(Set<Enemy> copyOfEnemies) {
        Queue<Enemy> removeEnemies = new LinkedList<>();

        for (Enemy enemy : copyOfEnemies) {
           enemy.x += 1;
           if (enemy.x == N) removeEnemies.add(enemy);
        }

        while (!removeEnemies.isEmpty()) {
            copyOfEnemies.remove(removeEnemies.poll());
        }
    }

    private int getDistanceDiff(Unit u1, Unit u2) {
        return Math.abs(u1.x - u2.x) + Math.abs(u1.y - u2.y);
    }
}
