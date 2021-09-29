package com.algorithm.programmers.skillcheck.level2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Problem3Test {

    private Problem3 problem3;

    @BeforeEach
    void setup() {
        problem3 = new Problem3();
    }

    @Test
    void solutionTest() {
        String s = "(())()";
        assertTrue(problem3.solution(s));
    }

}