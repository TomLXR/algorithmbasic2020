package class19_practice;

public class Knapsack {
	
	public static int maxValue(int[] w, int[] v, int bag) {
		return process(0,bag,w,v);
		
	}
	
	public static int process(int index,int rest,int[] w,int[] v) {
		if(rest < 0) {
			return -1;
		}
		if(index == w.length) {
			return 0;
		}

		int v1 = process(index+1,rest,w,v);
		int v2 = process(index+1,rest-w[index],w,v);
		if(v2 != -1) {
			v2 = v[index] + v2;
		}

		return Math.max(v1, v2);
	}
	
	
	public static int maxValue1(int[] w, int[] v, int bag){
		int[][] dp = new int[w.length+1][bag+1];
		for(int i = w.length-1;i>=0;i--) {
			for(int j=0;j<=bag;j++) {
				int v1 = dp[i+1][j];
				int v2 = -1;
				if((j-w[i]) >= 0) {
					v2 =  v[i] +dp[i+1][j-w[i]];
				}
				dp[i][j] = Math.max(v1, v2); 
			}
		}
		return dp[0][bag];
	}
	
	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
		int[] values = { 5, 6, 3, 19, 12, 4, 2 };
		int bag = 15;

		System.out.println(maxValue(weights,values,bag));
		System.out.println(maxValue1(weights,values,bag));
		
		
	}

}
