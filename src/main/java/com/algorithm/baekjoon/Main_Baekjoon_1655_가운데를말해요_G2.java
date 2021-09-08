package com.algorithm.baekjoon;

import com.algorithm.entity.Graph;
import com.algorithm.entity.Vertex;

import java.io.*;

public class Main_Baekjoon_1655_가운데를말해요_G2 {

    public static int N;

    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            N = Integer.parseInt(br.readLine());
            Graph graph = new Graph();

            for (int i = 0; i < N; i++) {

                int value = Integer.parseInt(br.readLine());

                graph.add(new Vertex(value));

                System.out.println("ROOT = " + graph.getRoot().getValue());
            }



//            int[] numbers = {1, 5, 2, 10, -99, 7, 5};
//
//            for (int number : numbers) {
//
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


