package class15_practice.day1;

import java.util.Arrays;
import java.util.Collection;

/*
数组实现并查集
 */
public class UnionSet2 {

    public static  class UnionFind{

        int[] parent;
        int[] size;
        int[] help;
        int sets;

        UnionFind(Collection<Integer> c){
            parent = new int[c.size()];
            size = new int[c.size()];
            help = new int[c.size()];
            sets = c.size();
            for (Integer i : c) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public Integer findFather(int n){
            // who to save room 参见 findFather1
            help = new int[size.length];
            int hi = 0;
            while(n != parent[n]){
                help[hi++] = n;
                n = parent[n];
            }

            for(hi--;hi>=0;hi--){
                parent[help[hi]] = n;
            }
            return n;
        }

        public int findFather1(int n){
            // help 需要在初始化对象初始化好
            int hi =0;
            while(n != parent[n]){
                help[hi++] = n;
                n = parent[n];
            }

            // 相当于数据实现堆栈
            for(hi--;hi>=0;hi--){
                parent[help[hi]] = n;
            }

            return n;
        }


        public void union(int n1,int n2){
            int f1 = findFather(n1);
            int f2 = findFather(n2);
            if(f1 != f2){
                int s1 = size[f1];
                int s2= size[f2];

                if(s1 < s2){
                    parent[f1] = f2;
                    size[f2] += size[f1];
                }else{
                    parent[f2] = f1;
                    size[f1] += size[f2];
                }
                sets--;
            }
        }


        public Integer getSize(){
            return sets;
        }

    }


    public static void main(String[] args) {
        UnionFind u1 = new UnionFind(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
        UnionSet1.UnionFind2<Integer> u2 = new UnionSet1.UnionFind2<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));

        u1.union(2,4);
        u2.union(2,4);

        u1.union(4,7);
        u2.union(4,7);

        u1.union(8,9);
        u2.union(8,9);

        u1.union(8,10);
        u2.union(8,10);


        System.out.println(u1.getSize());
        System.out.println(u2.getSize());

        System.out.println(u1.getSize() .equals( u2.getSize()));
    }
}
