package Algorithms;

/**
 * @author Shayan Dasgupta
 * @version 1.4.4
 */
public class sort<T extends Comparable<T>>
{
    String order;

    public sort()
    {
        order = "increasing";
    }

    public sort(String o)
    {
        order = o.toLowerCase();
    }

    /**
     * INSERTION SORT <br>
     * The algorithm sorts the elements in place. The element <code>A[j]</code> is compared with the elements that come
     * before it, which are <code>A[0...j-1]</code>. A rearrangement takes place between elements <code>A[0]</code> to
     * <code>A[j]</code>, at the end of which <code>A[j]</code> is placed in the proper position.
     * Loop invariant: At the start of each <code>for</code> loop the elements of the sub-array
     * <code>A[0...j-1]</code> are originally in positions from 0 to <code>j-1</code> and are in sorted order. <br>
     *
     * Time Complexity: \u0398(n^2) <br>
     * Space Complexity:
     *
     * @param A Array of type T
     */
    public void insertionSort(T[] A)
    {

        int j;
        T key;
        for(int i = 1; i < A.length; i++)
        {
            //the item to be checked
            key = A[i];

            //inserting 'key' into the proper place in the sorted array A[1...j-1]
            j = i - 1;
            while(j >= 0 && key.compareTo(A[j]) < 0)
            {
                A[j+1] = A[j];
                j--;
            }
            A[j+1] = key;
        }

        //checking for sorting order
      if(checkOrder())
          reverse(A);
    }

    /**
     * SELECTION SORT <br>
     * The algorithm sorts by taking the smallest element from the array <code>A[0..n-1]</code> and swapping it with
     * <code>A[0]</code>. Then, it finds the smallest element in the sub-array <code>A[1..n-1]</code> and swaps it with
     * <code>A[1]</code>. In general, the smallest element in <code>A[r..n-1]</code> is swapped with <code>A[r]</code>.
     * It keeps going on in this fashion till <code>r = n-2</code>. Loop invariant: At the start of each <code>for</code>
     * loop, the sub-array <code>A[0..i-1]</code> contains <code>i</code> smallest elements of the array <code>A</code>.<br>
     *
     * Time Complexity: \u0398(n^2) <br>
     * Space Complexity:
     *
     * @param A Array of type T
     */
    public void selectionSort(T[] A)
    {
        int min_pos;
        T temp;

        for(int i = 0; i < A.length-1; i++)
        {
            min_pos = i;

            //finding the smallest element in A[i..n-1]
            for(int j = i + 1; j < A.length; j++)
                if(A[j].compareTo(A[min_pos]) < 0)
                    min_pos = j;

            //swapping A[i] with the smallest element in A[i..n-1]
            temp = A[i];
            A[i] = A[min_pos];
            A[min_pos] = temp;
        }

        //checking for sorting order
        if(checkOrder())
            reverse(A);
    }

    /**
     * MERGE SORT<br>
     * The algorithm utilises Divide-and-Conquer method for sorting. The original array <code>A</code> is divided into
     * two halves, those two halves are sorted and then merged together. The real sorting takes place in the method
     * {@link #merge merge} while merging.<br>
     *
     * Time Complexity : \u0398(n logn)<br>
     * Space Complexity :
     *
     * @param A Array of type T
     * @param start starting point in array to be sorted
     * @param end ending point in array to be sorted
     */
    public void mergeSort(T[] A, int start, int end)
    {
        if(start < end)
        {
            //finding mid-section
            int mid = (start+end)/2;

            //sorting first half
            mergeSort(A,start,mid);

            //sorting second half
            mergeSort(A,mid+1, end);

            //merging the two sorted halves
            merge(A,start,mid,end);
        }

        if((start + end) == A.length - 1 && checkOrder())
            reverse(A);
    }

    /**
     * This method merges the two sub-arrays <code>A[p..q]</code> and <code>A[q+1..r]</code>. A <code>while</code> loop
     * runs and the lesser of the face value of the two sub-arrays is added to <code>A[k]</code>.
     * In this manner we get a sorted and merged sub-array <br><code>A[q..r]</code>. Loop invariant: At the start of each
     * <code>for</code> loop the sub-array <code>A[p..k-1]</code> contains k - p of the smallest elements of
     * <code>L[0..n1]</code> and <code>R[0..n2]</code>.
     *
     * @param A Array of type T
     * @param start starting position of sub-array
     * @param mid middle position of sub-array
     * @param end final position of sub-array
     */
    private void merge(T[] A, int start, int mid, int end)
    {
        int len_L = mid - start + 1; //finding the length of left array
        int len_R = end - mid; //finding the length of right array

        T[] L = (T[]) new Comparable[len_L+1];
        T[] R = (T[]) new Comparable[len_R+1];

        //copying values into sub-arrays
        System.arraycopy(A, start, L, 0, len_L);
        System.arraycopy(A, mid+1, R, 0, len_R);

        int lIndex = 0, rIndex = 0, k = start;

        while(lIndex < len_L && rIndex < len_R)
        {
            if(L[lIndex].compareTo(R[rIndex]) <= 0)
            {
                A[k] = L[lIndex];
                lIndex++;
            }
            else
            {
                A[k] = R[rIndex];
                rIndex++;
            }
            k++;
        }

        //emptying values of left sub-array
        while(lIndex < len_L)
            A[k++] = L[lIndex++];

        //emptying values of right sub-array
        while(rIndex < len_R)
            A[k++] = R[rIndex++];
    }
    private boolean checkOrder()
    {
        return order.equals("decreasing");
    }

    private void reverse(T[] A)
    {
        T temp;
        for(int i = 0; i < A.length/2; i++)
        {
            temp = A[i];
            A[i] = A[A.length-i-1];
            A[A.length-i-1] = temp;
        }
    }
}

