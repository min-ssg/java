import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int N = 4;

            Fish[][] fishArray = new Fish[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    fishArray[i][j] = new Fish(i,j,scan.nextInt(), scan.nextInt() - 1);
                }
            }

            Solution19236 solution19236 = new Solution19236();
            int answer = solution19236.solve(fishArray);
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
 * (0,0) 에서 시작.
 * 1. 상어가 먹이를 먹고,
 * 2. 물고기들 이동 후,
 * 3. 시뮬레이션 시작.
 */
class Solution19236 {

    private int N = 4;
    private int MAX = Integer.MIN_VALUE;

    private int[] dx = {-1,-1,0,1,1,1,0,-1};
    private int[] dy = {0,-1,-1,-1,0,1,1,1};

    public int solve(Fish[][] fishArray) {
        ChildhoodShark shark = beforeSimulation(fishArray);
        simulation(shark.getX(), shark.getY(), shark.getD(), shark.getIntake(), fishArray);
        return MAX;
    }

    private void simulation(int x, int y, int d, int intake, Fish[][] fishArray) {
        // 맵의 크기가 4개 밖에 되지 않기 때문에 3개로만 한다.
        for (int i = 1; i <= 3; i++) {
            int nx = x + dx[d] * i;
            int ny = y + dy[d] * i;

            if (nx < 0 || nx >= 4
            ||  ny < 0 || ny >= 4) break;
            if (fishArray[nx][ny] == null) continue;

            Fish[][] copyFishArray = deepCopy(fishArray);
            ChildhoodShark shark = new ChildhoodShark();
            eatingFish(nx,ny,shark,copyFishArray);
            movingFish(copyFishArray, shark);
            simulation(shark.getX(), shark.getY(), shark.getD(), intake + shark.getIntake(), copyFishArray);
        }

        MAX = Math.max(MAX, intake);
    }

    private Fish[][] deepCopy(Fish[][] fishArray) {
        Fish[][] copyFishArray = new Fish[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (fishArray[i][j] == null) continue;
                copyFishArray[i][j] = fishArray[i][j].clone();
            }
        }

        return copyFishArray;
    }

    private ChildhoodShark beforeSimulation(Fish[][] fishArray) {
        ChildhoodShark shark = new ChildhoodShark();
        // 물고기 먹자.
        eatingFish(0,0,shark,fishArray);
        movingFish(fishArray, shark);
        return shark;
    }

    private void movingFish(Fish[][] fishArray, ChildhoodShark shark) {
        Arrays.stream(fishArray).flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(f -> f.id))
                .forEach(fish -> {
                   Point point = getSwapPoint(fish, shark, fishArray);
                   if (point != null) swap(fish, point, fishArray);
                });
    }

    private void swap(Fish fish, Point point, Fish[][] fishArray) {
        Fish temp = fishArray[point.x][point.y];
        if (temp != null) {
            temp.x = fish.x;
            temp.y = fish.y;
        }
        fishArray[fish.x][fish.y] = temp;
        fish.x = point.x;
        fish.y = point.y;
        fishArray[point.x][point.y] = fish;
    }

    /**
     * fishArray 상어 Point 찾자...
     */
    private Point getSwapPoint(Fish fish, ChildhoodShark shark, Fish[][] fishArray) {
        int x = fish.x;
        int y = fish.y;
        int d = fish.d;

        for (int i = 0; i < 8; i++) { // 방향이 총 8개
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (isMovable(nx,ny,shark)) {
                fish.d = d;
                return new Point(nx, ny);
            }
            d = rotate45(d);
        }

        return null;
    }

    private int rotate45(int d) {
        return d + 1 < 8 ? d + 1 : 0;
    }

    private boolean isMovable(int nx, int ny, ChildhoodShark shark) {
        if (nx < 0 || nx >= N
        ||  ny < 0 || ny >= N) return false;
        if (shark.getX() == nx && shark.getY() == ny) return false;
        return true;
    }

    /**
     * 해당 좌표의 물고기를 상어가 잡아먹음.
     */
    private void eatingFish(int x, int y, ChildhoodShark shark, Fish[][] fishArray) {
        shark.eatFish(fishArray[x][y]);
        fishArray[x][y] = null;
    }

    // 좌표 객체
    class Point {
        int x;
        int y;

        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

class ChildhoodShark {
    Fish fish;

    ChildhoodShark() {}

    void eatFish(Fish fish) {
        this.fish = fish;
    }

    public int getX() {
        return fish.x;
    }

    public int getY() {
        return fish.y;
    }

    public int getD() {
        return fish.d;
    }

    public int getIntake() {
        return fish.id;
    }

    @Override
    public String toString() {
        return "Shark{" +
                "fish=" + fish +
                '}';
    }
}

class Fish  implements Cloneable {
    int x;
    int y;
    int id;
    int d;

    public Fish(int x, int y, int id, int d) {
        this.x = x;
        this.y = y;
        this.id= id;
        this.d = d;
    }

    @Override
    public Fish clone() {
        try {
            return (Fish) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String toString() {
        return "Fish{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                ", d=" + d +
                '}';
    }
}
