package class15_practice.day2;

public class DynamicNumberOfLands {
    public static class FindUnion{
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int row;
        private int sets;

        public FindUnion(int row,int col){
            int length  = row * col;
            parent = new int[length];
            size = new int[length];
            help = new int[length];
            this.col = col;
            this.row = row;
        }

        public int find(int i){
            int hi = 0;
            while(i != parent[i]){
                help[hi++] = i;
                i = parent[i];
            }

            for(hi--;hi>=0;hi--){
                parent[help[hi]] = i;
            }
            return i;
        }

        public int getSize(){
            return  sets;
        }

        public void uion(int r1,int c1,int r2,int c2){
            int index1 = index(r1, c1);
            int index2 = index(r2, c2);
            if(   r1>=0 && r1 <row &&  r2>=0 && r2 <row && c1 >=0 && c1 < col && c2 >=0 && c2 < col  &&  size[index1] >0 && size[index2] >0){
                int n1 = find(index1);
                int n2 = find(index2);
                if(n1 != n2){
                    if(size[n1] < size[n2]){
                        parent[n1] = n2;
                        size[n2] = size[n1] + size[n2];
                    }else {
                        parent[n2] = n1;
                        size[n1] = size[n1] + size[n2];
                    }
                    sets--;
                }
            }

        }

        public void connect(int r,int c){

            int index = index(r, c);
            if(size[index]==0){
                parent[index] = index;
                size[index] = 1;
                sets++;
                uion(r,c,r-1,c);
                uion(r,c,r+1,c);
                uion(r,c,r,c-1);
                uion(r,c,r,c+1);

            }

        }

        public int index(int r,int c){
            return  r*col + c;
        }

    }


    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {1,1,0,0,0},
                {0,1,0,0,1},
                {1,1,0,1,1},
                {0,0,0,0,0},
                {1,0,1,0,1}
        };

        FindUnion union = new FindUnion(matrix.length,matrix[0].length);
        union.connect(0,0);
        System.out.println(union.getSize());
        union.connect(0,1);
        System.out.println(union.getSize());
        union.connect(1,1);
        System.out.println(union.getSize());

        union.connect(2,3);
        System.out.println(union.getSize());


        union.connect(4,0);
        System.out.println(union.getSize());

        union.connect(2,4);
        System.out.println(union.getSize());

    }
}
