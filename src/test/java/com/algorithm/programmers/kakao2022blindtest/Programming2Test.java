package com.algorithm.programmers.kakao2022blindtest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Programming2Test {

    private Programming2 programming2;

    @BeforeEach
    void setup() {
        programming2 = new Programming2();
    }

    @Test
    void solutionTest() {
        int k = 3;
        int n = 999_999;

        String string = programming2.stringToNSystem(n, k);
        System.out.println(string);
//        int answer = programming2.solution();
//        int expect = 2;

//        assertThat("1000000".compareTo("2") > 0).isEqualTo(true);
    }
}