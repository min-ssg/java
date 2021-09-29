package com.algorithm.programmers.monthly.season1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Problem_삼각달팽이Test {

    private Problem_삼각달팽이 problem;

    @BeforeEach
    void setup() {
        problem = new Problem_삼각달팽이();
    }

    @Test
    void solutionTest() {
        int n = 6;
        int[] expect = {1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11};
//        int n2 = 4;
//        int[] expect2 = {1,2,9,3,10,8,4,5,6,7};

        int[] result = problem.solution(n);
        System.out.println(Arrays.toString(result));
//        int[] result2 = problem.solution(n2);
//        System.out.println(Arrays.toString(result2));

        assertThat(result).isEqualTo(expect);
//        assertThat(result2).isEqualTo(expect2);
    }
}