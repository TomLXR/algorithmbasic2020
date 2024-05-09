package class17_practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintAllSubsquences {
	
	public static List<String> subStr(String str){
		
		List<String> ans = new ArrayList<>();
		char[] chars = str.toCharArray();
		f(chars,"",0,ans);
		return ans;
	}
	
	
	public static void f(char[] chars,String path,int index,List<String> ans) {
		if(index == chars.length) {
			ans.add(path);
			return;
		}
		
		f(chars,path,index+1,ans);
		f(chars,path+chars[index],index+1,ans);
	}
	
	public static Set<String> subStr1(String str){
		
		Set<String> ans = new HashSet<>();
		char[] chars = str.toCharArray();
		f1(chars,"",0,ans);
		return ans;
	}
	
	
	public static void f1(char[] chars,String path,int index,Set<String> ans) {
		if(index == chars.length) {
			ans.add(path);
			return;
		}
		
		f1(chars,path,index+1,ans);
		f1(chars,path+chars[index],index+1,ans);
		
	}
	
	public static void main(String[] args) {
		
		List<String> list = subStr("abcc");
		for(String s:list) {
			System.out.println(s);
		}
		System.out.println("================");
		
		Set<String> set = subStr1("abcc");
		for(String s:set) {
			System.out.println(s);
		}
	}

}
