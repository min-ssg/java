package com.algorithm.programmers.skillcheck.level2;

import java.util.Stack;

public class Problem2 {

    public int solution(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (stack.empty()) {
                stack.push(c);
                continue;
            }

            char peek = stack.peek();
            if (c == stack.peek()) {
                stack.pop();
                continue;
            }

            stack.push(c);
        }

        if (stack.empty()) {
            return 1;
        }

        return 0;
    }
}
