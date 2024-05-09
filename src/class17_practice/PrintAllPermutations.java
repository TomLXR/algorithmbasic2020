package class17_practice;

import java.util.ArrayList;
import java.util.List;

public class PrintAllPermutations {

	
	public static List<String> permutations(String str){
		List<String> ans = new ArrayList<>();
		char[] chars=str.toCharArray();
		f(chars,0,ans);
		return ans;
	}
	
	public static void f(char[] chars,int index,List<String> ans) {
		if(index == chars.length) {
			ans.add(String.valueOf(chars));
			return;
		}
		
		for(int i=index;i<chars.length;i++) {
			swap(chars,index,i);
			f(chars,index+1,ans);
			swap(chars,index,i);
		}
	}
	
	public static void swap(char[] chars,int i,int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
	
	
	
	public static List<String> permutations1(String str){
		List<String> ans = new ArrayList<>();
		char[] chars=str.toCharArray();
		f1(chars,0,ans);
		return ans;
	}
	
	public static void f1(char[] chars,int index,List<String> ans) {
		if(index == chars.length) {
			ans.add(String.valueOf(chars));
			return ;
		}
		boolean[] visited = new boolean[256];
		
		for(int i=index;i<chars.length;i++) {
			if(!visited[chars[i]] ) {
				visited[chars[i]] = true;
				swap(chars,i,index);
				f1(chars,index+1,ans);
				swap(chars,i,index);
			}
		}
	}
	
	/*
	 * permutation 貌似就是排列组合
	 */
	public static void main(String[] args) {
		List<String> ans = permutations("acc");
		for(String s:ans) {
			System.out.println(s);
		}
		
		System.out.println("==============");
		
		List<String> ans1 = permutations1("acc");
		for(String s:ans1) {
			System.out.println(s);
		}
	}
}
