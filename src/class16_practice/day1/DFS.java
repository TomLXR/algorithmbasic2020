package class16_practice.day1;

import java.util.Stack;

public class DFS {

    public static void dfs(int[] array){
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while(!stack.isEmpty()){
            int p = stack.pop();
            System.out.println(array[p]);
            int left = 2*p+2;
            int right = 2*p+1;
            if(left<array.length){
                stack.push(left);
            }
            if(right<array.length){
                stack.push(right);
            }
        }

    }

    public static void main(String[] args) {

        int[] array = {1,2,3,4,5,6,7,8,9,10};
        dfs(array);
    }
}
