package class20_practice;

import java.util.Arrays;
import java.util.PriorityQueue;

//题目
//数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
//现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
//认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
//每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
//洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
//四个参数：arr, n, a, b
//假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
public class Coffee {

	public static int right(int[] arr, int n, int a, int b) {
		int[] times = new int[arr.length];
		int[] drink = new int[n];
		return forceMake(arr, drink, n, a, b);
	}
	
	public static class Machine{
		public int workTime;
		public int timePoint;
		public Machine(int time,int f) {
			workTime = time;
			timePoint = f;
		}
	}
	
	public static int forceMake(int[] arr,int[] drink,int n,int a,int b){
		PriorityQueue<Machine> pq = new PriorityQueue<>((c,d) ->(c.workTime+c.timePoint) - (d.workTime+d.timePoint));
		for(int i=0;i<arr.length;i++) {
			pq.add(new Machine(arr[i],0));
		}
		
		for(int i=0;i<n;i++) {
			Machine r=pq.poll();
			drink[i] = r.timePoint+r.workTime;
			r.timePoint = r.timePoint+r.workTime;
			pq.add(r);
		}
		
        
		return process(drink,0,0,a,b);
	}
	
	public static int process(int[] drink,int index,int free,int a,int b) {
		if(index == drink.length) {
			return 0;
		}
		
		int curFree1 = Math.max(drink[index],free)+a;
		int p1 = process(drink,index+1,curFree1,a,b);
		p1 = Math.max(p1, curFree1);
		
		int curFree2 = drink[index]+b;
		int p2 = process(drink,index+1,free,a,b);
		p2 = Math.max(p2, curFree2);
		
		
		return Math.min(p1, p2);
	}
	
	public static void main(String[] args) {
		int len = 10;
		int max = 10;
		int testTime = 10;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(len, max);
			int n = (int) (Math.random() * 7) + 1;
			int a = (int) (Math.random() * 7) + 1;
			int b = (int) (Math.random() * 10) + 1;
			int ans1 = right(arr, n, a, b);
			int ans2 = right1(arr, n, a, b);
			
			if (ans1 != ans2 ) {
				printArray(arr);
				System.out.println("n : " + n);
				System.out.println("a : " + a);
				System.out.println("b : " + b);
				System.out.println(ans1 + " , " + ans2 );
				System.out.println("===============");
				break;
			}
		}
		System.out.println("测试结束");

	}
	
	public static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		return arr;
	}
	
	// for test
	public static void printArray(int[] arr) {
		System.out.print("arr : ");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + ", ");
		}
		System.out.println();
	}
	
	

	// 验证的方法
	// 彻底的暴力
	// 很慢但是绝对正确
	public static int right1(int[] arr, int n, int a, int b) {
		int[] times = new int[arr.length];
		int[] drink = new int[n];
		return forceMake1(arr, times, 0, drink, n, a, b);
	}

	public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
		if (index == drinks.length) {
			return time;
		}
		// 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
		int wash = Math.max(drinks[index], washLine) + a;
		int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

		// 选择二：当前index号咖啡杯，选择自然挥发
		int dry = drinks[index] + b;
		int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
		return Math.min(ans1, ans2);
	}
	// 每个人暴力尝试用每一个咖啡机给自己做咖啡
	public static int forceMake1(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
		if (kth == n) {
			int[] drinkSorted = Arrays.copyOf(drink, kth);
			Arrays.sort(drinkSorted);
			return forceWash(drinkSorted, a, b, 0, 0, 0);
		}
		int time = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int work = arr[i];
			int pre = times[i];
			drink[kth] = pre + work;
			times[i] = pre + work;
			time = Math.min(time, forceMake1(arr, times, kth + 1, drink, n, a, b));
			drink[kth] = 0;
			times[i] = pre;
		}
		return time;
	}
}
