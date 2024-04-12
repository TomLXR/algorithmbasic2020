package class16_practice.day1;

import java.util.*;

/*
留存 蛇形留存
 */
public class BFS {

    // 层序遍历
    public static void bfs(int[] array){
        int i = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while(!queue.isEmpty()){
            int p = queue.poll();
            System.out.println(array[p]);
            int right = 2*p+1;
            int left = 2*p+2;

            if(right<array.length){
                queue.add(right);
            }

            if(left<array.length){
                queue.add(left);
            }
        }
    }

    public static class Helper{
        Queue<Integer> queue= new LinkedList<>();
        Stack<Integer> stack =new Stack<>();
        boolean flag ;
        Helper(boolean flag){
             this.flag = flag;
        }

        Helper(){
        }


        public void add(int e){
           if(flag){
               queue.add(e);
           }else{
               stack.add(e);
           }
        }


        public int poll(){
            int ans = -1;
            if(isEmpty()){
                return ans;
            }

            if(flag && !queue.isEmpty()){
               ans =  queue.poll();
               if(queue.isEmpty()){
                   flag = !flag;
               }
               return ans;
            }
            if(!flag && !stack.isEmpty()){
                ans = stack.pop();
                if(stack.isEmpty()){
                    flag = !flag;
                }
                return ans;
            }
            return ans;
        }

        public boolean isEmpty(){
            if(flag) return queue.isEmpty();

            return stack.isEmpty();

        }

    }

    // 蛇形层次遍历  用容器封装切换好难
    public static void snakeBFS(int[] array){


        Helper helper = new Helper(true);
        helper.add(0);
        while(!helper.isEmpty()){
            List<Integer> list = new ArrayList<>();
            while(!helper.isEmpty()){
                int e = helper.poll();
                if(e != -1){
                    list.add(e);
                    System.out.print(array[e]+"   ");
                }
            }

            if(!list.isEmpty()){
                for(int c:list){
                    int f = -1;
                    int s = -1;
                    if(helper.flag){
                        f = c * 2 +1;
                        s = c * 2 +2;
                    }else {
                        f = c * 2 +2;
                        s = c * 2 +1;
                    }

                    if(f<array.length){
                        helper.add(f);
                    }
                    if(s<array.length){
                        helper.add(s);
                    }
                }
            }
        }
    }



    /*
      用栈 qiue 模拟
     */
    public static void snakeBFS1(int[] array){
        Stack<Integer> stack = new Stack<>();
        int flag=0;
        stack.push(0);
        while(!stack.isEmpty()){
            List<Integer> list =new ArrayList<>();
            while (!stack.isEmpty()){
                int e = stack.pop();
                System.out.println(array[e] + "  ");
               list.add(e);
            }
            flag ^= 1;

            for(int i=0;i<list.size();i++){
                int c = list.get(i);
                int f = flag>0?c*2+1:c*2+2;
                int s = flag>0?c*2+2:c*2+1;
                if(f<array.length){
                    stack.push(f);
                }
                if(s<array.length){
                    stack.push(s);
                }
            }
        }

    }

    public void snakeBFS2(int[] array){
        int flag = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()){
            List<Integer> list =new ArrayList<>();
            while(!queue.isEmpty()){
                list.add(queue.poll());
            }

            flag ^= 1;
            for(int i=0;i<list.size();i++){
                int c = list.get(i);
                int f = c>0?c*2+2:c*2+1;
                int s = c>0?c*2+1:c*2+2;
                if(f < array.length){
                    queue.add(1);
                }

            }


        }

    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
       // bfs(array);

        int flag = 0;
        flag ^= 1;
        flag ^= 1;

        //System.out.println(flag);

        snakeBFS1(array);
    }
}
