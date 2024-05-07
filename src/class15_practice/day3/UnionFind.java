package class15_practice.day3;

import java.util.Stack;

public class UnionFind {
	public static final int nums = 1000000;
	int[] parents =new int[nums];
	int[] size = new int[nums];
	int sets =0;
	
	public UnionFind(int[] members) {
		for(int i=0;i<members.length;i++) {
			parents[members[i]] =members[i];
			size[members[i]] =1;
			sets++;
		}
	}
	
	public boolean isSameSets(int a,int b) {
		if(findFather(a) == findFather(b)) {
			return true;
		}
		return false;
	}
	
	public int findFather(int i) {
		Stack<Integer> stack  = new Stack<>();
		while(parents[i]!=i) {
			stack.push(i);
			i = parents[i];
		}
		while(!stack.isEmpty()) {
			parents[stack.pop()]=i;
		}
		return i;
	}
	
	public void union(int a,int b) {
		int father1 = findFather(a);
		int father2 = findFather(b);
		if(father1!=father2) {
			int big = size[father1]>size[father2]?father1:father2;
            int small = big == father1?father2:father1;
            parents[small] = big;
            size[big] = size[big] +size[small];
            size[small] = 0;
            sets--;
		}
	}
	
	public int sets() {
		return sets;
	}
	
	
	public static void main(String[] args) {
		
		UnionFind union = new UnionFind(new int[] {1,2,3,4,5,6,7,8,9});
		union.union(2, 3);
		union.union(3, 6);
		
		
		union.union(7, 9);
		union.union(7, 8);
		
		union.union(4, 5);
		
		System.out.println(union.isSameSets(4, 3));
		System.out.println(union.isSameSets(1,2));
		
		System.out.println(union.isSameSets(7,9));
		System.out.println(union.isSameSets(2,6));
		System.out.println(union.isSameSets(3,6));
		
		System.out.println(union.isSameSets(3,9));
		System.out.println(union.sets());
		
	}

}
