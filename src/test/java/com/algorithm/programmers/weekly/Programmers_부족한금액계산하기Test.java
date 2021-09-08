package com.algorithm.programmers.weekly;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Programmers_부족한금액계산하기Test {

    private Programmers_부족한금액계산하기 solution;

    @BeforeEach
    void setSolution() {
        solution = new Programmers_부족한금액계산하기();
    }

    @Test
    void solutionTest() {

        long result = this.solution.solution(2500, 1_000_000_000, 2500);
        System.out.println(result);
//        assertThat(result).isEqualTo(10);
    }

}