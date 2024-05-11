package class19_practice;

public class LongestCommonSubsequence {
	public static int longestCommonSubsequence1(String s1, String s2) {
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		
		return process(arr1,arr2,s1.length()-1,s2.length()-1);
	}
	
	public static int process(char[] s1,char[] s2,int index1,int index2) {
		if(index1 == 0 && index2 == 0) {
			return s1[index1] == s2[index2] ?1 :0;  
		}else if(index1 == 0) {
			return process(s1,s2,0,--index2);
		}else if(index2 == 0) {
			return process(s1,s2,--index1,0);
		}else {
			int p1 = process(s1,s2,index1-1,index2);
			int p2 = process(s1,s2,index1,index2-1);
			
			int p3 = (s1[index1] == s2[index2])? 1+process(s1,s2,index1-1,index2-1):0;
			
			return Math.max(p1, Math.max(p2, p3));
		}
		
	}
	
	public static int longestCommonSubsequence2(String s1, String s2) {
		int r = s1.length();
		int c = s2.length();
		char[] a1 = s1.toCharArray();
		char[] a2 = s2.toCharArray();
		
		int[][] dp = new int[r][c];
		dp[0][0] = a1[0] == a2[0]?1:0;
		for(int i=1;i<c;i++) {
			dp[0][i] = dp[0][i-1];
		}
		for(int i=1;i<r;i++) {
			dp[i][0] = dp[i-1][0];
		}
		
		for(int i=1;i<r;i++){
			for(int j=1;j<c;j++){
				dp[i][j] = Math.max((a1[i]==a2[j])?1+dp[i-1][j-1]:0, Math.max(dp[i-1][j],dp[i][j-1]));
			}	
		}
		
		return dp[r-1][c-1];
	}
	
	public static void main(String[] args) {
		System.out.println(longestCommonSubsequence1("1adfe","2ad,ddde"));
		System.out.println(longestCommonSubsequence2("1adfe","2ad,ddde"));
	}

}
