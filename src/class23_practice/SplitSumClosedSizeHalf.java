package class23_practice;

public class SplitSumClosedSizeHalf {
	public static int right(int[] arr) {
		if(arr == null || arr.length<2) {
			return 0;
		}
		
		int a =0;
		int b =0;
		boolean force = false;
		for(int i:arr) {
			if(!force) {
				a+=i;
			}else {
				b+=i;
			}
		}
		return Math.min(a, b);
	}
	

	
}
