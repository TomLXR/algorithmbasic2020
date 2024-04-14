package class16_practice.day2;

import java.util.*;

public class BFS {

    public static class Node{
        public int value;
        public int in;
        public int out;
        public List<Node> nexts;
        public List<Edge> edges;

        public Node(int value){
            this.value = value;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    public static class Edge{
        public Node from;
        public Node to;
        public int weigth;

        public Edge(Node from ,Node to,int weight){
            this.from = from;
            this.to = to;
            this.weigth = weight;
        }
    }

    public static class Graph{
        public Map<Integer,Node> nodes;
        public Set<Edge> edges;

        public Graph(){
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }


    public static Graph generateGraph(int[][] matrix){

        Graph graph = new Graph();
        for(int i=0;i<matrix.length;i++){
            int from = matrix[i][1];
            int to = matrix[i][2];

            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge edge  = new Edge(fromNode,toNode,0);

            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;

            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;

    }

    public static void bfs(Node start){

         if(start == null){
             return ;
         }

         Queue<Node> queue = new LinkedList<>();
         Set<Node> set = new HashSet<>();
         queue.add(start);
         queue.add(start);
        System.out.printf(start.value+" ");
        while(!queue.isEmpty()){
            Node current = queue.poll();
            for(Node next:current.nexts){
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                    System.out.printf(next.value+" ");
                }
            }
        }

    }



    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0,2,3},
                {0,2,1},
                {0,3,4},
                {0,1,6}};

        Graph graph = generateGraph(matrix);

        bfs(graph.nodes.get(2));

    }
}
