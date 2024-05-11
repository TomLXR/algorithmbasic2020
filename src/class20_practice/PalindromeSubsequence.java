package class20_practice;

public class PalindromeSubsequence {

	public static int lpsl1(String s) {
	
		return process(s.toCharArray(),0,s.length()-1);
	}
	
	public static int process(char[] chars,int l,int r) {
		if(l == r ) {
			return 1;
		}
		if(l == r-1 ) {
			return chars[l] == chars[r]?2 :1;
		}
		
		int p1 = process(chars,l+1,r);
		int p2 = process(chars,l,r-1);
		int p3 = (chars[l] == chars[r]) ? 2+ process(chars,l+1,r-1): 0;
		
		
		return Math.max(p1, Math.max(p2, p3));
	}
	
	
	public static  int lpsl2(String s) {
		char[] chars = s.toCharArray();
		int n = chars.length-1;
		int[][] dp = new int[n+1][n+1];
		for(int i=0;i<=n;i++) {
			dp[i][i] = 1;
		}

		for(int i = n-1;i>=0;i--) {
			dp[i][i+1] = chars[i] == chars[i+1] ? 2 :1;
			//dp[i][i + 1] = chars[i] == chars[i + 1] ? 2 : 1;
		}
		


		int c = 2;
		while(c <=n) {
			int row = 0;
			int col = c;
			while(col <= n) {
				
				dp[row][col] = Math.max(dp[row][col - 1], dp[row + 1][col]);
				if (chars[row] == chars[col]) {
					 dp[row][col] = Math.max(dp[row][col], dp[row + 1][col - 1] + 2); 
				} 
				row++;
				col++;
			} 
			c++;
		}
			
        return dp[0][n];
		
		
	}
	
	public static void print(int[][] dp) {
		int n = dp.length-1;
		int c = 2;
		while(c<=n) {
			
			int row = 0;
			int col = c;
			while(col <= n) {
				
				System.out.print(dp[row][col]+" ");
				
				row++;
				col++;
			}
			System.out.println();
			c++;
		}
	}
	
	public static void main(String[] args) {
		
		print(new int[][] {
			{1,2,3,4,5},
			{11,22,33,44,55},
			{111,222,333,444,555},
			{10,20,30,40,50},
			{12,22,32,42,52}
		});
		
		
		System.out.println(lpsl1("1dbcsdsdd123"));
		System.out.println(lpsl2("1dbcsdsdd123"));
		
	}
}
