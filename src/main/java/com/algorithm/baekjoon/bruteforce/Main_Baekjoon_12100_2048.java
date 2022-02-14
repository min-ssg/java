    package com.algorithm.baekjoon.bruteforce;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.*;

    public class Main_Baekjoon_12100_2048 {

        static int[] movements = new int[6]; // movements 5번
        static int N;
        static Board2048 board2048;
        static int max = Integer.MIN_VALUE;
//        static boolean found = false;

        public static void main(String[] args) {

            input();
            recurrentFunction(1);
            System.out.println(max);
        }

        private static void recurrentFunction(int k) {
            if (k == 6) { // 5번 움직였다면.
                board2048.init();
                for (int i = 1; i <= 5; i++) {
                    int movement = movements[i];
                    switch (movement) {
                        case 1:
                            board2048.moveLeft();
                            break;
                        case 2:
                            board2048.moveDown();
                            break;
                        case 3:
                            board2048.moveRight();
                            break;
                        case 4:
                            board2048.moveLeft();
                            break;
                    }
                }
                max = Math.max(max, board2048.getMaxValue());
                System.out.println(Arrays.toString(movements));
                board2048.print();
//                found = true;
                return;
            }

            for (int movement = 1; movement <= 4; movement++) {
                movements[k] = movement;
                recurrentFunction(k+1);
                movements[k] = 0;
//                if (found) return;
            }
        }

        private static void input() {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

                FastReader scan = new FastReader(br);
                int size = scan.nextInt();

                board2048 = new Board2048(size);

                for (int i = 1; i <= size; i++) {
                    for (int j = 1; j <= size; j++) {
                        board2048.setBoardValue(i,j,scan.nextInt());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static class FastReader {
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

class Board2048 {

    int N;
    int[][] board;
    int[][] origin;
    Stack<Integer> stack = new Stack<>();
    Queue<Integer> queue = new LinkedList<>();

    public Board2048(int size) {
        this.N = size;
        origin = new int[N+1][N+1];
        board = new int[N+1][N+1];
    }

    public void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                board[i][j] = origin[i][j];
            }
        }
    }

    public void setBoardValue(int x,int y, int value) {
//            board[x][y] = value;
        origin[x][y] = value;
    }

    public void moveUp() {

        for (int col = 1; col <= N; col++) {

            for (int row = N; row >= 1; row--) {
                if (board[row][col] == 0) continue;

                stack.add(board[row][col]);
                board[row][col] = 0;
            }
            move();
            int size = queue.size();
            for (int row = 1; row <= size; row++) {
                board[row][col] = queue.poll();
            }
        }
    }

    public void moveDown() {
        for (int col = 1; col <= N; col++) {
            for (int row = 1; row <= N; row++) {
                if (board[row][col] == 0) continue;

                stack.add(board[row][col]);
                board[row][col] = 0;
            }
            move();
            int size = N - queue.size() + 1;
            for (int row = N; row >= size; row--) {
                board[row][col] = queue.poll();
            }
        }
    }

    public void moveRight() {
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                if (board[row][col] == 0) continue;

                stack.add(board[row][col]);
                board[row][col] = 0;
            }
            move();
            int size = N - queue.size() + 1;
            for (int col = N; col >= size; col--) {
                board[row][col] = queue.poll();
            }
        }
    }

    public void moveLeft() {
        for (int row = 1; row <= N; row++) {
            for (int col = N; col >= 1; col--) {
                if (board[row][col] == 0) continue;

                stack.add(board[row][col]);
                board[row][col] = 0;
            }
            move();
            int size = queue.size();
            for (int col = 1; col <= size; col++) {
                board[row][col] = queue.poll();
            }
        }
    }

    private void move() {
        while (!stack.isEmpty()) {

            int first = stack.pop();

            if (stack.isEmpty()) {
                queue.add(first);
                break;
            }

            int second = stack.pop();
            if (first == second) {
                queue.add(first + second);
            } else {
                queue.add(first);
                stack.add(second);
            }
        }
    }

    public int getMaxValue() {
        int max = Integer.MIN_VALUE;

        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                max = Math.max(max, board[row][col]);
            }
        }

        return max;
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) {
                sb.append(board[row][col]).append(' ');
            }
            sb.append('\n');
        }
        sb.append('\n');
        System.out.println(sb.toString());
    }
}
