package class07_practice.day1;

import java.util.*;

public class Heap1<T> {

    private static class Node<T>{
        public T t;
        public Node(T t){
            this.t = t;
        }
    }

    public List<Node<T>> heapList;
    public Comparator<T> comparator;


    public Heap1(Comparator<T> comparator){
        heapList = new ArrayList<>();
        this.comparator = comparator;
    }

    /*
    set方法设置交换节点值 不是add
     */
    public void swap(int a,int b){
        Node<T> aNode = heapList.get(a);
        Node<T> bNode = heapList.get(b);
        heapList.set(a,bNode);
        heapList.set(b,aNode);
    }

    public void heapInsert(int index){
        while( comparator.compare(heapList.get(index).t,heapList.get((index-1)/2).t)>0){
            swap(index,(index-1)/2);
            index = (index-1)/2;
        }
    }



    public void push(T t){
        Node<T> node = new Node<>(t);
        heapList.add(node);
        heapInsert(heapList.size()-1);
    }

    public T pop(){
        if(heapList.isEmpty()){
            return null;
        }
        T  ans = heapList.get(0).t;
        swap(0,heapList.size()-1);
        heapList.remove(heapList.size()-1);
        heapify(0);
        return ans;
    }

    public void heapify(int index){
        int left = index*2+1;
        while(left<heapList.size()){
            int max = left+1<heapList.size() && comparator.compare(heapList.get(left+1).t,heapList.get(left).t)>0?left+1:left;
            max = comparator.compare(heapList.get(index).t,heapList.get(max).t)>0?index:max;
            if(max == index){
                break;
            }
            swap(max,index);
            index = max;
            left = index*2+1;
        }
    }

    public boolean isEmpty(){ return heapList.isEmpty();}

    public static void main(String[] args) {
        Heap1<Integer> heap = new Heap1<>((a,b)-> a-b);
        heap.push(4);
        heap.push(1);
        heap.push(8);
        heap.push(6);
        heap.push(7);
        heap.push(11);


        while (!heap.isEmpty()){
            System.out.println(heap.pop());
        }

    }
}
