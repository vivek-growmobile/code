/*
Counting Sort
  1) Create an array of indices that would represent a sorted array
  2) Use this array to sort the original array
  Run Time -> O(n^2)
  Stable? -> Yes if in[i] > in[j], No in[i] >= in[j]
*/

public static int[] countingSort(int[] in){
  int[] indices = new int[in.length];
  int[] sorted = new int[in.length];

  for (int i = 0; i < in.length; i++){
    for (int j = i + 1; j < in.length; j++){
      if (in[i] > in[j]) indices[i]++;
      else indices[j]++;
    }
  }

  for (int k = 0; k < in.length; k++){
    sorted[indices[k]] = in[k];
  }

  return sorted;
}


/*
Selection Sort v1
  1) Go through array if you find a smaller number to the right swap the numbers
  Run Time -> O(n^2)
  Stable? -> No eg [3,3,2]
*/

public static int[] selectionSort1(int[] in){
  for (int i = 0; i < in.length; i++){
    for (int j = i+1; j < in.legnth; j++){
      if (in[i] > in[j]){
        int tmp = in[j];
        in[j] = in[i];
        in[i] = tmp;
      }
    }
  }
  return in;
}

/*
Selection Sort v2
  1) Go through array if you find a smaller number to the right save it
  2) Once your through the array if you found a smaller number swqp the two
  Run Time -> O(n^2)
  Stable? => No eg [3,3,2]
*/


public static int[] selectionSort2(int[] in){
  for (int i = 0; i < in.length; i++){
    int min = in[i];
    int minIdx = i;
    for (int j = i+1; j < in.length; j++){
      if (min > in[j]){
        min = in[j];
        minIdx = j
      }
    }
    if (minIdx != i){
      in[minIdx] = in[i];
      in[i] = min;
    }
  }
}

/*
Insertion Sort
  1) Go through the array determine that elements position in the left subarray
  Run Time -> O(n^2)
  Stable? => Yes
*/

public static int[] insertionSort(int[] in){
  for (int i = 1; i < in.length; i++){
    for (int j = i; j > 0 && in[j] < in[j-1]; j--){
      int tmp = in[j-1];
      in[j-1] = in[j];
      in[j] = tmp;
    }
  }
  return in;
}

/*
Bubble Sort
  1) Go through the array and swap items that are out of order
  2) Continue going through array until it is in order
  Run Tume -> O(n^2)
  Stable -> Yes
*/

public static int[] bubbleSort(int[] in){
  boolean swapped;
  do {
    swapped = false;
    for (int i = 0; i < in.length - 1; i++){
      if (in[i] > in[i+1]){
        int tmp = in[i+1];
        in[i+1] = in[i];
        in[i] = tmp;
        swapped = true;
      }
    }
  } while (swapped);
  return in;
}

/*
Merge Sort
  1) Split the array in half
  2) Merge the individual arrays back together in order
  Run Time -> O(nlogn)
  Stable
*/

public static int[] mergeSort(int[] in){
  if (in.length <= 1){
    return in;
  }
  else {
    int mid = in.length / 2;
    int[] left = new int[mid];
    int[] right = new int[in.length - mid];

    System.arraycopy(in, 0, left, 0, left.length);
    System.arraycopy(in, mid, right, 0, right.length);

    left = mergeSort(left);
    right = mergeSort(right);

    return merge(left,right);
  }
}

private static int[] merge(int[] left, int[] right){
  int[] merged = new int[left.length + right.length];
  int leftIdx = 0;
  int rightIdx = 0;
  int mergedIdx = 0;

  while (leftIdx < left.length && rightIdx < right.length){
    if (right[rightIdx] < left[leftIdx]){
      merged[mergedIdx++] = right[rightIdx++];
    }
    else {
      merged[mergedIdx++] = left[leftIdx++];
    }
  }
  if (leftIdx < left.length){
    System.arraycopy(left, leftIdx, merged, mergedIdx, left.length - leftIdx);
  }
  else if (rightIdx < right.length){
    System.arraycopy(right, rightIdx, merged, mergedIdx, right.length - rightIdx);
  }
  return merged;
}

