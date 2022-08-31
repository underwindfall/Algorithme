package interview.booking;
// You are given an List of positions of cars as to where they are parked. 

import java.util.Arrays;

// You are also given an integer K. 

// The cars needs to be covered with a roof. You have to find the minimum length of roof that takes to cover K cars.

// Input : 12,6,5,2      K = 3

// Explanation :  There are two possible roofs that can cover K cars. One that covers the car in 2,5,6 parking spots and
// another roof which covers 5,6,12 parking spots. The length of these two roofs are 5 and 8 respectively. Return 5
// since that's the roof with minimum length that covers K cars.

// Output : 5
public class MinLengthParkedKCars {
    int minLengthOfParkedKCars(int[] cars, int k) {
        Arrays.sort(cars);
        int left = 0, right = k - 1, ans = Integer.MAX_VALUE;
        for (; right < cars.length; left++, right++) {
            ans = Math.min(ans, cars[right] - cars[left] + 1);
        }
        return ans;
    }
}
