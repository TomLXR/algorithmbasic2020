package class16_practice.day3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import class16.Edge;
import class16.Graph;
import class16.Node;
import class16.Code05_Prim.EdgeComparator;


public class Palgo {
	public static class Node{
		public int in;
		public int out;
		public int value;
		public List<Edge> edges = new ArrayList<>();
		public List<Node> nexts = new ArrayList<>();
		public Node() {
		}
		public Node(int v) {
			value = v;
		}
		@Override
		public String toString() {
			return value+"" ;
		}
		
	}

	public static class Edge {
		public Node from;
		public Node to;
		public int weight;
		public Edge(Node from, Node to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public Edge() {}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + "]";
		}
		 

	}
	
	public static class Graph{
		public Map<Integer,Node> nodes= new HashMap<>();
		public List<Edge> edges= new ArrayList<>();
	}
	
	
	public static class PQ{
		public Edge[] nodes;
		public Comparator<Edge> comp;
		public int heapSize;
		
		public PQ(int limit,Comparator<Edge> c) {
			nodes = new Edge[limit];
			comp = c;
		}
		
		public void heapInsert(int index) {
			while(comp.compare(nodes[index], nodes[(index-1)/2]) <0) {
				swap(index,(index-1)/2);
				index = (index-1)/2;
			}
		}
		
		public void heapify(int index) {
			int left = index*2+1;
			while(left<heapSize) {
				int best = left+1 < heapSize && comp.compare(nodes[left+1], nodes[left])<0?left+1:left;
				best = comp.compare(nodes[best], nodes[index])<0?best:index;
				if(best == index) {
					break;
				}
				swap(best,index);
				index = best;
				left = index*2+1;
			}
		}
		
		public void swap(int i,int j) {
			Edge temp = nodes[i];
			nodes[i] = nodes[j];
			nodes[j] = temp;
		}
		
		public void push(Edge node) {
			nodes[heapSize++] = node;
			heapInsert(heapSize-1);
		}
		
		public Edge pop() {
			Edge ans = nodes[0];
			swap(0,--heapSize);
			heapify(0);
			return ans;
		}
		
		public boolean isEmpty() {
			return heapSize == 0;
		}
	}
	
	
	public static List<Edge> prim(Node node){
		List<Edge> ans = new ArrayList<>();
		PQ pq = new PQ(1000,(a,b)->a.weight - b.weight);
		Set<Node> set = new HashSet<>();
		set.add(node);
		for(Edge e:node.edges) {
			pq.push(e);
		}
		
		while(!pq.isEmpty()) {
			Edge cur= pq.pop();
			if(!set.contains(cur.to)) {
				ans.add(cur);
				set.add(cur.to);
				for(Edge e:cur.to.edges) {
					pq.push(e);
				}
			}
		}
		return ans;
		
	}
	
	
//
//	public static Set<Edge> primMST(Graph graph) {
//		// 解锁的边进入小根堆
//		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
//
//		// 哪些点被解锁出来了
//		HashSet<Node> nodeSet = new HashSet<>();
//		
//		
//		
//		Set<Edge> result = new HashSet<>(); // 依次挑选的的边在result里
//
//		for (Node node : graph.nodes.values()) { // 随便挑了一个点
//			// node 是开始点
//			if (!nodeSet.contains(node)) {
//				nodeSet.add(node);
//				for (Edge edge : node.edges) { // 由一个点，解锁所有相连的边
//					priorityQueue.add(edge);
//				}
//				while (!priorityQueue.isEmpty()) {
//					Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
//					Node toNode = edge.to; // 可能的一个新的点
//					if (!nodeSet.contains(toNode)) { // 不含有的时候，就是新的点
//						nodeSet.add(toNode);
//						result.add(edge);
//						for (Edge nextEdge : toNode.edges) {
//							priorityQueue.add(nextEdge);
//						}
//					}
//				}
//			}
//			// break;
//		}
//		return result;
//	}
	

	public static Graph adapter(int[][] matrix) {
		
		Graph graph = new Graph();
		for(int i=0;i<matrix.length;i++) {
			int weight = matrix[i][0];
			int f = matrix[i][1];
			int t = matrix[i][2];

			if(!graph.nodes.containsKey(f)) {
				graph.nodes.put(f, new Node(f));
			}
			
			if(!graph.nodes.containsKey(t)) {
				graph.nodes.put(t, new Node(t));
			}
			
			Node from = graph.nodes.get(f);
			Node to = graph.nodes.get(t);
			Edge e = new Edge(from,to,weight);
			
			from.out++;
			from.edges.add(e);
			from.nexts.add(to);
			to.in++;
			graph.edges.add(e);
		}
		return graph;
		
		
	}
	
	public static void main(String[] args) {
		
		Graph g = adapter(new int[][] {
			{7,1,3},
			{1,3,9},
			{1,7,3},
			{5,1,4},
			{3,4,6},
			{1,6,7},
			{1,4,5},
			{10,5,8},
			{1,6,8}
		});
		for(Node node:g.nodes.values()) {
			List<Edge> list=prim(node);
			for(Edge e:list) {
				System.out.println(e);
			}
			break;
		}
		
	}

}
