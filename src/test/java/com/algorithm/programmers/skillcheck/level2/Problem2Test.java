package com.algorithm.programmers.skillcheck.level2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem2Test {

    private Problem2 problem2;

    @BeforeEach
    void setup() {
        problem2 = new Problem2();
    }

    @Test
    void solutionTest() {
        String s = "baabaa";
        int result = 1;

        int answer = problem2.solution(s);

        assertThat(answer).isEqualTo(result);
    }
}