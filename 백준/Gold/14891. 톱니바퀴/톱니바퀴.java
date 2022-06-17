import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            FastReader scan = new FastReader(br);

            String input1 = scan.nextLine();
            String input2 = scan.nextLine();
            String input3 = scan.nextLine();
            String input4 = scan.nextLine();

            int K = scan.nextInt();

            int[][] commands = new int[K][2];

            for (int i = 0; i < K; i++) {
                commands[i][0] = scan.nextInt();
                commands[i][1] = scan.nextInt();
            }

            Solution14891 solution14891 = new Solution14891();
            int answer = solution14891.solve(commands, input1,input2,input3,input4);
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

class Solution14891 {

    Gear[] gears = new Gear[6]; // 인덱스 1부터 시작.

    public int solve(int[][] commands, String input1, String input2, String input3, String input4) {
        initialize(input1, input2, input3, input4);
        execute(commands);
        return calculateScore();
    }

    private void execute(int[][] commands) {

        for (int[] command : commands) {
            int n = command[0];
            int d = command[1];

            Gear current = gears[n];

            LChaining(current, d);
            RChaining(current, d);
            current.rotate(d);
        }
    }

    private void LChaining(Gear current, int d) {
        if (current.LGear == null) return;

        Gear LGear = current.LGear;
        int currentGearLeftType = current.getLeftType();
        int LGearRightType = LGear.getRightType();

        if (currentGearLeftType == LGearRightType) return;
        LChaining(LGear, d * - 1);
        LGear.rotate(d * - 1);
    }

    private int calculateScore() {
        return Arrays.stream(gears,1,5)
                .filter(Gear::isS)
                .mapToInt(gear -> (int) Math.pow(2, gear.index - 1))
                .sum();
    }

    private void RChaining(Gear current, int d) {
        if (current.RGear == null) return;

        Gear RGear = current.RGear;
        int currentGearRightType = current.getRightType();
        int RGearLeftType = RGear.getLeftType();

        if (currentGearRightType == RGearLeftType) return;
        RChaining(RGear, d * -1);
        RGear.rotate(d * -1);
    }

    private void initialize(String... inputs) {
        for (int i = 1; i <= 4; i++) {
            gears[i] = new Gear(i, inputs[i - 1]);
//            gears[i].loop(-1);
        }

        for (int i = 1; i <= 4; i++) {
            gears[i].LGear = gears[i - 1];
            gears[i].RGear = gears[i + 1];
        }
    }
}

class Gear {
    int index;
    Node head;
    Gear LGear;
    Gear RGear;

    Gear(int index, String input) {
        this.index = index;
        for (char c : input.toCharArray()) {
            addNode(new Node(c - '0'));
        }
    }

    private void addNode(Node newNode) {
        if (head == null) head = newNode;
        else if (head.L == null) {
            newNode.L = head;
            newNode.R = head;
            head.L = newNode;
            head.R = newNode;
        } else {
            Node temp = head.L;
            newNode.L = temp;
            newNode.R = head;
            head.L = newNode;
            temp.R = newNode;
        }
    }

    public void loop(int mode) {
        StringBuilder sb = new StringBuilder();
        Node start = null;
        sb.append('[').append(head.type);
        if (mode == 1) { // 시계방향
            start = head.R;
            while (start != head) {
                sb.append(',').append(start.type);
                start = start.R;
            }
        } else {
            start = head.L;
            while (start != head) {
                sb.append(',').append(start.type);
                start = start.L;
            }
        }
        sb.append(']');

        System.out.println(sb.toString());
    }

    public int getLeftType() {
        return head.L.L.type;
    }

    public int getRightType() {
        return head.R.R.type;
    }

    public void rotate(int type) {
        if (type == 1) { // 시계 방향
            head = head.L;
        } else {    // 반시계 방향
            head = head.R;
        }
    }

    public boolean isS() {
        return head.type == 1;
    }

    private class Node {
        int type;
        Node L;
        Node R;

        Node (int type) {
            this.type = type;
        }
    }
}

