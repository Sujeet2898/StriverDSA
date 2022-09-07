/*
    Time complexity: O(N)
    Space complexity: O(1)

    Where N is the length of the given array.

*/
import java.util.Scanner;
import java.util.ArrayList;

public class _11_MissingAndRepeatingNumbers {

    public static int[] missingAndRepeating(ArrayList<Integer> arr, int n) {
            int xor = 0;
            for(int val : arr){
                xor ^= val;
            }
            for(int i = 1; i <= arr.size(); i++){
                xor ^= i;
            }

            int rsbm = xor & -xor; // Finding Right Most Set Bit (RSB) Mask of xxory

            int x = 0;
            int y = 0;
            for(int val : arr){
                // bit off
                if ((val & rsbm) == 0){
                    x = x ^ val;
                }
                // bit on
                else {
                    y = y ^ val;
                }
            }
            for(int i = 1; i <= arr.size(); i++){
                // bit off
                if ((i & rsbm) == 0){
                    x = x ^ i;
                }
                // bit on
                else {
                    y = y ^ i;
                }
            }

            int r = 0, m = 0;
            for (int val : arr){
                if (val == x){
                    m = y;
                    r = x;
                    break;
                }else if (val == y){
                    m = x;
                    r = y;
                    break;
                }
            }

            int[] ans = new int[]{m, r};
            return ans;

        }

    }

    // 1. Right Most Set Bit (RSB) Mask
    public static void rightMostSetBitMask(int n){
        int rsbm = n & -n;
        System.out.println(Integer.toBinaryString(rsbm));
    }
    /*
    Input:
    58
    Output:
    10
     */

    // 2. Kernighans Algorithm (You have to count the number of set bits in the given number)
    public static void kernighansAlgorithm(int n){
        int counter = 0;
        while(n != 0){
            int rsbm = n & -n; // Finding Right Most Set Bit (RSB) Mask
            n -= rsbm;
            counter++;
        }
        System.out.println(counter);
    }
    /*
    Input:
    58
    Output:
    4
     */

    // 3. All Repeating Except One
    public static void allRepeatingExceptOne(int[] arr){
        int uni = 0;
        for(int val : arr){
            uni = uni ^ val; // uni ^= val;
        }
        System.out.println(uni);
    }
    /*
    Input:
    5
    23 27 23 17 17
    Output:
    27
     */

    // 4. All Repeating Except Two
    public static void allRepeatingExceptTwo(int[] arr){
        int xxory = 0;
        for(int val : arr){
            xxory = xxory ^ val;  // xxory ^= val;
        }

        int rsbm = xxory & -xxory; // Finding Right Most Set Bit (RSB) Mask of xxory

        int x = 0;
        int y = 0;
        for(int val : arr){
            // bit off
            if ((val & rsbm) == 0){
                x = x ^ val;
            }
            // bit on
            else {
                y = y ^ val;
            }
        }

        if (x < y){
            System.out.println(x);
            System.out.println(y);
        }else {
            System.out.println(y);
            System.out.println(x);
        }
    }
    /*
    Input:
    6
    23 27 23 17 17 37
    Output:
    27
    37
     */

    //  5. Duplicate Number and Missing Number
    public static void  duplicateNumberAndMissingNumber(int[] arr){
        int xor = 0;
        for(int val : arr){
            xor ^= val;
        }
        for(int i = 1; i <= arr.length; i++){
            xor ^= i;
        }

        int rsbm = xor & -xor; // Finding Right Most Set Bit (RSB) Mask of xxory

        int x = 0;
        int y = 0;
        for(int val : arr){
            // bit off
            if ((val & rsbm) == 0){
                x = x ^ val;
            }
            // bit on
            else {
                y = y ^ val;
            }
        }
        for(int i = 1; i <= arr.length; i++){
            // bit off
            if ((i & rsbm) == 0){
                x = x ^ i;
            }
            // bit on
            else {
                y = y ^ i;
            }
        }

        for (int val : arr){
            if (val == x){
                System.out.println("Missing Number -> " + y);
                System.out.println("Repeating Number -> " + x);
                break;
            }else if (val == y){
                System.out.println("Missing Number -> " + x);
                System.out.println("Repeating Number -> " + y);
                break;
            }
        }
    }
    /*
    Input:
    7
    1 3 4 5 1 6 2
    Output:
    Missing Number -> 7
    Repeating Number -> 1
     */

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0 ; i < n; i++){
            arr[i] = scn.nextInt();
        }
        rightMostSetBitMask(n);
        kernighansAlgorithm(n);
        allRepeatingExceptOne(arr);
        allRepeatingExceptTwo(arr);
        duplicateNumberAndMissingNumber(arr);
        duplicateNumberAndMissingNumber(arr);
    }
}

