package class15_practice.day1;

import java.util.Arrays;
import java.util.List;

/*
 * @description: 朋友圈练习
 */
public class FriendCircle {
    public static class UionFind {
        public static final int MAX_SIZE = 10001;
        private int[] father = new int[MAX_SIZE];
        private int[] size = new int[MAX_SIZE];
        private int[] help = new int[MAX_SIZE];
        public UionFind(List<Integer> c)
        {
            for(int i:c){
                father[i] = i;
                size[i] =1;
            }
        }

        public int find(int i){
            int hi=0;
            while(i != father[i]){
                help[hi++] = i;
                i = father[i];
            }
            for(hi--;hi>=0;hi--){
                father[help[hi]] = i;
            }
            return i;
        }


        public void union(int i,int j){
            int fi = find(i);
            int fj = find(j);
            if(fi != fj){
                if(size[fi] > size[fj]){
                    father[fj] = fi;
                    size[fi] = size[fj] + size[fi];
                }else {
                    father[fi] = fj;
                    size[fj] = size[fi] + size[fj];
                }
            }
        }

        public int getSize(int i){
            return size[find(i)];
        }
    }


    public static void main(String[] args)
    {
        List<Integer> c = Arrays.asList(1,4,6,8,3,1,9,0,10);
        UionFind find = new UionFind(c);
        int i = find.getSize(4);
        System.out.println(i);

        find.union(4,8);
        System.out.println(find.getSize(4));
        System.out.println(find.getSize(8));


    }
}
