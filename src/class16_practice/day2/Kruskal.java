package class16_practice.day2;

import java.util.*;

public class Kruskal {

    public static class UnionFind{
        public Map<Integer,Integer> father = new HashMap<>();
        public Map<Integer,Integer> size = new HashMap<>();

        public UnionFind(int[] matrix){
            for(int i=0;i<matrix.length;i++){
                 father.put(matrix[i],matrix[i]);
                 size.put(matrix[i],1);
            }
        }

        public int find(int x){
            Stack<Integer> stack = new Stack<>();
            while(x != father.get(x)){
                stack.push(x);
                x= father.get(x);
            }

            while(!stack.isEmpty()){
                father.put(stack.pop(),x);
            }
            return x;
        }

        public void union(int x,int y){

            int f1 = find(x);
            int f2 = find(y);
            if(f1 != f2){
                int s1 = size.get(f1);
                int s2 = size.get(f2);

                if(s1 < s2){
                    father.put(f1,f2);
                    size.put(f2,s1+s2);
                    size.remove(s1);
                }else {
                    father.put(f2,f1);
                    size.put(f1,s1+s2);
                    size.remove(s2);
                }
            }
        }

        public boolean isSameSet(int x,int y){
            return find(x) == find(y);
        }

    }

    public static class UnionFind1{
        public Map<Node,Node> father;
        public Map<Node,Integer> size;

        public UnionFind1(){
            father = new HashMap<>();
            size = new HashMap<>();
        }

        public Node find(Node curNode){
            Stack<Node> stack = new Stack<>();
            while(curNode != father.get(curNode)){
                stack.push(curNode);
                curNode = father.get(curNode);
            }
            while(!stack.isEmpty()){
                father.put(stack.pop(),curNode);
            }
            return curNode;
        }


        public void union(Node n1,Node n2){
            Node f1 = find(n1);
            Node f2 = find(n2);
            if(f1 != f2){
                int s1 = size.get(f1);
                int s2 = size.get(f2);

                if(s1 < s2){
                    father.put(f1,f2);
                    size.put(f2,s1+s2);
                    size.remove(f1);
                }else {
                    father.put(f2,f1);
                    size.put(f1,s1+s2);
                    size.remove(f2);
                }
            }
        }

        public void makeSets(Collection<Node> nodes){
            for(Node node:nodes){
                father.put(node,node);
                size.put(node ,1);
            }
        }

        public boolean  isSameSet(Node n1,Node n2){
            return find(n1) == find(n2);
        }
    }

    public static  class Graph{
        public Map<Integer,Node> nodes = new HashMap<>();
        public Set<Edge> edges = new HashSet<>();
    }

    public static class Node{
        public int in;
        public int out;
        public int from;
        public int to;
        public int value;
        public List<Node> next;
        public List<Edge> edges;

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

    public static Graph generateGraph(int[][] matrix){
        Graph graph = new Graph();
        for(int i=0;i<matrix.length;i++){
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];

            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,new Node(from));
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(from,new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge edge = new Edge(fromNode,toNode,weight);

            fromNode.next.add(toNode);
            fromNode.out++;
            toNode.in++;

            fromNode.edges.add(edge);
            graph.edges.add(edge);

        }

        return graph;
    }

    public Set<Edge> kruskalMST(Graph g){

        UnionFind1 usets = new UnionFind1();
        usets.makeSets(g.nodes.values());

        Set<Edge> edges = g.edges;
        List<Edge> edgeList = new ArrayList<>(edges);
        edgeList.sort((a,b)->a.weight-b.weight);


        Set<Edge> result = new HashSet<>();
        for(Edge e:edgeList){
            if(!usets.isSameSet(e.from,e.to)){
                usets.union(e.from,e.to);
                result.add(e);
            }
        }
        return result;
    }


    public static void main(String[] args) {

    }
}
