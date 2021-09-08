package com.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws Exception {

//        Scanner scanner = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

//        int n = scanner.nextInt();
//        scanner.hasNextLine();
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
        int max = 0;
        int[] arrays = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            arrays[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        dp[0] = arrays[0];

        for (int i = 1; i < n; i++) {
            dp[i] = arrays[i] > arrays[i-1] ? dp[i-1] : arrays[i];
        }

        for (int i = 0; i < n; i++) {
            int temp = arrays[i] - dp[i];
            if (temp > max) {
                max = temp;
            }
        }

        System.out.println(max);
    }
}
