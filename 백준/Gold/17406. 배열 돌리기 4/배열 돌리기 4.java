import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int M = scan.nextInt();
            int K = scan.nextInt();

            int[][] map = new int[N + 1][M + 1];
            List<Operation> operations = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    map[i][j] = scan.nextInt();
                }
            }

            for (int i = 0; i < K; i++) {
                int r = scan.nextInt();
                int c = scan.nextInt();
                int s = scan.nextInt();
                operations.add(new Operation(r,c,s));
            }

            Solution17406 solution = new Solution17406();
            int answer = solution.solve(N,M,K,map,operations);
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

class Operation {

    private final int r;
    private final int c;
    private final int s;

    public Operation(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getS() {
        return s;
    }
}

class Solution17406 {

    private int N,M,K,size,min = Integer.MAX_VALUE;
    private int[][] map;
    private List<Operation> operations;

    boolean[] used;
    int[] selected;

    public int solve(int n, int m, int k, int[][] map, List<Operation> operations) {
        this.N = n;
        this.M = m;
        this.K = k;
        this.map = map;
        this.operations = operations;
        this.size = operations.size();

        this.used = new boolean[size];
        this.selected = new int[size];
        recurrentFunction(0);

        return min;
    }

    private void recurrentFunction(int k) {
        if (k == size) {
            int minValue = getMinValue();
//            System.out.println(Arrays.toString(selected)  + ": minValue = " + minValue);
            min = Math.min(min,minValue);
            return;
        }

        for (int i = 0; i < size; i++) {
            if (used[i]) continue;

            selected[k] = i;
            used[i] = true;
            recurrentFunction(k + 1);
            selected[k] = 0;
            used[i] = false;
        }
    }

    private int getMinValue() {
        int[][] copiedMap = new int[N + 1][M + 1];
        // copiedMap
        for (int i = 1; i <= N; i++) {
            copiedMap[i] = Arrays.copyOf(map[i], M + 1);
        }

        for (int x : selected) {
            Operation operation = operations.get(x);
            int r = operation.getR();
            int c = operation.getC();
            int s = operation.getS();
            for (int i = s; i >= 1; i--) {
                rotate(r,c,i,copiedMap);
            }
//            printMap(copiedMap);
        }

        return Arrays.stream(copiedMap,1,N + 1)
                .mapToInt(arr -> Arrays.stream(arr).sum())
                .min()
                .getAsInt();
    }

    private void printMap(int[][] copiedMap) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sb.append(copiedMap[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

    private void rotate(int r, int c, int s, int[][] copiedMap) {
        int temp = copiedMap[r - s][c - s];

        for (int i = r - s; i < r + s; i++) {
            copiedMap[i][c - s] = copiedMap[i + 1][c - s];
        }

        for (int i = c - s; i < c + s; i++) {
            copiedMap[r + s][i] = copiedMap[r + s][i + 1];
        }

        for (int i = r + s; i > r - s; i--) {
            copiedMap[i][c + s] = copiedMap[i - 1][c + s];
        }

        for (int i = c + s; i > c - s; i--) {
            copiedMap[r - s][i] = copiedMap[r - s][i - 1];
        }

        copiedMap[r - s][c - s + 1] = temp;
    }
}