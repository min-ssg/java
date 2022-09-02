import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        CustomQueue cq1 = new CustomQueue(queue1);
        CustomQueue cq2 = new CustomQueue(queue2);
        int n = queue1.length;
        int count = 0;
        if ((cq1.getSum() + cq2.getSum()) % 2 != 0) return -1;

        while (cq1.getSum() != cq2.getSum()) {
            if (count > 2 * n + 1) return -1;
            if (cq1.getSum() > cq2.getSum()) {
                cq2.add(cq1.pop());
            } else {
                cq1.add(cq2.pop());
            }
            count += 1;
        }

        return count;
    }

    private class CustomQueue {
        Queue<Integer> queue = new LinkedList<>();
        long sum;

        CustomQueue(int[] inputs) {
            for (int element : inputs) {
                queue.add(element);
                sum += element;
            }
        }

        private long getSum() {
            return sum;
        }

        public int pop() {
            int x = queue.poll();
            sum -= x;
            return x;
        }

        public void add(int x) {
            queue.add(x);
            sum += x;
        }
    }
}