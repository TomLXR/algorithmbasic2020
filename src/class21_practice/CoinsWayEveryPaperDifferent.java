package class21_practice;

public class CoinsWayEveryPaperDifferent {
	public static int coinWays(int[] arr, int aim) {
		return process(arr,0,aim);
	}
	
	public static int process(int[] arr,int index,int rest) {
		if(index == arr.length) {
			return rest == 0?1:0;
		}
		int ways = process(arr,index+1,rest);
		if(rest - arr[index] >=0) {
			return ways += process(arr,index+1,rest-arr[index]);
		}
		return ways;
	}

	
	public static int coinWays2(int[] arr,int aim) {
		System.out.println("coinWays2========= ");
		int N = arr.length;
		int[][] dp = new int[N+1][aim+1];
		dp[N][0] = 1;
		for(int i=N-1;i>=0;i--) {
			for(int j=0;j<=aim;j++) {
				int ways = dp[i+1][j];
				if(j - arr[i] >=0) {
					ways += dp[i+1][j - arr[i]];
				}
				dp[i][j] = ways;
			}
			for(int m=0;m<=aim;m++) {
				System.out.print(dp[i][m]+" ");
			}
			System.out.println();
			
		}
		return dp[0][aim];
	}
	
	/*
	 * 不能压缩具体例子再想想
	 */
	public static int coinWays3(int[] arr,int aim) {
		System.out.println("coinWays3========= ");
		int N = arr.length;
		int[] dp =new int[aim+1];
		dp[0] = 1;
		for(int i=N-1;i>=0;i--) {
			for(int j=0;j<=aim;j++) {
				if(j - arr[i] >=0) {
					dp[j] += dp[j-arr[i]];
				}
			}
			for(int m=0;m<=aim;m++) {
				System.out.print(dp[m]+" ");
			}
			System.out.println();
		}
		return dp[aim];
	}
	
	public static int coinWays1(int[] arr, int aim) {
		return process1(arr, 0, aim);
	}

	// arr[index....] 组成正好rest这么多的钱，有几种方法
	public static int process1(int[] arr, int index, int rest) {
		if (rest < 0) {
			return 0;
		}
		if (index == arr.length) { // 没钱了！
			return rest == 0 ? 1 : 0;
		} else {
			return process(arr, index + 1, rest) + process(arr, index + 1, rest - arr[index]);
		}
	}
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * maxValue) + 1;
		}
		return arr;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {

		System.out.println(coinWays3(new int[] {2 ,1, 2 },3));
		System.out.println(coinWays2(new int[] {2 ,1, 2 },3));
		
		int maxLen = 5;
		int maxValue = 5;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinWays(arr, aim);
			int ans2 = coinWays1(arr, aim);
			int ans3 = coinWays2(arr, aim);
			int ans4 = coinWays3(arr,aim);
			
			if (ans1 != ans2 || ans3 != ans2 || ans4 != ans2) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				break;
			}
		}
		System.out.println("测试结束");
	}
	
}
