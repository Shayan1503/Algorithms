package Algorithms;

/**
 * @author Shayan Dasgupta
 * @version 1.0
 * @param <T>
 */

public class search <T extends Comparable<T>>
{
    /** LINEAR SEARCH is the simplest algorithm for searching a value in an array.
     * It takes an array <code>A</code> of size <code>n</code> and the value <code>v</code> to be searched as the parameters.
     * A loop scans through <code>A</code> and returns the position of <code>v</code>. On the absence of <code>v</code>,
     * the function returns -1.
     * The loop invariant for this algorithm is the fact that for any ith iteration of the <code>for</code>
     * loop, the value <code>v</code> is not present in the sub-array <code>A[0...i-1]</code>.<br>
     *
     * Time Complexity: O(n)<br>
     * Space Complexity:
     *
     * @param a array of type T
     * @param v value to be searched
     * @return position of the value in the array otherwise -1
     */
    public int linear(T[] a, T v)
    {
        for(int i = 0; i < a.length; i++)
        {
            if(a[i].equals(v))
                return i;
        }

        return -1;
    }
}
