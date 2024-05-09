package class17_practice;

public class Hanoi {

	/*
	 * 直接上最简答的写法
	 */
	public static void han(int n) {
		f(n,"左","右","中");
	}
	
	public static void f(int n,String from,String to,String other) {
		if(n == 1) {
			System.out.println(String.format("move %d from:%s to:%s",1,from,to ));
			return;
		}
		f(n-1,from,other,to);
		System.out.println(String.format("move %d from:%s to:%s",n,from,to ));
		f(n-1,other,to,from);
	}
	
	public static void main(String[] args) {
		han(3);
	}
}
