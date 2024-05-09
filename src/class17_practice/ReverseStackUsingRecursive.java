package class17_practice;

import java.util.Stack;

public class ReverseStackUsingRecursive {
	
	public static void reverse(Stack<Integer> stack) {
		if(stack.isEmpty()) {
			return;
		}
		int last = f(stack);
		reverse(stack);
		stack.push(last);
	}
	
	public static int f(Stack<Integer>stack) {
		Integer i= stack.pop();
		if(stack.isEmpty()) {
			return i;
		}
		int last = f(stack);
		stack.push(i);
		return last;
	}
	
	/*
	 * 非递归版本
	 */
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(5);
		stack.push(9);
		stack.push(10);
		
		reverse(stack);
		for(int i:stack) {
			System.out.println(i+" ");
		}
	}
	

}
