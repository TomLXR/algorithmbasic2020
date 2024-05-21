package class25_practice;

import java.util.Stack;

// 测试链接 : https://leetcode.com/problems/maximum-subarray-min-product/
public class AllTimesMinToMax {
	
    public static int max2(int[] nums) {
         int[] sums = new int[nums.length];
         sums[0] = nums[0]; 
         for(int i=1;i<nums.length;i++) {
        	 sums[i] = nums[i] + sums[i-1];
         }
         Stack<Integer> stack = new Stack<>();
         int max = Integer.MIN_VALUE;
         for(int i=0;i<nums.length;i++) {
        	 while(!stack.isEmpty()&& nums[stack.peek()] >= nums[i]) {
        		 int k = stack.pop();
        		 int left = stack.isEmpty()?0:sums[stack.peek()];
        		 int right = sums[i];
        		 max = Math.max(max, (right - left-nums[i]) *nums[k] );
        	 }
        	 stack.push(i);
         }
         while(!stack.isEmpty()) {
        	 
        	 int k = stack.pop();
        	 int left = stack.isEmpty()?0:sums[stack.peek()];
        	 max = Math.max(max, (sums[sums.length-1] - left) * nums[k]);
         }
         return max;
         
    }
	
	public static int[] gerenareRondomArray() {
		int[] arr = new int[(int) (Math.random() * 20) + 10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 101);
		}
		return arr;
	}

	public static int max1(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				int minNum = Integer.MAX_VALUE;
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += arr[k];
					minNum = Math.min(minNum, arr[k]);
				}
				max = Math.max(max, minNum * sum);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		int testTimes = 2000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = gerenareRondomArray();
			if (max1(arr) != max2(arr)) {
				System.out.println("FUCK!");
				break;
			}
		}
		System.out.println("test finish");
	}


}
