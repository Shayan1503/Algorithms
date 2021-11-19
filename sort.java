package Algorithms;

/**
 * @author Shayan Dasgupta
 * @version 1.3
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
     * <code>A[0...j-1]</code> are originally in positions from 1 to <code>j-1</code> and are in sorted order. <br>
     *
     * Time Complexity: O(n^2) <br>
     * Space Complexity:
     *
     * @param A Array of type T
     * @return sorted Array
     */
    public T[] insertion(T[] A)
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
          return reverse(A);

      return A;
    }

    /**
     * SELECTION SORT <br>
     * The algorithm sorts by taking the smallest element from the array <code>A[0..n-1]</code> and swapping it with
     * <code>A[0]</code>. Then, it finds the smallest element in the sub-array <code>A[1..n-1]</code> and swaps it with
     * <code>A[1]</code>. In general, the smallest element in <code>A[r..n-1]</code> is swapped with <code>A[r]</code>.
     * It keeps going on in this fashion till <code>r = n-2</code>. Loop invariant: At the start of each <code>for</code>
     * loop, the sub-array <code>A[0..i-1]</code> contains <code>i</code> smallest elements of the array <code>A</code>.<br>
     *
     * Time Complexity: O(n^2) <br>
     * Space Complexity:
     *
     * @param A Array of type T
     * @return sorted Array
     */
    public T[] selection(T[] A)
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
            return reverse(A);

        return A;
    }

    private boolean checkOrder()
    {
        return order.equals("decreasing");
    }
    private T[] reverse(T[] A)
    {
        T temp;
        for(int i = 0; i < A.length/2; i++)
        {
            temp = A[i];
            A[i] = A[A.length-i - 1];
            A[A.length-i - 1] = temp;
        }

        return A;
    }
}

