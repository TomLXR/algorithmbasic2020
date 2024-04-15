package class07_practice.day1;

public class Heap {
    /*
    大根堆
     */
    public static class Heap1{
        private int[] heap;
        private int heapSize;
        private int limit;

        public Heap1(int limit){
            heap = new int[limit];
            this.limit = limit;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public boolean isFull(){
            return heapSize == limit;
        }

        public void heapInsert(int index){
            while(heap[index]>heap[(index - 1)/2]){
                swap(index,(index-1)/2);
                 index = (index-1)/2;
            }
        }

        /*
        参数设计 可以指定从那个节点开始做 heapify 所以有param index
         */
        public void heapify(int index, int heapSize){
            int left = index*2+1;
            while(left < heapSize){
                int max = left+1 < heapSize && heap[left+1]>heap[left]?left+1:left;
                max = heap[index]>heap[max]?index:max;
                if(max == index){
                    break;
                }
                swap(max,index);
                index = max;
                left = index*2+1;
            }
        }

        public void swap(int a,int b){
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }

        public void push(int v){
            if(heapSize<limit){
                heap[heapSize] = v;
                heapInsert(heapSize++);
            }
        }

        public int pop(){
            int ans = heap[0];
            swap(0,--heapSize);
            heapify(0,heapSize);
            return ans;
        }
    }

    public static void main(String[] args) {
        Heap1 heap1 =new Heap1(5);
        heap1.push(4);
        heap1.push(1);
        heap1.push(8);
        heap1.push(6);
        heap1.push(7);
        heap1.push(11);


        while (!heap1.isEmpty()){
            System.out.println(heap1.pop());
        }


    }

}
