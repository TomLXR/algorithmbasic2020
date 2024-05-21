package class25_practice;

import java.util.Stack;

public class CountSubmatricesWithAllOnes {

	public static int maximalRectangle(char[][] map) {
		if(map == null || map.length == 0) {
			return 0;
		}
		int sum = 0;
		int len = map[0].length;
		int[] arr  = new int[map[0].length];
		for(int i=0;i<map.length;i++) {
			for(int j =0;j<len;j++) {
				arr[j] = map[i][j] == '1'?arr[j]+1:0;
			}
			sum +=  getMaxRect(arr);
		}
		return sum;
	}
	
	public static int nums(int l) {
		return l*(l+1)/2;
	}
	
	public static int getMaxRect(int[] arr) {
		int nums = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<arr.length;i++) {
			while(!stack.isEmpty() && arr[stack.peek()] >= arr[i] ){
				int c =stack.pop();
				int l = !stack.isEmpty() ? stack.peek():-1;
				int r = i;
				nums += nums(r-l-1) * (arr[c] - Math.max((l==-1)?0:arr[l],arr[r]))  ;
			}
			stack.push(i);
		}
		while(!stack.isEmpty()) {
			int  k = stack.pop();
			int left = stack.isEmpty()?-1: stack.peek();
			nums += nums(arr.length-left -1) * (left == -1?arr[k]:(arr[k]-arr[left]));
		}
		return nums;
		
	}
}
