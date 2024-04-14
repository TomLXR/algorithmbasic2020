package class16_practice.day2;

import java.util.*;

public class GraphGenerator {

    public static class Graph{
        public Map<Integer,Node> nodes = new HashMap<>();
        public Set<Edge> edges = new HashSet<>();
    }

    public static class Node{
        public int value ;
        public int in;
        public int out;
        public List<Node> nexts = new ArrayList<>();
        public List<Edge> edges =new ArrayList<>();

        public Node(int value){
            this.value = value;
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

    public static Graph generateGraph(int[][] gMatrix){

        Graph graph = new Graph();
        for(int i=0;i<gMatrix.length;i++){
            int weight = gMatrix[i][0];
            int from = gMatrix[i][1];
            int to = gMatrix[i][2];

            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }

            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(fromNode,toNode,weight);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;

            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;

    }

}
