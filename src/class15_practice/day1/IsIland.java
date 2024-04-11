package class15_practice.day1;

/*
 * @description:小岛 问题 并查集解法
 * question: 如果是二维数据，如何优化？ 二维数组扁平化
 */
public class IsIland {

    public static class UnionFind {
        private static final int MAX_SIZE = 10001;
        private int[] father = new int[MAX_SIZE];
        private int[] size = new int[MAX_SIZE];
        private int[] help = new int[MAX_SIZE];

        int s = 0;
        public  UnionFind(int[][] matrix){
            int row = matrix.length;
            int col = matrix.length>0?matrix[0].length:0;
            for(int i=0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(matrix[i][j] == 1){
                        father[i*col+j] = i*col+j;
                        size[i*col+j] = 1;
                        s++;
                    }
                }
            }
        }


        public int find(int[][] matrix,int x,int y){
            int col = matrix[0].length;
            int index = x*col+y;
            int hi = 0;
            while(index !=father[index]){
                help[hi++] = index;
                index = father[index];
            }

            for(hi--;hi>=0;hi--){
                father[help[hi]] = index;
            }
            return index;
        }

        public void union(int[][] matrix,int x1, int y1,int x2,int y2){
            int i = find(matrix,x1,y1);
            int j = find(matrix,x2,y2);
            if(i != j){
                if(size[i]< size[j]){
                    father[i] = j;
                    size[j] = size[i] + size[j];
                }else {
                    father[j] = i;
                    size[i] = size[j] + size[i];
                }
                s--;
            }

        }

        public int getSize(){
            return  s;
        }

    }

    public static int[][] generateRandomMatrix(int size){
        int c = (int)(Math.random()*(size + 1));

        int [][] matrix = new int[c][c];
        for(int i=0;i<c;i++){
            for (int j=0 ;j<c;j++){
               matrix[i][j] = Math.random()>0.3 ? 0 :1;
            }
        }
        return matrix;
    }


    public static int isLand(int[][] matrix){
        int row = matrix.length;
        int col = matrix.length>0?matrix[0].length:0;

        int size = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(matrix[i][j] == 1){
                    infect(matrix,i,j);
                    size++;
                }
            }
        }
        return size;
    }

    public static void infect(int[][] matrix,int x,int y){
        if(matrix[x][y] != 1 ){
           return;
        }
        matrix[x][y] =2;
        if(x-1>=0 ){
            infect(matrix,x-1,y);
        }
        if(x+1<matrix.length){
            infect(matrix,x+1,y);
        }

        if(y-1>=0 ){
            infect(matrix,x,y-1);
        }

        if(y+1<matrix[0].length ){
            infect(matrix,x,y+1);
        }

    }


    public static int[][] copyMatrix(int[][] matrix){
        int row = matrix.length;
        int col = matrix.length>0?matrix[0].length:0;
        int[][] newMatrix = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j =0 ;j<col;j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static void main(String[] args)
    {

        boolean flag = true;
        for(int x=0;x<10000;x++){
            int[][] matrix = generateRandomMatrix(100);
            int[][] matrix1 = copyMatrix(matrix);

            UnionFind findset = new UnionFind(matrix);
            int row = matrix.length;
            int col = matrix.length>0?matrix[0].length:0;
            for(int i = 0;i<row;i++){
                for(int j=0;j<col;j++){
                    if(matrix[i][j] == 1 && (i-1>=0 && matrix[i-1][j] == 1)){
                        findset.union(matrix,i,j,i-1,j);
                    }
                    if(matrix[i][j] == 1 && (j-1>=0 && matrix[i][j-1] == 1)){
                        findset.union(matrix,i,j,i,j-1);
                    }
                }
            }

            int size1 = findset.getSize();
            int size2 = isLand(matrix1);
            if(size1 != size2){
                System.out.println("error");
                flag= false;
                break;
            }
        }

        if(flag){
            System.out.println(" done ");
        }
    }
}
