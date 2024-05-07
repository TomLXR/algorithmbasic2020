package class16_practice.day3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Kalgo {
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
	
	
	public static class UnionSet<T>{
		public Map<T,T> parent;
		public Map<T,Integer> size;
		public int sets;
		
		
		public UnionSet() {
			parent = new HashMap<>();
			size = new HashMap<>();
			sets = 0;
		}
		
		public void makeSets(Collection<T> c) {
			parent.clear();
			size.clear();
			sets = c.size();
			for(T t:c) {
				parent.put(t, t);
				size.put(t, 1);
			}
		}
		
		public T findFather(T t) {
			Stack<T> stack = new Stack<>();
			while(parent.get(t) != t) {
				stack.add(t);
				t = parent.get(t);
			}
			while(!stack.isEmpty()) {
				size.remove(stack.peek());
				parent.put(stack.pop(), t);
			}
			return t;
		}
		
		public void union(T t1,T t2) {
			T f1=findFather(t1);
			T f2=findFather(t2);
			if(f1 != f2) {
				int s1 = size.get(f1);
				int s2 = size.get(f2);
				if(s1 > s2) {
					parent.put(f2, f1);
					size.put(f1, size.get(f1)+size.get(f2));
					size.remove(f2);
				}else {
					parent.put(f1, f2);
					size.put(f2, size.get(f1)+size.get(f2));
					size.remove(f1);
				}
				sets--;
			}
		}
		
		public boolean isSameSet(T t1,T t2) {
			return findFather(t1) == findFather(t2);
		}
		
		
	}
	
	/*
	 * 根据权重把边排序 从小到大 看会不会形成环路，不会就要，形成环路边放弃 
	 */
	public static List<Edge> KMST(Graph g){
		List<Edge> edges = g.edges;
		List<Edge> ans = new ArrayList<>();
		Collections.sort(edges,(a,b)->a.weight-b.weight);
		UnionSet<Node> union = new UnionSet<>();
		union.makeSets(g.nodes.values());
		for(Edge e:edges) {
			if(!union.isSameSet(e.from, e.to)) {
				union.union(e.from, e.to);
				ans.add(e);
			}
		}
		return ans;
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
		List<Edge> list=KMST(g);
		for(Edge e:list) {
			System.out.println(e);
		}
		
	}

}
