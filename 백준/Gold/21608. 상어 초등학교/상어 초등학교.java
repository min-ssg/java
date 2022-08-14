import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N^2의 크기를 갖는 반 크기
 *
 */
public class Main {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[][] inputs = new int[N * N][5];
            for (int i = 0; i < N * N; i++) {
                for (int j = 0; j < 5; j ++) {
                    inputs[i][j] = scan.nextInt();
                }
            }

            Solution21608 solution = new Solution21608();
            int answer = solution.solve(N, inputs);
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

class Solution21608 {

    private int N;
    private int[][] MAP;

    private int[] dx = {-1,0,1,0};
    private int[] dy = {0,1,0,-1};

    public int solve(int n, int[][] inputs) {
        setN(n);
        setMap(n);

        /**
         * 1. 좋아하는 학생이 있는 칸에 인접한 칸에 가장 많은 칸.
         * 2. 1을 만족하는 칸이 여러 개라면, 비어있는 칸이 가장 많은 자리로 간다.
         * 3. 2를 만족하는 칸이 여러 개라면, 열, 행 번호 순으로 간다.
         */
        int[][] input2 = new int[n * n + 1][4];

        for (int[] input : inputs) {
            int target = input[0];
            Room room = getRoom(input);
            MAP[room.x][room.y] = target;

            for (int i = 0; i < 4; i++) {
                input2[target][i] = input[i + 1];
            }
        }

        return getScore(input2);
    }

    private int getScore(int[][] input2) {
        int score = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                score += calculateScore(i, j, input2[MAP[i][j]]);
            }
        }
        return score;
    }

    private int calculateScore(int x, int y, int[] likes) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 1 || x + dx[i] > N
            ||  y + dy[i] < 1 || y + dy[i] > N) continue;
            int target = MAP[x + dx[i]][y + dy[i]];
            if (Arrays.stream(likes).anyMatch(element -> target == element)) count += 1;
        }
        if (count == 0) return 0;
        return (int) Math.pow(10, count - 1);
    }

    private Room getRoom(int[] input) {
        PriorityQueue<Room> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (MAP[i][j] != 0) continue;
                pq.add(findEmptyRoom(input, i, j));
            }
        }
        return pq.poll();
    }

    private Room findEmptyRoom(int[] input, int x, int y) {
        int like = 0;
        int count = 0;

        for (int i = 0; i < 4; i++) { // top, bottom, right, left;
            if (x + dx[i] < 1 || x + dx[i] > N
            ||  y + dy[i] < 1 || y + dy[i] > N) continue;
            if (MAP[x + dx[i]][y + dy[i]] != 0) {
                if (!containsLike(MAP[x + dx[i]][y + dy[i]], input)) continue;
                like += 1;
            }
            count += 1;
        }
        return new Room(x, y, count, like);
    }

    private boolean containsLike(int like, int[] input) {
        int student = input[0];
        return Arrays.stream(input, 1, 5)
                .anyMatch(x -> x == like);
    }

    private void setN(int n) {
        this.N = n;
    }

    private void setMap(int n) {
        this.MAP = new int[N + 1][N + 1];
    }

    private class Room implements Comparable<Room> {
        int x;
        int y;
        int count;
        int like;

        public Room(int x, int y, int count, int like) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.like = like;
        }

        @Override
        public int compareTo(Room other) {
            if (this.like == other.like) {
                if (this.count == other.count) {
                    if (this.x == other.x) {
                        return Integer.compare(this.y, other.y);
                    }
                    return Integer.compare(this.x, other.x);
                }
                return Integer.compare(other.count, this.count);
            }
            return Integer.compare(other.like, this.like);
        }

        @Override
        public String toString() {
            return "Room{" +
                    "x=" + x +
                    ", y=" + y +
                    ", count=" + count +
                    ", like=" + like +
                    '}';
        }
    }
}