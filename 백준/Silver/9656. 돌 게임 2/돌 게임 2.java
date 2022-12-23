import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            String[] winners = new String[N + 1];
            winners[1] = "CY";

            for (int i = 2; i <= N; i++) {
                if (i - 3 > 0) {
                    winners[i] = "CY".equals(winners[i - 3]) ? "SK" : "CY";
                }

                winners[i] = "CY".equals(winners[i - 1]) ? "SK" : "CY";
            }

//            System.out.println(Arrays.toString(winners));
            System.out.println(winners[N]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
