package com.algorithm.programmers.weekly;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Programmers_위클리8주차_모음사전Test {

    private Programmers_위클리8주차_모음사전 program = new Programmers_위클리8주차_모음사전();

    @Test
    void testSolution() {
        int[][] sizes = new int[][]{{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int expect = 4000;

        int result = program.solution(sizes);

        assertThat(result).isEqualTo(expect);
    }
}