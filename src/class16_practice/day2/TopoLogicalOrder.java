package class16_practice.day2;

import java.util.*;

public class TopoLogicalOrder {

    public static class Node{
        public int value;
        public int in;
        public int out;
        public List<Node> nexts;
        public Set<Edge> edges;

        public Node(int value){
            this.value = value;
            nexts = new ArrayList<>();
            edges = new HashSet<>();
        }

    }

    public static class Edge{
        public Node from;
        public Node to;
        public int weight;

        public Edge(Node from,Node to,int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static class Graph{
        public List<Edge> edges;
        public Map<Integer,Node> nodes;

        public Graph(){
            this.edges = new ArrayList<>();
            this.nodes = new HashMap<>();
        }

    }



    public static Graph generateGraph(int[][] matrix){
        Graph g =new Graph();
        for(int i=0;i<matrix.length;i++){
            int from = matrix[i][1];
            int to = matrix[i][2];


            if(!g.nodes.containsKey(from)){
                g.nodes.put(from,new Node(from));
            }

            if(!g.nodes.containsKey(to)){
                g.nodes.put(to,new Node(to));
            }

            Node fromNode = g.nodes.get(from);
            Node toNode = g.nodes.get(to);

            Edge edge =new Edge(fromNode,toNode,0);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;

            fromNode.edges.add(edge);
            g.edges.add(edge);
        }

        return g;
    }


    public static List<Node> topoSort(Graph g){
        Map<Node,Integer> inMap =new HashMap<>();
        List<Node> nodes = new ArrayList<>(g.nodes.values());
        List<Node> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        for(Node n:nodes){
            inMap.put(n,n.in);
            if(n.in == 0){
                queue.add(n);
                result.add(n);
            }
        }

        while(!queue.isEmpty()){
            Node node = queue.poll();
            for(Node next:node.nexts){
                inMap.put(next,inMap.get(next)-1);
                if(inMap.get(next) ==0){
                    queue.add(next);
                    result.add(next);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
