package class23_practice;

public class SplitSumClosed {
	
	public static int right(int[] arr) {
		if(arr == null || arr.length < 2) {
			return 0;
		}
		Record r= process(arr,0,0,0);
		return Math.min(r.a,r.b);
	}
	
	public static Record process(int[] arr,int index,int a,int b) {
		if(index == arr.length) {
			return new Record(a,b,Math.abs(a-b));
		}
		Record r1 = process(arr,index+1,arr[index]+a,b);
		Record r2 = process(arr,index+1,a,arr[index]+b);
		return r1.diff>r2.diff?r2:r1;
	}
	
	public static int dp1(int[] arr) {
		int N = arr.length;
		int sum = 0;
		for(int i:arr) {
			sum +=  i;
		}
		Record[][][] dp  =new Record[N+1][sum][sum];
		for(int a =0;a<sum;a++ ) {
			for(int b =0;b<sum;b++ ) {
				dp[N][a][b] =  new Record(a,b,Math.abs(a-b));
			}	
		}
		
		for(int index = N-1;index>=0;index--) {
			for(int a =0;a<sum;a++ ) {
				for(int b =0;b<sum;b++ ) {
					Record r1 = null ;
					if(arr[index]+a< sum) {
						r1 = dp[index+1][arr[index]+a][b];
					}
					
					Record r2 =null;
					if(arr[index]+b< sum) {
						r2 = dp[index+1][a][arr[index]+b];
					}

					dp[index][a][b] = (r1!= null && r2!= null)? (r1.diff>r2.diff?r2:r1):null;
				}	
			}
		}
		return Math.min(dp[0][0][0].a, dp[0][0][0].b);
		
	}
	
	public static class Record{
		public int a;
		public int b;
		public int diff;
		
		public Record() {}
		public Record(int a,int b,int diff) {
			this.a = a;
			this.b = b;
			this.diff = diff;
		}
	}
	
	public static int right1(int[] arr) {
		if(arr == null || arr.length < 2) {
			return 0;
		}
		int sum =0;
		for(int i:arr) {
			sum += i;
		}
		return process1(arr,0,sum>>1);
	}
	
	public static int process1(int[] arr,int index,int rest) {
		if(index == arr.length) {
			return 0;
		}

		int p1 = process1(arr,index+1,rest);
		int p2 = 0;
		if(rest>=arr[index]) {
			p2 = arr[index] +process1(arr,index+1,rest-arr[index]);
		}
		return Math.max(p1, p2);
	}
	public static int[] randomArray(int len, int value) {
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * value);
		}
		return arr;
	}

	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 50;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * maxLen);
			int[] arr = randomArray(len, maxValue);
			int ans1 = right(arr);
			int ans2 = right1(arr);
			int ans3 = dp1(arr);
			
			
			if (ans1 != ans2 || ans3 != ans2) {
				printArray(arr);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
