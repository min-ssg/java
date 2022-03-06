package com.algorithm.programmers.highscorekit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Main_Programmers_등굣길Test {

    Main_Programmers_등굣길 main_programmers_등굣길 = new Main_Programmers_등굣길();

    @Test
    @DisplayName("등굣길 예제 테스트")
    void exampleTest() {
        int M = 4;
        int N = 3;
        int[][] puddles = {{2,2}};

        int result = main_programmers_등굣길.solution(M, N, puddles);

        assertThat(result).isEqualTo(4);
    }


}