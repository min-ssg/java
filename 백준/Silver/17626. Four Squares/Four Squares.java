import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            
            // dp[i] 는 i값을 제곱근으로 표현했을 때 최소가 되는 숫자를 의미함.
            int[] dp = new int[N + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[1] = 1;
            
            for (int i = 2; i <= N; i++) {
                int sqrt = (int) Math.sqrt(i);
                // 제곱근과 같다면 가장 최소가 되는 1을 넣어준다.
                if (i == sqrt * sqrt) {
                    dp[i] = 1;
                    continue;
                }
                
                for (int j = 1; j <= sqrt; j++) {
                    int pow = j * j;
                    dp[i] = Math.min(dp[i], dp[pow] + dp[i - pow]);
                }
            }
            
            System.out.println(dp[N]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}