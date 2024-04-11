package class15_practice.day1;

import java.util.*;

/**
 * Union set
 * HashMap + Stack 非数字都要map
 *
 */
public class UnionSet1
{
    public static class Node<T>{
        private T t;
        Node(T t){
            this.t = t;
        }

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    public static class UnionFind<T>{
        private Map<T,Node<T>> nodes = new HashMap<>();
        private Map<Node<T>,Node<T>> parent = new HashMap<>();
       private Map<Node<T>,Integer> size = new HashMap<>();

       public UnionFind(Collection<T> c){
           if(c != null){
               for(T t:c){
                   nodes.put(t,new Node<>(t));
                   parent.put(nodes.get(t),nodes.get(t));
                   size.put(nodes.get(t),1);
               }
           }
       }


       public Node<T> findFather(T t){
           /*
            1 获取root节点、收集字节的
            2 遍历root节点的子节点，将子节点的root节点指向root节点
            */
           Stack<Node<T>> stack =new Stack<>();
           Node<T> node = nodes.get(t);
           while(node != parent.get(node)){
               stack.push(node);
               node = parent.get(node);
           }

           while(!stack.isEmpty()){
               Node<T> n = stack.pop();
               parent.put(n,node);
               size.remove(n);
           }
           return node;
       }

       public boolean isSameParent(T t1,T t2){
           return findFather(t1) == findFather(t2);
       }


       public void union(T t1,T t2){
           if(!isSameParent(t1,t2)){
               Node<T> n1 = findFather(t1);
               Node<T> n2 = findFather(t2);
               Node<T> snode = size.get(n1) < size.get(n2) ?n1:n2;
               Node<T> bnode = n1 == snode ?n2:n1;

               parent.put(snode,bnode);
               size.put(bnode,size.get(bnode)+size.get(snode));
               size.remove(snode);
           }

       }

       public Integer getSize(){
           return size.size();
       }

    }


    public static class UnionFind2<T>{
        private Map<Node<T>, Set<Node<T>>> sets = new HashMap<>();
        private Map<T, Node<T>> nodes = new HashMap<>();
        Integer size = 0;


        public  UnionFind2(Collection<T> c){
            for(T t:c){
                nodes.put(t,new Node<>(t));
                size++;
                sets.put(nodes.get(t),new HashSet<>());
                sets.get(nodes.get(t)).add(nodes.get(t));
            }
        }

        public void union(T t1,T t2){
            Node<T> n1 = nodes.get(t1);
            Node<T> n2 = nodes.get(t2);

            Set<Node<T>> set1 = sets.get(n1);
            Set<Node<T>> set2 = sets.get(n2);
            if(set1 != set2){
                set1.addAll(set2);
                for(Node<T> t:set1){
                    sets.put(t,set1);
                }
                size--;
            }
        }

        public Integer getSize(){
            return size;
        }

    }


    public static void main( String[] args )
    {

        UnionFind<Integer> u1 = new UnionFind<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        UnionFind2<Integer> u2 = new UnionFind2<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));

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

        System.out.println(u1.getSize() .equals(u2.getSize()) );

    }
}
