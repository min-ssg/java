import java.util.Map;
import java.util.HashMap;

class Solution {
    char[] members = {'A','C','F','J','M','N','R','T'};
    Map<Character, Integer> memberMap = new HashMap<>();
    boolean[] used;
    String[] data;
    int count;
    static final int MEMBERS = 8;

    public int solution(int n, String[] data) {
        this.data = data;
        this.used = new boolean[MEMBERS];
        recursive(0);
        return count;
    }

    private void recursive(int x) {
        if (x == MEMBERS) {
            if (determination()) {
                count += 1;
            }
            return;
        }

        for (int i = 0; i < MEMBERS; i++) {
            if (used[i]) continue;
            memberMap.put(members[x], i);
            used[i] = true;
            recursive(x + 1);
            used[i] = false;
        }
    }

    private boolean determination() {
        for (String d : data) {
            char a = d.charAt(0);
            char b = d.charAt(2);
            char operator = d.charAt(3);
            int distance = d.charAt(4) - '0';
            int aTob = Math.abs(memberMap.get(a) - memberMap.get(b)) - 1;

            if (operator == '>') {
                if (aTob <= distance) return false;
            } else if (operator == '<') {
                if (aTob >= distance) return false;
            } else if (operator == '=') {
                if (aTob != distance) return false;
            }
        }
        return true;
    }
}