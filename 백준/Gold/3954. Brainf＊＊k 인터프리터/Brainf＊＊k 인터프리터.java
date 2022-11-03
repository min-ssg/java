import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            int T = scan.nextInt();
            int[] M = new int[T];
            int[] C = new int[T];
            int[] I = new int[T];
            String[] code = new String[T];
            String[] input = new String[T];

            for (int i = 0; i < T; i++) {
                M[i] = scan.nextInt();
                C[i] = scan.nextInt();
                I[i] = scan.nextInt();
                code[i] = scan.nextLine();
                input[i] = scan.nextLine();
            }

            Solution3954 solution = new Solution3954(T,M,C,I,code,input);
            solution.solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader (BufferedReader br) {
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

class Solution3954 {
    private final StringBuilder sb = new StringBuilder();
    private final int T;
    private final int[] M,C,I;
    private final String[] code, input;

    public Solution3954 (int t,
                         int[] m,
                         int[] c,
                         int[] i,
                         String[] code,
                         String[] input) {
        this.T = t;
        this.M = m;
        this.C = c;
        this.I = i;
        this.code = code;
        this.input = input;
    }

    public void solve() {
        for (int i = 0; i < T; i++) {
            Program program = new Program(M[i],C[i],I[i],code[i],input[i]);
            program.compile();
            sb.append(program.start()).append('\n');
        }

        System.out.println(sb.toString());
    }

    private class Program {
        private final int m,c,i;
        private final String code, input;

        private final int[] memory;
        private int pointer, cursor, inputCursor, count;
        private final Stack<Integer> stack = new Stack<>();
        private final Map<Integer,Integer> pair = new HashMap<>();
        private static final int MOD = 256;
        private static final int MAX_EXECUTE = 50_000_000;

        public Program(int m, int c, int i, String code, String input) {
            this.m = m;
            this.c = c;
            this.i = i;
            this.code = code;
            this.input = input;

            this.memory = new int[m];
        }

        public void compile() {
            for (int i = 0; i < c; i++) {
                char ch = code.charAt(i);
                if (ch == '[') {
                    stack.add(i);
                } else if (ch == ']') {
                    int start = stack.pop();
                    pair.put(start,i);
                    pair.put(i,start);
                }
            }
//            System.out.println(Arrays.toString(pair));
        }

        public String start() {

            boolean isTerminated = true;
            int loopStartIndex = Integer.MAX_VALUE;

            while (cursor < c) {
                char command = code.charAt(cursor);
                switch (command) {
                    case '-':
                        valueDown();
                        break;
                    case '+':
                        valueUp();
                        break;
                    case '<':
                        leftPointer();
                        break;
                    case '>':
                        rightPointer();
                        break;
                    case '[':
                        startLoop();
                        break;
                    case ']':
                        endLoop();
                        break;
                    case '.':
                        break;
                    case ',':
                        readInput();
                        break;
                }

                count++;

                if (count > MAX_EXECUTE) {
                    loopStartIndex = Math.min(loopStartIndex, cursor);
                }

                if (count > MAX_EXECUTE * 2) {
                    isTerminated = false;
                    break;
                }

                cursor++;
            }

            return isTerminated ? "Terminates" : String.format("Loops %s %s", loopStartIndex, pair.get(loopStartIndex));
        }

        private void readInput() {
            if (inputCursor < i) {
                memory[pointer] = input.charAt(inputCursor++);
                return;
            }
            memory[pointer] = MOD - 1;
        }

        private void valueDown() {
            memory[pointer] = (memory[pointer] - 1) % MOD;
        }

        private void valueUp() {
            memory[pointer] = (memory[pointer] + 1) % MOD;
        }

        private void leftPointer() {
            if (pointer - 1 == -1) {
                pointer = m - 1;
                return;
            }
            pointer -= 1;
        }

        private void rightPointer() {
            if (pointer + 1 == m) {
                pointer = 0;
                return;
            }
            pointer += 1;
        }

        private void startLoop() {
            if (memory[pointer] == 0) {
                cursor = pair.get(cursor);
                return;
            }
        }

        private void endLoop() {
            if (memory[pointer] != 0) {
                cursor = pair.get(cursor);
                return;
            }
        }
    }
}
