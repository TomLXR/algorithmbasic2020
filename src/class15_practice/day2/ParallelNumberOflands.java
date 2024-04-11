package class15_practice.day2;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/*
未完待续
 */
public class ParallelNumberOflands {

    public static class UnionFind {
        private Map<String,String> parent = new HashMap<>();
        private Map<String,Integer> size = new HashMap<>();
        private int row;
        private int col;
        private int sets;
        public int gmatrix_r;
        public int gmatrix_c;

        private Map<String,String> side = new HashMap<>();

        public UnionFind(int[][] matrix,int gr,int gc){
            row = matrix.length;
            col = matrix.length>0?matrix[0].length:0;
            gmatrix_r = gr;
            gmatrix_c = gc;

            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(matrix[i][j] == 1){
                       connect(i,j);
                       if(i==0 || j ==0){
                           side.put(key(gmatrix_r*10+i,gmatrix_c*10+j),key(i,j));
                       }
                    }
                }
            }
        }

        public String find(String key){
            Stack<String> stack = new Stack<>();
            while(key != parent.get(key)){
                stack.push(key);
                key = parent.get(key);
            }
            while(!stack.isEmpty()){
                parent.put(stack.pop(),key);
            }
            return key;
        }

        public String find(int r,int c){
            return find(key(r,c));
        }


        public void union(String key1,String key2){
            if(size.containsKey(key1) && size.containsKey(key2)){
                String p1 = find(key1);
                String p2 = find(key2);
                int s1 = size.get(p1);
                int s2 = size.get(p2);
                if(s1<s2){
                    parent.put(p1,p2);
                    size.put(p2,s1+s2);
                }else {
                    parent.put(p2,p1);
                    size.put(p1,s1+s2);
                }
                sets--;
            }
        }

        public void connect(int r,int c){
            String key = key(r, c);
            if(!size.containsKey(key)){
                size.put(key,1);
                parent.put(key,key);
                sets++;
                union(key,key(r+1,c));
                union(key,key(r-1,c));
                union(key,key(r,c+1));
                union(key,key(r,c-1));
            }
        }


        public String key(int r,int c){
            return r+"_"+c;
        }

        public int getSize(){
            return sets;
        }
    }


    public static int[][] generateRandomMatric(int r,int c){
        int row = r * 10;
        int col = c * 10;
        int [][] matrix = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                 matrix[i][j] = Math.random() > 0.3?0:1;
            }
        }
        return matrix;
    }

    public static int[][] copy(int[][] m){
        int row = m.length;
        int col = m.length>0?m[0].length:0;

        int[][] matrix = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                matrix[i][j] = m[i][j];
            }
        }
        return matrix;
    }

    public static class UnionFind2{
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;
        private int col;
        private int row;


        public UnionFind2(int[][] matrix){

        }

        public int find(int r,int c){
            int index = index(r,c);
            int hi = 0;
            while(index != parent[index]){
                help[hi++] = index;
                index = parent[index];
            }
            for(hi--;hi>=0;hi--){
                parent[help[hi]] = index;
            }
            return index;
        }

        public void union(int r1,int c1,int r2,int c2){
            if(r1 < 0 || r1 >= row || r2 < 0 || r2 >= row || c1<0 || c1 >=col || c2<0 || c2 >=col){
                return;
            }
            int index1 = index(r1,c1);
            int index2 = index(r2,c2);
            if(size[index1]>0 && size[index2] > 0){
                int f1 = find(r1, c1);
                int f2 = find(r2, c2);
                if(f1 < f2){
                    parent[f1] = f2;
                    size[f2] = size[f1] +  size[f2];
                }else {
                    parent[f2] = f1;
                    size[f1] = size[f1] +  size[f2];
                }
                sets--;
            }
        }

        public void connect(int r,int c){
            int index= index(r,c);
            if(size[index] == 0){
                parent[index] = index;
                size[index] = 1;
                sets++;
                union(r,c,r+1,c);
                union(r,c,r-1,c);
                union(r,c,r,c+1);
                union(r,c,r,c-1);
            }
        }

        public int index(int r,int c){
            return r * col + c;
        }

        public int getSets(){
            return sets;
        }

    }

    public int parallelProcess(int[][] matrix){

        int row = matrix.length;
        int col = matrix.length>0?matrix[0].length:0;
        int r = row/10;
        int c = col/10;

        UnionFind[][] find = new UnionFind[r][c];
        Wapper[][] wappers = new Wapper[r][c];

        CountDownLatch latch = new CountDownLatch(r*c);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20,20,0, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

        int sets = 0;

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
               int[][] m = new int[10][10];
               int x = i+i*10;
               int y = j+j*10;

                fill(matrix,m,x,y,10);
                wappers[i][j]  =new Wapper(m,i,j);
                final int d = i;
                final int e = j;

                executor.execute( () ->{
                    find[d][e] = new UnionFind(m,d,e);
                    latch.countDown();
                });
            }
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=1;i<r;i++){
            UnionFind u1 = find[i-1][0];
            UnionFind u2 = find[i][0];

        }

        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){


            }
        }

        return sets;

    }

    public static class Wapper{
        public int[][] m;
        public int r;
        public int c;
        public Wapper(int[][] matrix,int row,int col){
            m = matrix;
            r = row;
            c = col;
        }

    }

    public void fill(int[][] sourceMatrix,int[][] targetMatrix,int x,int y,int size){
        for(int i=x;i<size;i++){
            for(int j=y;j<size;j++){
                targetMatrix[i-x][j-y] = sourceMatrix[i][j];
            }
        }
    }

    public static void main(String[] args) {

         boolean flag = true;
         for(int i=0;i<100;i++){
             int[][] m1 = generateRandomMatric(4, 5);
             int[][] m2 = copy(m1);
             UnionFind2 find = new UnionFind2(m1);
             int sets1 = find.getSets();




         }

    }


}
