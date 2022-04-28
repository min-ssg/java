import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();
            int N = scan.nextInt();
            int[] infix = new int[N + 1];
            int[] postfix = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                infix[i] = scan.nextInt();
            }

            for (int i = 1; i <= N; i++) {
                postfix[i] = scan.nextInt();
            }

            Solution2263 solution2263 = new Solution2263();
            int[] answer = solution2263.solve(N, infix, postfix);
            for (int i = 1; i <= N; i++) {
                sb.append(answer[i]).append(' ');
            }
            System.out.println(sb.toString());
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

class Solution2263 {

    private int N, index = 1;
    int[] infix, postfix, infixIndices, prefix;

    public int[] solve(int n, int[] infix, int[] postfix) {
        this.N = n;
        this.infix = infix;
        this.postfix = postfix;
        this.infixIndices = new int[N + 1];
        this.prefix = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            infixIndices[infix[i]] = i;
        }

        recurrentFunction(new Tree(1,N), new Tree(1,N));

        return prefix;
    }

    private void recurrentFunction(Tree infixTree, Tree postfixTree) {

        if (infixTree.S > infixTree.E || postfixTree.S > postfixTree.E) return;

        if (infixTree.S == infixTree.E) {
            prefix[index++] = infix[infixTree.S];
            return;
        }

        int rootIndex = infixIndices[postfix[postfixTree.E]];
        prefix[index++] = postfix[postfixTree.E];

        // Left Tree
        recurrentFunction(new Tree(infixTree.S, rootIndex - 1), new Tree(postfixTree.S, postfixTree.S + (rootIndex - infixTree.S) - 1));
        // Right Tree
        recurrentFunction(new Tree(rootIndex + 1, infixTree.E), new Tree(postfixTree.E - (infixTree.E - rootIndex), postfixTree.E - 1));
    }

    private class Tree {
        int S;
        int E;

        public Tree(int S, int E) {
            this.S = S;
            this.E = E;
        }
    }
}
