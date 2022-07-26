import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * knapsack algorithm
 */
public class knapsack {

    static Integer[][] dp;
    static int[] weight;
    static int[] value;

    static int n, k;

    static int pi(String s){return Integer.parseInt(s);}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = pi(st.nextToken()); // 물건의 개수
        k = pi(st.nextToken()); // 배낭에 들어갈 수 있는 무게

        weight = new int[n]; // 물건의 무게
        value = new int[n]; // 물건의 가치

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = pi(st.nextToken());
            value[i] = pi(st.nextToken());
        }
        
        int answer = algorithm(n-1, k);
        System.out.println(answer);
    }

    private static int algorithm(int idx, int capacity) {
        if(idx<0) return 0;

        if(dp[idx][capacity] == null){
            if(weight[idx] > capacity){ //만약 배낭에 idx의 물건을 넣을 수 있는 공간이 없다면 다음 물건으로 이동
                dp[idx][capacity] = algorithm(idx-1, capacity);
            } else { //만약 배낭에 idx의 물건을 넣을 수 있는 공간이 있다면
                dp[idx][capacity] = Math.max(algorithm(idx-1, capacity), algorithm(idx-1, capacity-weight[idx]) + value[idx]);
            }
        }

        return dp[idx][capacity];
    }
}
