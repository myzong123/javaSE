package list;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author myz
 * @create 2023/2/21-20:14
 */
public class LearnArrayList {
    public static void main(String[] args) { // 一般try catch处理
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        minSubArrayLen(target,nums);
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int low = 0;
        int fast = 0;
        int sum = 0;
        while(fast < nums.length){
            if(nums[fast] > target) return 1;
            sum += nums[fast];
            if(sum < target){
                fast++;
            }
            while(sum >= target){
                minLen = Math.min(minLen, fast - low + 1);
                sum -= nums[low++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}


