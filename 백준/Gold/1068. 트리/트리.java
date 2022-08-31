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

            int N = scan.nextInt(); // N > 0 && N <= 50
            int[] parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = scan.nextInt();
            }
            int removeNode = scan.nextInt();
            Solution1068 solution = new Solution1068();
            long answer = solution.solve(N, parents, removeNode);
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

class Solution1068 {

    List<Integer>[] graph;
    int[] leaves;

    public long solve(int n, int[] parents, int removeNode) {
        this.graph = new ArrayList[n];
        this.leaves = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            int parent = parents[i];
            if (parent == -1) {
                root = i;
                continue;
            }
            graph[parent].add(i);
        }

        for (int i = 0; i < n; i++) {
            if (graph[i].contains(removeNode)) {
                graph[i].remove(graph[i].indexOf(removeNode));
            }
        }
        if (root == removeNode) return 0;
        return dfs(root);
    }

    private int dfs(int x) {
        if (graph[x].isEmpty()) return 1;
        int leaf = 0;
        for (int next : graph[x]) {
            leaf += dfs(next);
        }
        return leaf;
    }
}