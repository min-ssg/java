package com.algorithm.programmers.weekly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Programmers_위클리2주차_상호평가Test {

    private Programmers_위클리2주차_상호평가 solution;

    @BeforeEach
    void setup() {
        solution = new Programmers_위클리2주차_상호평가();
    }

    @Test
    void solutionTest() {
        int[][] scores = {{100,90,98,88,65},{50,45,99,85,77},{47,88,95,80,67},{61,57,100,80,65},{24,90,94,75,65}};
        int[][] scores1 = {{50, 90, 85}, {80, 87, 41}, {50, 87, 41}};
        int[][] scores2 = {{0,0,0}, {0,0,0}, {0,0,0}};
        String expect = "FBABD";
        String expect1 = "DBD";
        assertThat(solution.solution(scores)).isEqualTo(expect);
        assertThat(solution.solution(scores1)).isEqualTo(expect1);
        solution.solution(scores2);
    }
}