import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);
            StringBuilder sb = new StringBuilder();
            int T = scan.nextInt(); // 테스트 케이스

            for (int i = 0; i < T; i++) {
                int N = scan.nextInt();
                String[] commands = scan.nextLine().split(" ");

                Solution5373 solution5373 = new Solution5373();
                String s = solution5373.solve(commands);
                sb.append(s);
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

class Solution5373 {

    StringBuilder sb = new StringBuilder();

    Cube[][][] rubikCube = new Cube[3][3][3];

    public String solve(String[] commands) {
        initialize();

        for (String command : commands) {
            switch (command) {
                case "U-":
                    xRotate(0,-1);
                    break;
                case "U+":
                    xRotate(0,1);
                    break;
                case "D-":
                    xRotate(2, 1);
                    break;
                case "D+":
                    xRotate(2,-1);
                    break;
                case "F-":
                    yRotate(2,-1);
                    break;
                case "F+":
                    yRotate(2, 1);
                    break;
                case "B-":
                    yRotate(0,1);
                    break;
                case "B+":
                    yRotate(0,-1);
                    break;
                case "L-":
                    zRotate(0,-1);
                    break;
                case "L+":
                    zRotate(0,1);
                    break;
                case "R-":
                    zRotate(2,1);
                    break;
                case "R+":
                    zRotate(2,-1);
                    break;
            }
        }

        printSide("U");

        return sb.toString();
    }

    private void xRotate(int index, int mode) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rubikCube[index][i][j].xRotate(mode);
            }
        }

        Cube temp = rubikCube[index][0][0];
        if (mode > 0) {
            rubikCube[index][0][0] = rubikCube[index][2][0];
            rubikCube[index][2][0] = rubikCube[index][2][2];
            rubikCube[index][2][2] = rubikCube[index][0][2];
            rubikCube[index][0][2] = temp;

            temp = rubikCube[index][0][1];
            rubikCube[index][0][1] = rubikCube[index][1][0];
            rubikCube[index][1][0] = rubikCube[index][2][1];
            rubikCube[index][2][1] = rubikCube[index][1][2];
            rubikCube[index][1][2] = temp;
        } else {
            rubikCube[index][0][0] = rubikCube[index][0][2];
            rubikCube[index][0][2] = rubikCube[index][2][2];
            rubikCube[index][2][2] = rubikCube[index][2][0];
            rubikCube[index][2][0] = temp;

            temp = rubikCube[index][0][1];
            rubikCube[index][0][1] = rubikCube[index][1][2];
            rubikCube[index][1][2] = rubikCube[index][2][1];
            rubikCube[index][2][1] = rubikCube[index][1][0];
            rubikCube[index][1][0] = temp;
        }
    }

    private void yRotate(int index, int mode) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rubikCube[i][index][j].yRotate(mode);
            }
        }

        Cube temp = rubikCube[0][index][0];
        if (mode > 0) {
            rubikCube[0][index][0] = rubikCube[2][index][0];
            rubikCube[2][index][0] = rubikCube[2][index][2];
            rubikCube[2][index][2] = rubikCube[0][index][2];
            rubikCube[0][index][2] = temp;

            temp = rubikCube[0][index][1];
            rubikCube[0][index][1] = rubikCube[1][index][0];
            rubikCube[1][index][0] = rubikCube[2][index][1];
            rubikCube[2][index][1] = rubikCube[1][index][2];
            rubikCube[1][index][2] = temp;
        } else {
            rubikCube[0][index][0] = rubikCube[0][index][2];
            rubikCube[0][index][2] = rubikCube[2][index][2];
            rubikCube[2][index][2] = rubikCube[2][index][0];
            rubikCube[2][index][0] = temp;

            temp = rubikCube[0][index][1];
            rubikCube[0][index][1] = rubikCube[1][index][2];
            rubikCube[1][index][2] = rubikCube[2][index][1];
            rubikCube[2][index][1] = rubikCube[1][index][0];
            rubikCube[1][index][0] = temp;
        }
    }

    private void zRotate(int index, int mode) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rubikCube[i][j][index].zRotate(mode);
            }
        }

        Cube temp = rubikCube[0][0][index];
        if (mode > 0) {
            rubikCube[0][0][index] = rubikCube[2][0][index];
            rubikCube[2][0][index] = rubikCube[2][2][index];
            rubikCube[2][2][index] = rubikCube[0][2][index];
            rubikCube[0][2][index] = temp;

            temp = rubikCube[0][1][index];
            rubikCube[0][1][index] = rubikCube[1][0][index];
            rubikCube[1][0][index] = rubikCube[2][1][index];
            rubikCube[2][1][index] = rubikCube[1][2][index];
            rubikCube[1][2][index] = temp;
        } else {
            rubikCube[0][0][index] = rubikCube[0][2][index];
            rubikCube[0][2][index] = rubikCube[2][2][index];
            rubikCube[2][2][index] = rubikCube[2][0][index];
            rubikCube[2][0][index] = temp;

            temp = rubikCube[0][1][index];
            rubikCube[0][1][index] = rubikCube[1][2][index];
            rubikCube[1][2][index] = rubikCube[2][1][index];
            rubikCube[2][1][index] = rubikCube[1][0][index];
            rubikCube[1][0][index] = temp;
        }
    }

    private void printSide(String side) {
        if ("U".equals(side)) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(rubikCube[0][i][j].getUpColor());
                }
                sb.append('\n');
            }
        }
    }

    private void initialize() {
        for (int i = 0 ;i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    rubikCube[i][j][k] = new Cube();
                }
            }
        }
    }

    class Cube {
        Side up;
        Side down;
        Side front;
        Side back;
        Side left;
        Side right;

        Cube() {
            this.up = new Side('w');
            this.down = new Side('y');
            this.front = new Side('r');
            this.back = new Side('o');
            this.left = new Side('g');
            this.right = new Side('b');
        }

        public char getUpColor() {
            return this.up.color;
        }

        public void xRotate(int mode) {
            Side temp = front;
            if (mode > 0) {
                front = right;
                right = back;
                back = left;
                left = temp;
            } else {
                front = left;
                left = back;
                back = right;
                right = temp;
            }
        }

        public void yRotate(int mode) {
            Side temp = up;
            if (mode > 0) {
                up = left;
                left = down;
                down = right;
                right = temp;
            } else {
                up = right;
                right = down;
                down = left;
                left = temp;
            }
        }

        public void zRotate(int mode) {
            Side temp = up;
            if (mode > 0) {
                up = back;
                back = down;
                down = front;
                front = temp;
            } else {
                up = front;
                front = down;
                down = back;
                back = temp;
            }
        }

        private class Side {
            char color;
            public Side(char color) {
                this.color = color;
            }
        }
    }
}
