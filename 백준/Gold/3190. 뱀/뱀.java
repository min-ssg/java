import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int K = scan.nextInt();

            boolean[][] MAP = new boolean[N + 1][N + 1];

            for (int i = 0; i < K; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                MAP[x][y] = true;
            }

            int L = scan.nextInt();

            List<Statement> statementList = new ArrayList<>();
            for (int i = 0; i < L; i++) {
                statementList.add(new Statement(scan.nextInt(), scan.next().charAt(0)));
            }

            Solution3190 solution = new Solution3190();
            int answer = solution.solve(N,K,MAP, statementList);
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

class Solution3190 {

    private int N, K;
    private boolean[][] MAP;
    List<Statement> statementList;

    private Snake snake;

    public int solve(int n, int k, boolean[][] map, List<Statement> statementList) {
        initialize(n,k,map,statementList);
        return play();
    }

    private void initialize(int n, int k, boolean[][] map, List<Statement> statementList) {
        this.N = n;
        this.K = k;
        this.MAP = map;
        this.statementList = statementList;
        this.snake = new Snake(new Point(1,1), Direction.RIGHT);
    }

    public int play() {
        int time = 0;

        for (Statement statement : statementList) {
            int executionTime = statement.getExecutionTime() - time;
            Point head = snake.head;
            Direction direction = snake.headDirection;
            int nx = head.x;
            int ny = head.y;
            for (int i = 1; i <= executionTime; i++) {
                time += 1;
                nx += direction.x;
                ny += direction.y;

                if (nx < 1 || nx > N
                        ||  ny < 1 || ny > N) return time;

                if (snake.isBumpIntoOwnBody(nx,ny)) return time;

                if (MAP[nx][ny]) { // apple
                    snake.eatAnApple(new Point(nx,ny));
                    MAP[nx][ny] = false;
                } else {
                    snake.moveNext(new Point(nx, ny));
                }
            }

            if (statement.getCommand() == 'L') {
                // LEFT
                switch (direction) {
                    case UP:
                        snake.headDirection = Direction.LEFT;
                        break;
                    case RIGHT:
                        snake.headDirection = Direction.UP;
                        break;
                    case DOWN:
                        snake.headDirection = Direction.RIGHT;
                        break;
                    case LEFT:
                        snake.headDirection = Direction.DOWN;
                        break;
                }
            } else {
                // RIGHT
                switch (direction) {
                    case UP:
                        snake.headDirection = Direction.RIGHT;
                        break;
                    case RIGHT:
                        snake.headDirection = Direction.DOWN;
                        break;
                    case DOWN:
                        snake.headDirection = Direction.LEFT;
                        break;
                    case LEFT:
                        snake.headDirection = Direction.UP;
                        break;
                }
            }
        }

        Direction direction = snake.headDirection;
        int nx = snake.head.x;
        int ny = snake.head.y;

        while (true) {
            time += 1;
            nx += direction.x;
            ny += direction.y;

            if (nx < 1 || nx > N
            ||  ny < 1 || ny > N) break;

            if (snake.isBumpIntoOwnBody(nx,ny)) break;

            if (MAP[nx][ny]) { // apple
                snake.eatAnApple(new Point(nx,ny));
                MAP[nx][ny] = false;
            } else {
                snake.moveNext(new Point(nx, ny));
            }
        }

        return time;
    }


    private enum Direction {
        UP(-1,0),RIGHT(0,1),DOWN(1,0),LEFT(0,-1);

        int x;
        int y;
        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class Snake {
        Point head;
        Direction headDirection;
        Queue<Point> body;
        Set<Point> bodySet;

        public Snake(Point head, Direction headDirection) {
            this.head = head;
            this.headDirection = headDirection;

            this.body = new LinkedList<>();
            this.body.add(head);
            this.bodySet = new HashSet<>();
            this.bodySet.add(head);
        }

        public void eatAnApple(Point point) {
            this.head = point;
            this.body.add(point);
            this.bodySet.add(point);
        }

        public void moveNext(Point point) {
            this.head = point;
            this.body.add(point);
            this.bodySet.add(point);

            Point tail = body.poll();
            bodySet.remove(tail);
        }

        public boolean isBumpIntoOwnBody(int nx, int ny) {
            return bodySet.contains(new Point(nx,ny));
        }
    }

    private class Point {
        int x;
        int y;

        public  Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Override `hashCode()` and `equals()`

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

class Statement {
    private int executionTime;
    private char command;

    public Statement(int executionTime, char command) {
        this.executionTime = executionTime;
        this.command = command;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public char getCommand() {
        return command;
    }
}