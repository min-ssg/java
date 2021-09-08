package com.algorithm.dynamic;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Baekjoon_12865_평범한배낭_G5 {

    public static List<Product> packages = new ArrayList<>();

    public static int N;
    public static int K;

    public static int[][] dp;

    public static void main(String[] args) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            StringTokenizer st = new StringTokenizer(bf.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            dp = new int[N+1][K+1];

            packages.add(new Product(0, 0));
            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(bf.readLine());

                int weight = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                packages.add(new Product(weight, value));
            }

            int size = packages.size();
            for (int i = 1; i < size; i++) {

                Product product = packages.get(i);
                int weight = product.getWeight();
                int value = product.getValue();

                for (int j = 1; j <= K; j++) {

                    if (j < weight ) {
                        dp[i][j] = dp[i-1][j];
                        continue;
                    }

                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight] + value);
                }
            }

            for (int i = 0; i < size; i++) {
                bw.write(Arrays.toString(dp[i]) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Product {

    private final int weight;
    private final int value;

    public Product(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }
}
