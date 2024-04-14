package class16_practice.day2;

import java.util.*;

import static class16_practice.day2.GraphGenerator.*;



public class Prim {


    public static List<Edge> primMST(Graph graph){

        Set<Node> selectedNodes = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((a,b)->a.weight-b.weight);
        List<Edge> result = new ArrayList<>();
        for(Node n:graph.nodes.values()){

            selectedNodes.add(n);
            for(Edge e:n.edges){
                priorityQueue.add(e);
            }

            while(!priorityQueue.isEmpty()){
                Edge e = priorityQueue.poll();
                Node toNode = e.to;
                if(!selectedNodes.contains(toNode)){
                    selectedNodes.add(toNode);
                    result.add(e);
                    for(Edge e1:toNode.edges){
                        priorityQueue.add(e1);
                    }
                }
            }
            // break; 防森林
        }
        return result;
    }

}
