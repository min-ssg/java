package com.algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Baekjoon_17845_수강과목 {

    public static int maxNeedStudyTime;
    public static int numberOfSubject;
    public static List<Subject> list = new ArrayList<>();

    public static void main(String[] args) {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            maxNeedStudyTime = Integer.parseInt(stringTokenizer.nextToken());
            numberOfSubject = Integer.parseInt(stringTokenizer.nextToken());

            int[][] table = new int[numberOfSubject + 1][maxNeedStudyTime + 1];


            for (int i = 1; i <= numberOfSubject; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int grade = Integer.parseInt(stringTokenizer.nextToken());
                int studyTime = Integer.parseInt(stringTokenizer.nextToken());

                for (int j = 0; j <= maxNeedStudyTime; j++) {
                    if (j < studyTime) {
                        table[i][j] = table[i-1][j];
                        continue;
                    }
                    table[i][j] = table[i-1][j] >= grade + table[i-1][j - studyTime] ? table[i-1][j] : grade + table[i-1][j - studyTime];
                }

                System.out.println(Arrays.toString(table[i]));
            }

            System.out.println(table[numberOfSubject][maxNeedStudyTime]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Subject {
    private int grade;
    private int studyTime;

    public Subject(int grade, int studyTime) {
        this.grade = grade;
        this.studyTime = studyTime;
    }

    public int getGrade() {
        return grade;
    }

    public int getStudyTime() {
        return studyTime;
    }

    @Override
    public String toString() {
        return String.format("{%s : %s}", grade, studyTime);
    }
}
