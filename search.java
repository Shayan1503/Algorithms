package Algorithms;

/**
 * @author Shayan Dasgupta
 * @version 1.1
 * @param <T>
 */

public class search <T extends Comparable<T>>
{
    /** LINEAR SEARCH<br>
     * This algorithm is the simplest algorithm for searching a value in an array.
     * A loop scans through <code>A</code> and returns the position of <code>v</code>. On the absence of <code>v</code>,
     * the function returns -1.
     * The loop invariant for this algorithm is the fact that for any ith iteration of the <code>for</code>
     * loop, the value <code>v</code> is not present in the sub-array <code>A[0...i-1]</code>.<br>
     *
     * Time Complexity: \u0398(n)<br>
     * Space Complexity:
     *
     * @param A array of type T
     * @param v value to be searched
     * @return true if the value is present in <code>A</code>
     */
    public boolean linear(T[] A, T v)
    {
        for (T t : A) {
            if (t.equals(v))
                return true;
        }

        return false;
    }

    /**BINARY SEARCH<br>
     * This algorithm works <strong>only</strong> for arrays that are sorted. Inorder to reduce the burden on the user
     * to sort the array, the method sorts the array by itself by calling the method {@link sort#merge merge}.
     * The element at the middle of the array <code>A</code> is checked against the value <code>v</code>.
     * If <code>v</code> is greater than the midpoint then the other half of the array is discarded.
     * We get a new midpoint element and the search begins again. Loop invariant:
     *
     * Time Complexity: \u0398(logn)<br>
     * Space Complexity:
     *
     * @param A array of type T
     * @param v value to be searched
     * @return true if the value is present in <code>A</code>
     */
    public boolean binary(T[] A, T v)
    {
        //making a copy and sorting array
        sort<T> array_sort = new sort<>();
        T[] copy = (T[]) new Comparable[A.length];
        System.arraycopy(A, 0, copy, 0, A.length);
        array_sort.merge(copy, 0, copy.length-1);

        int Lend = 0, Rend = A.length-1, mid;

        //finding where v lies
        while(Lend <= Rend)
        {
            mid = (Lend + Rend)/2;
            if(copy[mid].compareTo(v) == 0)
                return true;
            else if(copy[mid].compareTo(v) < 0)
                Lend = mid + 1;
            else
                Rend = mid - 1;
        }

        return false;
    }
}
