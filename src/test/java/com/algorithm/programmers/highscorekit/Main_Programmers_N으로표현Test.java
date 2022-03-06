package com.algorithm.programmers.highscorekit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Main_Programmers_N으로표현Test {

    Main_Programmers_N으로표현 main_programmers_n으로표현 = new Main_Programmers_N으로표현();

    @Test
    @DisplayName("프로그래머스 N으로 표현 예제 데이터 입력")
    void exampleTest() {
        int N = 2;
        int number = 11;

        int result = main_programmers_n으로표현.solution(N, number);

        assertThat(result).isEqualTo(3);
    }

}