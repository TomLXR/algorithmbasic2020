package class21_practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import class21.Code04_CoinsWaySameValueSamePapper.Info;

public class CoinsWaySameValueSamePapper {
	public static int coinsWay(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
			//if (!map.containsKey(map.get(arr[i]))) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
		}
		int[] v = new int[map.size()];
		int[] c = new int[map.size()];
		Set<Integer> keySet = map.keySet();
		int i = 0;
		for (int key : keySet) {
			v[i] = key;
			c[i] = map.get(key);
			i++;
		}
//		printArray(v);
//		printArray(c);

		return process(v, c, 0, aim);
	}

	public static int process(int[] m, int[] c, int index, int rest) {
		if (index == m.length) {
			return rest == 0 ? 1 : 0;
		}
		int v = m[index];
		int ways = 0;
		for (int zhang = 0; zhang <= c[index] && rest >= zhang * v; zhang++) {
			ways += process(m, c, index + 1, rest - zhang * v);
		}
		return ways;
	}

	public static int coinsWay3(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
			//if (!map.containsKey(map.get(arr[i]))) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
		}
		

		int N = map.size();
		int[] coins = new int[N];
		int[] zhangs = new int[N];
		Set<Integer> keySet = map.keySet();
		int i = 0;
		for (int key : keySet) {
			coins[i] = key;
			zhangs[i] = map.get(key);
			i++;
		}
		int[][] dp = new int[N+1][aim+1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - coins[index] >= 0) {
					dp[index][rest] += dp[index][rest - coins[index]];
				}
				if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
					dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
				}
			}
		}
		return dp[0][aim];

		
	}
	
	public static int dp2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] zhangs = info.zhangs;
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];
				if (rest - coins[index] >= 0) {
					dp[index][rest] += dp[index][rest - coins[index]];
				}
				if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
					dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
				}
			}
		}
		return dp[0][aim];
	}
	
	public static class Info {
		public int[] coins;
		public int[] zhangs;

		public Info(int[] c, int[] z) {
			coins = c;
			zhangs = z;
		}
	}

	public static Info getInfo(int[] arr) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		for (int value : arr) {
			if (!counts.containsKey(value)) {
				counts.put(value, 1);
			} else {
				counts.put(value, counts.get(value) + 1);
			}
		}
		int N = counts.size();
		int[] coins = new int[N];
		int[] zhangs = new int[N];
		int index = 0;
		for (Entry<Integer, Integer> entry : counts.entrySet()) {
			coins[index] = entry.getKey();
			zhangs[index++] = entry.getValue();
		}
//		printArray(coins);
//		printArray(zhangs);
		return new Info(coins, zhangs);
	}

	public static int coinsWay1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		return process1(info.coins, info.zhangs, 0, aim);
	}

	public static int process1(int[] coins, int[] zhangs, int index, int rest) {
		if (index == coins.length) {
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
			ways += process1(coins, zhangs, index + 1, rest - (zhang * coins[index]));
		}
		return ways;
	}

	// 为了测试
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
		int maxLen = 10;
		int maxValue = 20;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinsWay(arr, aim);
			int ans2 = coinsWay1(arr, aim);
			 int ans3 = coinsWay3(arr, aim);
				if (ans1 != ans2  || ans1 != ans3 ) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				 System.out.println(ans3);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
