import java.util.*;

public class Heap<E> {
  private ArrayList<E> heap;

  public Heap(){
    heap = new ArrayList<E>();
  }

  private void buildHeap(E[] unsorted){
    for (int i = 0; i < unsorted.length; i++){
      heap.add(unsorted[i]);
    }
    for (int i = (heap.length/2); i >= 1; i--){
      heapify(i);
    }

  }

  private void heapify(int i){
    int curr = heap.get(i);
    if (2*i <= heap.length){
      int left = heap.get(2*i);
    }

    if ((2*i + 1) <= heap.length){
      int right = heap.get(2*i + 1);
    }

    int smallest = curr;
    int sIdx = i;

    if (left && left < smallest){
      smallest = left;
      sIdx = 2*i;
    }

    if (right && right < smallest){
      smallest = right;
      sIdx = 2*i + 1;
    }

    if (smallest != curr){
      heap.set(i, smallest);
      heap.set(sIdx, curr);
      heapify(sIdx);
    }
  }

  private E poll(){
    E item = heap.get(1);
    heap.set(1, heap.remove(heap.length - 1));
    heapify(1);
    return item;

  }

  public E[] sort(E[] unsorted){
    E[] sorted = new E[unsorted.length];
    buildHeap(unsorted);

    for (int i = 0; i < heap.length; i++){
      sorted[i] = poll();
    }

    return sorted;
  }


}