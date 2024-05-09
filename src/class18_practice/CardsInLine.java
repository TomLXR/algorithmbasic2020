package class18_practice;

public class CardsInLine {

	public static int pk(int[] cards) {
		int first = f(cards,0,cards.length-1);
		int second = g(cards,0,cards.length-1);
		return Math.max(first,second);
	}
	
	public static int f(int[] cards,int left,int right) {
		if(left == right) {
			return cards[left];
		}
		return Math.max( cards[left]+ g(cards,left+1,right), cards[right]+ g(cards,left,right-1));
	}
	public static int g(int[] cards,int left,int right) {
		if(left == right) {
			return 0;
		}
		return Math.min(f(cards,left+1,right), f(cards,left,right-1) );
	}
	
	
	public static int pk1(int[] cards) {
		int[][] fdp = new int[cards.length][cards.length];
		int[][] sdp = new int[cards.length][cards.length];
		
		int first = f1(cards,0,cards.length-1,fdp,sdp);
		int second = g1(cards,0,cards.length-1,fdp,sdp);
		return Math.max(first, second);
		
	}
	
	public static int f1(int[] cards,int left,int right,int[][]fdp,int[][]sdp) {
		if(fdp[left][right] != 0) {
			return fdp[left][right];
		}
		int ans;
		if(left == right) {
			ans = cards[left];
		}else {
			ans = Math.max(cards[left] +g1(cards,left+1,right,fdp,sdp), cards[right]+g1(cards,left,right-1,fdp,sdp));
		}
		fdp [left][right] = ans;
		return ans;
	}
	
	public static int g1(int[] cards,int left,int right,int[][]fdp,int[][]sdp) {
		if(sdp[left][right] != 0) {
			return sdp[left][right];
		}
		int ans;
		if(left == right) {
			ans =0;
		}else {
			ans = Math.min(f1(cards,left+1,right,fdp,sdp), f1(cards,left,right-1,fdp,sdp));
		}
		sdp[left][right] = ans;
		return ans;
	}
	
	public static int pk2(int[] cards) {
		int[][] fdp = new int[cards.length][cards.length];
		int[][] sdp = new int[cards.length][cards.length];
		for(int i=0;i<cards.length;i++) {
			fdp[i][i] = cards[i];
		}
		
		int col = 1;
		while(col <cards.length) {
			int r = 0;
			int c =col;
			while(c<cards.length) {
				fdp[r][c] = Math.min(sdp[r+1][c], sdp[r][c-1] );
				sdp[r][c] = Math.max(cards[r]+fdp[r+1][c],cards[c]+fdp[r][c-1]);
				r++;
				c++;
			}
			col++;
		}
		return Math.max(fdp[0][cards.length-1], sdp[0][cards.length-1]);
		
	}
	
	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		System.out.println(pk(arr));
		System.out.println(pk1(arr));
		System.out.println(pk2(arr));
	}
}
