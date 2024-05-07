package class07_practice.day1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowHeapGreater {

	public static class HeapGreater<T> {
		public List<T> heapList;
		public Map<T, Integer> indexMap;
		public Comparator<T> comp;

		public HeapGreater(Comparator<T> c) {
			heapList = new ArrayList<>();
			indexMap = new HashMap<>();
			comp = c;
		}

		public boolean compare(int a, int b) {
			return comp.compare(heapList.get(a), heapList.get(b)) < 0;
		}

		public void swap(int a, int b) {
			T ha = heapList.get(a);
			T hb = heapList.get(b);
			heapList.set(b, ha);
			heapList.set(a, hb);
			indexMap.put(hb, a);
			indexMap.put(ha, b);
		}

		public void heapInsert(int index) {
			while (compare(index, (index - 1) / 2)) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		public void heapify(int index) {
			int left = index * 2 + 1;
			while (left < heapList.size()) {
				int max = left + 1 < heapList.size() && compare(left + 1, left) ? left + 1 : left;
				max = compare(max, index) ? max : index;
				if (max == index) {
					break;
				}
				swap(max, index);
				index = left;
				left = index * 2 + 1;
			}

		}

		public boolean isEmpty() {
			return heapList.isEmpty();
		}

		public void push(T t) {
			int ind = heapList.size();
			heapList.add(t);
			indexMap.put(t, ind);
			heapInsert(ind);
		}

		public T poll() {
			if (heapList.isEmpty()) {
				throw new RuntimeException("Empty!!");
			}
			T ans = heapList.get(0);
			swap(0, heapList.size() - 1);
			heapList.remove(heapList.size() - 1);
			indexMap.remove(ans);
			heapify(0);
			return ans;
		}

		public void resign(int index) {
			heapify(index);
			heapInsert(index);
		}

		public void resign(T t) {
			int index = indexMap.get(t);
			resign(index);
		}

		public void remove(T t) {
			int oldInd = indexMap.get(t);
			int replaceInd = heapList.size() - 1;
			swap(oldInd, replaceInd);
			heapList.remove(replaceInd);
			indexMap.remove(t);
			if (oldInd != replaceInd) {
				heapify(oldInd);
				heapInsert(oldInd);
			}

		}
		
		/*
		 * 相當精簡
		 */
		public void remove1(T obj) {
//			T replace = heap.get(heapSize - 1);
//			int index = indexMap.get(obj);
//			indexMap.remove(obj);
//			heap.remove(--heapSize);
//			if (obj != replace) {
//				heap.set(index, replace);
//				indexMap.put(replace, index);
//				resign(replace);
//			}
		}
	}

	public static class Student {
		public int age;
		public int id;

		public Student() {
		}

		public Student(int a) {
			age = a;
			id = a;
		}

		@Override
		public String toString() {
			return "Student [age=" + age + ", id=" + id + "]";
		}

	}

	public static void main(String[] args) {
		HeapGreater<Integer> heap = new HeapGreater<>((a, b) -> b - a);
		heap.push(1);
		heap.push(5);
		heap.push(10);
		heap.push(4);
		heap.push(2);
		heap.push(7);

		while (!heap.isEmpty()) {
			System.out.print(heap.poll() + " ");
		}
		System.out.println(" ");

		HeapGreater<Student> heap1 = new HeapGreater<>((a, b) -> b.age - a.age);
		heap1.push(new Student(1));
		heap1.push(new Student(5));
		heap1.push(new Student(10));
		heap1.push(new Student(4));
		Student s2 = new Student(2);

		heap1.push(s2);
		s2.age = 1000;
		heap1.resign(s2);
		heap1.push(new Student(7));

		heap1.remove(s2);
		while (!heap1.isEmpty()) {
			System.out.print(heap1.poll() + " ");
		}
		System.out.println(" ");

	}

}
