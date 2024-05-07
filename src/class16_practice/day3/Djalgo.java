package class16_practice.day3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Djalgo {
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
	
	
	public Map<Node,Integer> d(Node node){
		Map<Node,Integer> table = new HashMap<>();
		table.put(node, 0);
		
		Set<Node> set = new HashSet<>();
		
		Node n= getNode(table,set);
		while(n != null) {
			for(Edge e:n.edges) {
				int cur =table.get(n);
				if(!table.containsKey(e.to)) {
					table.put(e.to, cur+e.weight);
				}else {
					table.put(e.to, Math.min(table.get(e.to), cur+e.weight));
				}
			}
			n= getNode(table,set);
		}
		
		return table;
	}
	
	
	public Node  getNode(Map<Node,Integer> table,Set<Node> set) {
		int m = Integer.MAX_VALUE;
		Node ans = null;
		Iterator<Entry<Node, Integer>> iterator = table.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Entry<Node, Integer> next = iterator.next();
			Node node = next.getKey();
			int w = next.getValue();
			if(w<m && !set.contains(node)) {
				ans = node;
				m = w;
			}
		}
		if(ans != null) set.add(ans);
		
		return ans;
		
	}
	
	
	public static void main(String[] args) {
		
		
	}

}
