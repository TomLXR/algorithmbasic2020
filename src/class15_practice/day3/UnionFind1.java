package class15_practice.day3;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UnionFind1<T> {
	
	public static class Node<T>{
		T t;
		public Node(T t) {
			this.t = t;
		}
		public Node() {
		}
	}
	
	public Map<T, Node<T>> nodeMap;
	public Map<Node<T>, Node<T>> parentMap;
	public Map<Node<T>, Integer> sizeMap;

	
	
	public UnionFind1(Collection<T> colletions) {
	    nodeMap = new HashMap<>();
	    parentMap = new HashMap<>();
	    sizeMap = new HashMap<>();
	    for(T t:colletions) {
	    	nodeMap.put(t, new Node<>(t));
	    	parentMap.put(nodeMap.get(t), nodeMap.get(t));
	    	sizeMap.put(nodeMap.get(t), 1);
	    }
	}
	
	public Node<T> findFather(Node<T> n1)
	{
		Stack<Node<T>> stack = new Stack<>();
		while(n1!=parentMap.get(n1)) {
			stack.push(n1);
			n1 = parentMap.get(n1);
		}
		while(!stack.isEmpty()) {
			sizeMap.remove(stack.peek());
			parentMap.put(stack.pop(), n1);
		}
		return n1;
	}

	
	public boolean isSameSet(T n1,T n2) {
		Node<T> node1=nodeMap.get(n1);
		Node<T> node2=nodeMap.get(n2);
		return findFather(node1) == findFather(node2);
	}
	
	public int getSets() {
		return sizeMap.size();
	}
	
	public void union(T n1,T n2) {
		Node<T> node1=nodeMap.get(n1);
		Node<T> node2=nodeMap.get(n2);
		Node<T> f1=findFather(node1);
		Node<T> f2=findFather(node2);
		if(f1 != f2) {
			int s1 = sizeMap.get(f1);
			int s2 = sizeMap.get(f2);
			if(s1 > s2) {
				parentMap.put(f2, f1);
				sizeMap.put(f1, sizeMap.get(f1)+sizeMap.get(f2));
				sizeMap.remove(f2);
			}else {
				parentMap.put(f1, f2);
				sizeMap.put(f2, sizeMap.get(f1)+sizeMap.get(f2));
				sizeMap.remove(f1);
			}
		}
	}
	
	public static void main(String[] args) {
		
		UnionFind1<Integer> union = new UnionFind1<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
		union.union(2, 3);
		union.union(3, 6);
		
		
		union.union(7, 9);
		union.union(7, 8);
		
		union.union(4, 5);
		
		System.out.println(union.isSameSet(4, 3));
		System.out.println(union.isSameSet(1,2));
		
		System.out.println(union.isSameSet(7,9));
		System.out.println(union.isSameSet(2,6));
		System.out.println(union.isSameSet(3,6));
		
		System.out.println(union.isSameSet(3,9));
		System.out.println(union.getSets());
		
	}
}
