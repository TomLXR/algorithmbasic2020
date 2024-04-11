package class15_practice.day2;

import java.util.*;
/*
 * 记录 貌似Hash实现 边界处理简单
 */
public class NumberOfIslands {
    public static class Node<T>{
        private T t;
        public Node(T t){
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    public static class Dot{

    }

    public static class UnionFind<V>{
        private Map<Node<V>,Node<V>> parents = new HashMap<>();
        private Map<Node<V>,Integer> size = new HashMap<>();
        private Map<V,Node<V>> nodes = new HashMap<>();

        public  UnionFind(List<V> list){
            if(!Objects.isNull(list)){
                for(V v:list){
                    Node<V> node = new Node<>(v);
                    nodes.put(v,node);
                    parents.put(node,node);
                    size.put(node,1);
                }
            }
        }

        public Node<V> findFather(Node<V> v){
            Stack<Node<V>> stack =new Stack<>();
            while(v != parents.get(v)){
                stack.push(v);
                v = parents.get(v);
            }
            while(!stack.isEmpty()){
                parents.put(stack.pop(),v);
            }
            return v;
        }


        public void union(V v1,V v2){
//            Node<V> n1 = nodes.get(v1);
//            Node<V> n2 = nodes.get(v2);
            Node<V> n1 = findFather(nodes.get(v1));
            Node<V> n2 = findFather(nodes.get(v2));
            if(n1 != n2){
                if(size.get(n1) < size.get(n2)){
                    parents.put(n1,n2);
                    size.put(n2,size.get(n2) + size.get(n1));
                    size.remove(n1);
                }else{
                    parents.put(n2,n1);
                    size.put(n1,size.get(n1) + size.get(n2));
                    size.remove(n2);
                }
            }
        }

        public int getSize(){
            return size.size();
        }
    }

    public static int getNumberOfLands(int[][] matrix){
         List<Dot> list =new ArrayList<>();
         int row = matrix.length;
         int col = matrix.length>0?matrix[0].length:0;
         Dot[][] dotMatrix = new Dot[row][col];
         for(int i=0;i<row;i++){
             for(int j=0 ;j<col;j++){
                 if(matrix[i][j] == 1){
                     dotMatrix[i][j] = new Dot();
                     list.add(dotMatrix[i][j]);
                 }
             }
         }
        UnionFind<Dot> unionFind =new UnionFind<>(list);
         for(int i=0;i<row;i++){
             for(int j=0;j<col;j++){
                  if(i-1 >=0 && (matrix[i][j] ==1 && matrix[i-1][j] == 1)){
                      unionFind.union(dotMatrix[i][j],dotMatrix[i-1][j]);
                  }

                  if(j-1 >=0 && (matrix[i][j] == 1 && matrix[i][j-1] == 1)){
                      unionFind.union(dotMatrix[i][j],dotMatrix[i][j-1]);
                  }
             }
         }
         return unionFind.getSize();
    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{  惊喜还以为对了 结果下面空指针
//                {1,1,0,0,0},
//                {0,1,0,0,1},
//                {1,0,0,1,1},
//                {0,0,0,0,0},
//                {1,0,1,0,1}
//        };

        int[][] matrix = new int[][]{
                {1,1,0,0,0},
                {0,1,0,0,1},
                {1,1,0,1,1},
                {0,0,0,0,0},
                {1,0,1,0,1}
        };
        System.out.println(getNumberOfLands(matrix));


    }
}
