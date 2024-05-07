package class07_practice.day1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class CoverMax {
	
	public static int maxCover(int[][] lines) {
		int ans = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		
		for(int i=0;i<lines.length;i++) {
			min = Math.min(lines[i][0], min);
			max = Math.max(lines[i][1], max);
		}
		
		for(double i=(min+0.5);i<max;i++) {
			int cover = 0;
			for(int j=0;j<lines.length;j++) {
				if(i>lines[j][0] && i<lines[j][1]) {
					cover++;
				}
			}
			ans= Math.max(cover, ans);
		}
		
		return ans;
	}
	
	public static int maxCover1(int[][] lines) {
		Arrays.sort(lines,(a,b)->a[0]-b[0]);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int cover = 0;
		for(int i=0;i<lines.length;i++) {
			int s=lines[i][0];
			int e=lines[i][1];
			while(!pq.isEmpty() && pq.peek()<=s) {
				pq.poll();
			}
			pq.add(e);
			
			cover = Math.max(cover,pq.size());
			
		}
		return cover;
	}
	
	public static void main(String[] args) {
		
		int[][] lines = new int[][] {
			{1,5},{2,4},{3,7},{4,8}
		};
		
		System.out.println(maxCover(lines));
		System.out.println(maxCover1(lines));
	}

}
