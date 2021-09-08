package com.algorithm.programmers.weekly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Programmers_위클리6주차_복서정렬하기Test {

    private Programmers_위클리6주차_복서정렬하기 solution;

    @BeforeEach
    void setup() {
        solution = new Programmers_위클리6주차_복서정렬하기();
    }

    @Test
    void solutionTest() {
//        int[] weights = {50, 82, 75, 120};
        int[] weights = {60, 70, 60};
        String[] head2head = {"NNW", "NNN", "LNN"};
        int[] expect = {1,2,3};

        int[] result = solution.solution(weights, head2head);

        assertThat(result).isEqualTo(expect);
    }
}