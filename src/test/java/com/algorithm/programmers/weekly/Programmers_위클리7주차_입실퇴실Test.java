package com.algorithm.programmers.weekly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Programmers_위클리7주차_입실퇴실Test {

    private Programmers_위클리7주차_입실퇴실 problem;

    @BeforeEach
    void setup() {
        problem = new Programmers_위클리7주차_입실퇴실();
    }

    @Test
    void solutionTest() {
        int[] enter = {1,4,2,3};
        int[] leave = {2,1,3,4};

        int[] expect = {2,2,1,3};

        int[] answer = problem.solution(enter, leave);

        assertThat(answer).isEqualTo(expect);
    }
}