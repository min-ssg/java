import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            int[] people = new int[N + 1];
            ArrayList<Integer>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                people[i] = scan.nextInt();
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= N; i++) {
                int count = scan.nextInt();
                for (int j = 0; j < count; j++) {
                    int adjacent = scan.nextInt();
                    graph[i].add(adjacent);
                }
            }

            Solution17471 solution = new Solution17471();

            int answer = solution.solve(N,people,graph);
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

class Solution17471 {

    private int N, min = Integer.MAX_VALUE;
    private int[] people;
    private ArrayList<Integer>[] graph;

    private boolean[] selected;

    public int solve(int n, int[] people, ArrayList<Integer>[] graph) {
        this.N = n;
        this.people = people;
        this.graph = graph;
        this.selected = new boolean[N + 1];

        int size = n / 2;
        for (int k = 1; k <= size; k++) {
            recurrentFunction(1,k,1);
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private void recurrentFunction(int x, int k, int start) {
        if (x == k + 1) {
            int diffPeople = different();
            min = Math.min(min, diffPeople);
            return;
        }

        for (int i = start; i <= N; i++) {
            selected[i] = true;
            recurrentFunction(x + 1, k, i + 1);
            selected[i] = false;
        }
    }

    private int different() {

        int[] visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (selected[i]) {
                dfs(i,visited,1);
                break;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!selected[i]) {
                dfs(i,visited,2);
                break;
            }
        }

        boolean isNotVisited = Arrays.stream(visited, 1, N + 1).anyMatch(x -> x == 0);
        if (isNotVisited) return Integer.MAX_VALUE;

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 1; i <= N; i++) {
            if (selected[i]) sum1 += people[i];
            else sum2 += people[i];
        }

        return Math.abs(sum1 - sum2);
    }

    private void dfs(int x, int[] visited, int value) {
        if (visited[x] != 0) return;
        int v = selected[x] ? 1 : 2;
        if (v != value) return;
        visited[x] = value;

        for (int next : graph[x]) {
            if (visited[next] != 0) continue;
            dfs(next, visited, value);
        }
    }
}