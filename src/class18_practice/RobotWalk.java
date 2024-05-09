package class18_practice;

public class RobotWalk {
	
	public static int walk(int M,int P,int K,int N) {
		if(K == 0) {
			return M==P?1:0;
		}
		
		if(M ==1) {
			return walk(2,P,K-1,N);
		}
		if(M == N) {
			return walk(N-1,P,K-1,N);
		}
		
		return walk(M-1,P,K-1,N) + walk(M+1,P,K-1,N);
	}
	
	
	/*
	 * 0列是base case
	 */
	public static int walk1(int cur,int aim,int rest,int n) {
		int[][] dp =new int[n+1][rest+1];
		for(int i=1;i<=n;i++) {
			for(int j=0;j<=rest;j++) {
				dp[i][j] = -1;
			}
		}
		return process1(cur,aim,rest,n,dp);
	}
	
	public static int process1(int cur,int aim,int rest,int n,int[][] dp) {
		if(dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		
		int ans;
		if(rest == 0) {
			ans=(cur == aim )?1:0;
		}
		else if(cur == 1) {
			ans = process1(2,aim,rest-1,n,dp);
		}else if(cur == n) {
			ans = process1(n-1,aim,rest-1,n,dp);
		}else {
			ans = process1(cur-1,aim,rest-1,n,dp) + process1(cur+1,aim,rest-1,n,dp);
		}
		dp[cur][rest] = ans;
		return ans;
	}
	
	
	public static int walk2(int cur,int aim,int rest,int n) {
		int[][]dp = new int[n+1][rest+1];
		dp[cur][0] = 1;
		
		for(int i = 1;i<=rest ;i++) {
			dp[1][i] = dp[2][i-1];
			for(int j=2;j<n;j++) {
				dp[j][i] = dp[j+1][i-1] +dp[j-1][i-1];
			}
			dp[n][i] = dp[n-1][i-1];
			
		}
		
		return dp[cur][rest];
	}
	
	public static int walk3(int cur,int aim,int rest,int n) {
		int[]dp = new int[n+1];
		dp[cur] = 1;
		
		int[]help = new int[n+1];

		
		for(int col = 1;col<= rest;col++) {
			help[1] = dp[2];
			for(int row = 2;row<n;row++) {
				help[row] = dp[row-1] +dp[row+1];
			}
			help[n] = dp[n-1];
			for(int i=0;i<dp.length;i++) {
				dp[i] = help[i];
			}
		}
		return dp[aim];
		
	}
	
	public static void main(String[] args) {
		System.out.println(walk(2,4,6,10));	
		System.out.println(walk1(2,4,6,10));
		System.out.println(walk2(2,4,6,10));
		System.out.print(walk3(2,4,6,10));
	}

}
