import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            int N = scan.nextInt();
            String expr = scan.nextLine();

            Solution16637 solution = new Solution16637();
            int answer = solution.solve(N,expr);
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

        public String nextLine() throws IOException {
            return next();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

class Solution16637 {

    private int size, max = Integer.MIN_VALUE;
    private char[] braces, expr;

    public int solve(int n, String expr) {

        this.expr = expr.toCharArray();
        this.size = n / 2 + 1;
        braces = new char[size];

        recurrentFunction(0);
        return max;
    }

    private void recurrentFunction(int k) {
        if (k == size) {
            int result = calculate();
            max = Math.max(max, result);
            return;
        }

        if (k > 0 && braces[k - 1] == '(') {
            braces[k] = ')';
            recurrentFunction(k + 1);
            braces[k] = ' ';
            return;
        }

        braces[k] = ' ';
        recurrentFunction(k + 1);
        if (k < size - 1) {
            braces[k] = '(';
            recurrentFunction(k + 1);
            braces[k] = ' ';
        }
    }

    private int calculate() {

        Stack<Integer> stack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        int result = 0;

        stack.add(expr[0] - '0');
        for (int i = 1; i < size ; i++) {
            if (braces[i] == ' ' || braces[i] == ')') {

                int x = stack.pop();
                int y = expr[i * 2] - '0';
                char operator = expr[i * 2 - 1];
                int value = cal(x, y, operator);

                while (!stack.isEmpty()) {
                    int top = stack.pop();
                    char oper = operatorStack.pop();
                    value = cal(top, value, oper);
                }

                stack.add(value);
                continue;
            }

            stack.add(expr[i * 2] - '0');
            operatorStack.add(expr[i * 2 - 1]);
        }
        return stack.pop();
    }

    private int cal(int x, int y, char operator) {
        if (operator == '+') {
            return x + y;
        } else if (operator == '-') {
            return x - y;
        }
        return x * y;
    }
}