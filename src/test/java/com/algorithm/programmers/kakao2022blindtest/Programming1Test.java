package com.algorithm.programmers.kakao2022blindtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Programming1Test {

    private Programming1 programming1;

    @BeforeEach
    void setup() {
        programming1 = new Programming1();
    }

    @Test
    void solutionTest() {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;

        int[] answer = programming1.solution(id_list, report, k);
        int[] expect = {2,1,1,0};
        assertThat(answer).isEqualTo(expect);
    }
}