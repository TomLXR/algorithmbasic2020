package class25_practice;

import java.util.Stack;

public class LargestRectangleInHistogram {
	public static int largestRectangleArea1(int[] height) {
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<height.length;i++) {
			while(!stack.isEmpty() && height[stack.peek()] >= height[i]) {
				int k = stack.pop();
				int left = stack.isEmpty()?-1:stack.peek();
				int right = i;
				max = Math.max(max, (right-left-1)*height[k]);
			}
			stack.push(i);
		}
		while(!stack.isEmpty()) {
			int k = stack.pop();
			max = Math.max(max, (height.length-1 - (stack.isEmpty()?0:stack.peek())) *height[k] );
		}
		return max;
	}
}
