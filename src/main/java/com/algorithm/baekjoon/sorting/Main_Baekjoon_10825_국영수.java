package com.algorithm.baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_Baekjoon_10825_국영수 {

    private static int N;
    private static Student[] STUDENTS;
    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {

            N = Integer.parseInt(bf.readLine());
            STUDENTS = new Student[N]; // N명
            for (int i = 0; i < N; i++) {
                String[] word = bf.readLine().split(" ");
                STUDENTS[i] = new Student(word[0],
                        Integer.parseInt(word[1]),
                        Integer.parseInt(word[2]),
                        Integer.parseInt(word[3]));
            }
            // 정렬
            Arrays.sort(STUDENTS);

            Arrays.stream(STUDENTS).forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Student implements Comparable<Student> {

        private String name;
        private int korean, english, math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        public int getKorean() {
            return korean;
        }

        public int getEnglish() {
            return english;
        }

        public int getMath() {
            return math;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(Student other) {
            // 국어 점수 내림차순
            if (this.korean != other.korean) {
                return other.korean - korean;
            }
            // 영어 점수 오름차순
            if (this.english != other.english) {
                return this.english - other.english;
            }
            // 수학 점수 오름차순
            if (this.math != other.math) {
                return other.math - this.math;
            }
            return this.name.compareTo(other.name);
        }
    }
}

