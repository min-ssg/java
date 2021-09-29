package com.algorithm.programmers.weekly;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Programmers_위클리4주차_직업군추천하기Test {

    private Programmers_위클리4주차_직업군추천하기 problem;

    @BeforeEach
    void setup() {
        problem = new Programmers_위클리4주차_직업군추천하기();
    }

    @Test
    void testSolution() {
        String[] tables = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
        String[] languages = {"JAVA", "JAVASCRIPT"};
        int[] preference = {7,5};
        String expect = "PORTAL";

        String result = problem.solution(tables, languages, preference);
        assertThat(result).isEqualTo(result);
    }
}