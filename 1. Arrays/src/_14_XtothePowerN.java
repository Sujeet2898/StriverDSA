/*
Question: Pow(x, n)
Implement pow(x, n), which calculates x raised to the power n (i.e., xn)

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
 */
public class _14_XtothePowerN {

    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public double myPow1(double x, int n) {
        double ans = 1.0;
        for(int i = 0; i < n; i++){
            ans = ans * x;
        }
        return ans;
    }

    // Time Complexity: O(logn)
    // Space Complexity: O(1)
    public double myPow2(double x, int n) {
        double ans = 1.0;
        long nn = n;
        
        if (nn < 0){
            nn = -1 * nn;
        }
        
        while (nn > 0) {
            if (nn % 2 == 1) {
                ans = ans * x; // number is multiply by initial ans
                nn = nn - 1; // power is reduced by 1
            } else {
                // nn % 2 == 0
                x = x * x;  // number is multiply by itself
                nn = nn / 2;  // power is halfed
            }
        }
        
        if (n < 0){
            ans = (double)(1.0) / (double)(ans);
        }
        return ans;
    }
}
