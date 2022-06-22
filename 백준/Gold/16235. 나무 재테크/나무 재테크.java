import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int K = scan.nextInt();
            int[][] A = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    A[i][j] = scan.nextInt();
                }
            }

            List<Tree> trees = new LinkedList<>();
            for (int i = 0; i < M; i++) {
                trees.add(new Tree(scan.nextInt(), scan.nextInt(), scan.nextInt()));
            }

            Solution16235 solution = new Solution16235();
            int answer = solution.solve(N,M,K,A,trees);
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

class Solution16235 {

    private int N,M,K;
    private int[][] A, deadNutrient;
    Room[][] rooms;

    int[] dx = {-1,-1,-1,0,1,1,1,0};
    int[] dy = {-1,0,1,1,1,0,-1,-1};

    public int solve(int n, int m, int k, int[][] a, List<Tree> trees) {
        initialize(n,m,k,a,trees);

        for (int i = 0; i < k; i++) {
            springSummer();
            //summer();
            autum();
            winter();
        }

        return getNumberOfTrees();
    }

    private void springSummer() {
        deadNutrient = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (rooms[r][c].isTreeEmpty()) continue;
                intakeOfNutrients(rooms[r][c]);
                rooms[r][c].nutrient += deadNutrient[r][c];
            }
        }
    }

    private void intakeOfNutrients(Room room) {

        Collections.sort(room.trees); // 나이 오름차순 정렬.

        for (int i = 0; i < room.trees.size(); i++) {
            Tree tree = room.trees.get(i);
            if (room.nutrient < tree.age) {
                deadNutrient[tree.x][tree.y] += tree.age / 2;
                room.trees.remove(i--);
                continue;
            }
            room.nutrient -= tree.age;
            tree.age += 1;
        }
    }

    private void autum() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                reproduce(rooms[r][c]);
            }
        }
    }

    private void reproduce(Room room) {
        for (Tree tree : room.trees) {
            if (tree.age % 5 != 0) continue;

            for (int i = 0; i < 8; i++) {
                if (room.x + dx[i] < 1 || room.x + dx[i] > N
                ||  room.y + dy[i] < 1 || room.y + dy[i] > N) continue;
                rooms[room.x + dx[i]][room.y + dy[i]].addTree(new Tree(room.x + dx[i], room.y + dy[i], 1));
            }
        }
    }

    private void winter() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                Room room = rooms[r][c];
                room.nutrient += A[room.x][room.y];
            }
        }
    }

    private void initialize(int n, int m, int k, int[][] a, List<Tree> trees) {
        this.N = n;
        this.M = m;
        this.K = k;
        this.A = a;
        this.rooms = new Room[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                rooms[i][j] = new Room(i,j);
            }
        }

        for (Tree tree : trees) {
            rooms[tree.x][tree.y].addTree(tree);
        }
    }

    private int getNumberOfTrees() {
        int numberOfTrees = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                numberOfTrees += rooms[r][c].getNumberOfTrees();
            }
        }

        return numberOfTrees;
    }
}

class Room {
    int x;
    int y;
    int nutrient;
    List<Tree> trees;

    public Room(int x, int y) {
        this.x = x;
        this.y = y;
        this.nutrient = 5;
        trees = new ArrayList<>();
    }

    public int getNumberOfTrees() {
        return trees.size();
    }

    public boolean isTreeEmpty() {
        return trees.isEmpty();
    }

    public void addTree(Tree tree) {
        trees.add(tree);
    }
}

class Tree implements Comparable<Tree> {
    int x;
    int y;
    int age;
    boolean isDead;
    public Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
        this.isDead = false;
    }

    @Override
    public int compareTo(Tree other) {
        return Integer.compare(this.age, other.age);
    }
}