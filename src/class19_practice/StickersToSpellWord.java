package class19_practice;

import java.util.HashMap;
import java.util.Map;

/*
 * 首先应该分析有无重复案例，只有重复案例并且可以复用结构 才可以动态规划
 */
public class StickersToSpellWord {

	public static int minStickers(String[] tickers, String target) {
		int ans = process(tickers,target);
		if(ans == Integer.MAX_VALUE) {
			return -1;
		}
		return ans;
		
    }

	/*
	 * 字符串相等使用equal 或 比较length  length的算法复杂度稳定，尽量用length比较字符串是否相等 
	 */
	public static int process(String[] tickers, String rest) {
		if(rest.length() == 0) {
			return 0;
		}
		int ans = Integer.MAX_VALUE;
		for(String str:tickers) {
			String cur = minus(rest,str);
			if(cur.length() != rest.length()) {//同理字符串比较 小心比较错误
				ans = Math.min(ans, process(tickers,cur)) ;
			}
		}
		
		return ans + (ans == Integer.MAX_VALUE ? 0 : 1);
	}
	
	public static String minus(String target,String str) {
		int[] arr = new int[26];
		char[]  chars1= target.toCharArray();
		char[]  chars2= str.toCharArray();
		for(int i=0;i<chars1.length;i++) {
			arr[chars1[i]-'a']++;
		}
		for(int i=0;i<chars2.length;i++) {
			arr[chars2[i]-'a']--;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>0) {
				while(arr[i]>0) {
					sb.append((char)(i+'a'));
					arr[i]--;
				}
			}
		}
		return sb.toString();
	}
	
	
	
	

	public static int minStickers1(String[] stickers, String target) {
		int ans = process1(stickers, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// 所有贴纸stickers，每一种贴纸都有无穷张
	// target
	// 最少张数
	public static int process1(String[] stickers, String target) {
		if (target.length() == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (String first : stickers) {
			String rest = minus1(target, first);
			if (rest.length() != target.length()) {
				min = Math.min(min, process1(stickers, rest));
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	public static String minus1(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] count = new int[26];
		for (char cha : str1) {
			count[cha - 'a']++;
		}
		for (char cha : str2) {
			count[cha - 'a']--;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				for (int j = 0; j < count[i]; j++) {
					builder.append((char) (i + 'a'));
				}
			}
		}
		return builder.toString();
	}
	
	public static int minStickers2(String[] tickers, String target) {
		Map<String,Integer> dpMap = new HashMap<>();
		dpMap.put("", 0);
		int count=process2(tickers,target,dpMap);
		if(count == Integer.MAX_VALUE) {
			return -1;
		}
		return count;
	}
	
	public static int process2(String[] tickers, String rest,Map<String,Integer> dpMap) {
		if(dpMap.containsKey(rest)){
			return dpMap.get(rest);
		}
		int ans = Integer.MAX_VALUE;
		for(String t:tickers) {
			if(rest.charAt(0) == t.charAt(0)) {  //减枝
				String cur = minus(rest,t);
				if(cur.length()!= rest.length()) {
					ans = Math.min(ans, process2(tickers, cur, dpMap));
				}
			}
		}
		
		return (ans ==Integer.MAX_VALUE)?Integer.MAX_VALUE:ans+1;
	}
	
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer();
		
		System.out.println(s.toString() == "");
		System.out.println(minStickers(new String[]{"abc","kk","zm"},"ak"));
		System.out.println(minStickers1(new String[]{"abc","kk","zm"},"ak"));
		System.out.println(minStickers2(new String[]{"abc","kk","zm"},"ak"));
		

	}
}
