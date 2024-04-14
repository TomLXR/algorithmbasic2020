package class16_practice.day2;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static class16_practice.day2.GraphGenerator.*;

public class DFS {


    public static void dfs(Node node){
        if(node == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        print(node);

        while(!stack.isEmpty()){
            Node n  = stack.pop();
            for(Node o:n.nexts){
                if(!set.contains(o)){
                    print(o);
                    set.add(o);
                    stack.push(n);
                    stack.push(o);
                    break;
                }
            }
        }
    }

    public static void print(Node node){
        System.out.printf(node.value+" ");
    }



    public static void main(String[] args) {


         int[][] gMatrix = new int[][]{
             {1, 2, 1},
             {2, 2, 3},
             {3, 3, 1},
             {4, 3, 4}
         };
         Graph graph = generateGraph(gMatrix);

         graph.nodes.forEach((key,value)->{
             dfs(value);
             System.out.println("");
         });



    }
}
