/*
Question: Nth Root Of M
 */

public class _1_NthRootOfM {

    // Time Complexity: N x log(M x 10^d)
    // Space Complexity: O(1)

    private static double multiply(double number, int n) {
        double ans = 1.0;
        for(int i = 1; i <= n; i++) {
            ans = ans * number;
        }
        return ans;
    }
    public static double findNthRootOfM1(int n, int m){
        double low = 1;
        double high = m;
        double epsilon = 1e-7;

        while(high - low > epsilon) {

            double mid = (low + high) / 2.0;

            if(multiply(mid, n) < m) {
                high = mid;
            }
            else {
                low = mid;
            }
        }
        return (high + low) / 2;
    }

/*
Time Complexity: O(log(N) * log(M)), Where ‘N’ and ‘M’ are input integers.
Reason: In the above algorithm, the search space is ‘M’, and hence in ‘log(M)’ iterations we will find the ‘N’th root, and
in each iteration, we find the ‘N’th power of ‘MIDDLE’ using binary exponentiation which takes ‘log( N )’ iterations,
thus in total ‘log(N)*log(M)’ iterations.
Hence the time complexity will be O( log(N) * log(M) )

Space Complexity: O(1)
Reason: In the above algorithm, we only use variables ‘LOW’, ‘HIGH’, ‘EPS’, and some temporary loop variables,
Hence the space complexity will be O( 1 ).
 */

    public static double findNthRootOfM2(int n, int m) {
        double low = 1;
        double high = m;
        double epsilon = 1e-7;   // margin of error 10^-7

        while(high - low > epsilon) {
            double mid = (high + low) / 2;
            double nRootGuess = Math.pow(mid, n);

            if (nRootGuess > m) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return (high + low) / 2;
    }
}
