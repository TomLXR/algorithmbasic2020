package class19_practice;

public class ConvertToLetterString {
	public static int number(String s) {
		char[] str = s.toCharArray();
		return process(str,0);
		
	}
	
	/*
	 * 1先看base case
	 */
	public static int process(char[] str,int index) {
		
		if(index == str.length) {
			return 1;
		}
		if(str[index] =='0') {
			return 0;
		}
		
		int p1 = process(str,index+1) ;
		int p2=0;
		if(index+1<str.length && ((str[index]-'0')*10+(str[index+1]-'0')) <27 ) {
			p2=process(str,index+2);
		}
		return p1+p2;
		
	}
	
	
	public static int number1(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process1(str.toCharArray(), 0);
	}

	// str[0..i-1]转化无需过问
	// str[i.....]去转化，返回有多少种转化方法
	public static int process1(char[] str, int i) {
		if (i == str.length) {
			return 1;
		}
		// i没到最后，说明有字符
		if (str[i] == '0') { // 之前的决定有问题
			return 0;
		}
		// str[i] != '0'
		// 可能性一，i单转
		int ways = process(str, i + 1);
		if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			ways += process(str, i + 2);
		}
		return ways;
	}
	
	public static int number2(String s) {
		
          char[] str = s.toCharArray();
		  int n = str.length;
		  int[] dp = new int[n+1];
		  dp[n] = 1;
		  
		  for(int i=n-1;i>=0;i--) {
			  int ways = dp[i+1];
			if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			   	ways += dp[i+2];
			  }
			  dp[i] = ways;
		  }
		  return dp[0];
	}

	
	public static void main (String[] args) {
		
		System.out.println(number("1212121166121"));
		System.out.println(number1("1212121166121"));
		System.out.println(number2("1212121166121"));
		
	}

}
