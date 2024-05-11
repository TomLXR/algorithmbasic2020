package class20_practice;

public class HorseJump {
	
	// 从0，0 跳到 a,b  有多少中方法
	public static int jump(int a, int b, int k) {
		
		return process(0,0,a,b,k);
	}
	
	public static int process(int x,int y,int a,int b,int rest) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		if(rest == 0) {
			return x == a && y == b?1:0;
		}
		
		int ways = process(x+1,y+2,a,b,rest-1);
		ways += process(x+1,y-2,a,b,rest-1);
		ways += process(x-1,y+2,a,b,rest-1);
		ways += process(x-1,y-2,a,b,rest-1);
		ways += process(x+2,y+1,a,b,rest-1);
		ways += process(x-2,y-1,a,b,rest-1);
		ways += process(x-2,y+1,a,b,rest-1);
		ways += process(x+2,y-1,a,b,rest-1);
		return ways;
	}

	
	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;


		System.out.println(jump(x, y, step));
	}
}
