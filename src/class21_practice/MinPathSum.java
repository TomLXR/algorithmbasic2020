package class21_practice;

public class MinPathSum {
	
	public static int minPathSum0(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[][] dp = new int[row][col];
		dp[0][0] = m[0][0];
		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + m[i][0];
		}
		for (int j = 1; j < col; j++) {
			dp[0][j] = dp[0][j - 1] + m[0][j];
		}
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
			}
		}
		return dp[row - 1][col - 1];
	}
	
	/*
	 * 只依赖左边和上边的答案 所以可以空间压缩  空间压缩使用R 和 C 里较小的来压缩
	 */
	public static int minPathSum2(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int row = m.length;
		int col = m[0].length;
		int[] dp = new int[col];
		dp[0] = m[0][0];
		for(int i=1;i<col;i++) {
			dp[i] = m[0][i] +dp[i-1];
		}
		
		for(int i=1;i<row;i++) {
			
			dp[0] += m[i][0];
			for(int j=1;j<col;j++) {
				dp[j] = Math.min(dp[j], dp[j-1]) + m[i][j];
			}
		}

		return dp[col - 1];
	}
	
	public static int minPathSum1(int[][] m) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return 0;
		}
		int R = m.length;
		int C = m[0].length;
		int[][] dp = new int[R][C];
		dp[0][0] = m[0][0];
		for(int i=1;i<C;i++) {
			dp[0][i] =m[0][i]+ dp[0][i-1];
		}
		for(int i =1;i<R;i++) {
			dp[i][0] =m[i][0]+ dp[i-1][0];
		}
		
		for(int i=1;i<R;i++) {
			for(int j=1;j<C;j++) {
				dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) +m[i][j];
			}
		}
		return dp[R-1][C-1];
	}
	
	
	public static int[][] getRandomArr(){
		
		int R = (int)(Math.random()*20);
		int C = (int)(Math.random()*20);
		
		int[][] arr = new int[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				arr[i][j] = (int)(Math.random()*30);
			}
		}
		return arr;
	}
	
	public static int[][] copyArr(int[][] a){
		int R = a.length;
		int C = a.length>0?a[0].length:0;
		int[][] arr = new int[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				arr[i][j] = a[i][j];
			}
		}
		return arr;
	}
	
	public static void main(String[] args) {
		
		boolean result = true;
		for(int i=0;i<100;i++) {
			int[][] arr1 = getRandomArr();

			
			int s1 = minPathSum0(arr1);
			int s2 = minPathSum1(arr1);
			int s3 = minPathSum2(arr1);
			if(s1!=s2 || s2 != s3) {
				result = false;
				break;
			}
			
		}
		if(!result) {
			System.out.println("Failed");
		}
	}

}
