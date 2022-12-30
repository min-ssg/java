import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            // bottom-up
            BigInteger a = BigInteger.ZERO;
            BigInteger b = BigInteger.ONE;
            BigInteger answer = BigInteger.ZERO;
            
            if (N == 1) {
                answer = BigInteger.ONE;
            }

            for (int i = 2; i <= N; i++) {
                answer = a.add(b);
                a = b;
                b = answer;
            }

            System.out.println(answer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
