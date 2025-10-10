package HW4;

public class Problem1 {
    public static void main(String[] args) {
        int arr[] = {3, 1, 4, 1, 5, 9 , 2};
        System.out.println(findMaxRecursive(arr, 0, arr.length));
    }

    public static int findMaxRecursive(int[] A, int low, int count) {
        if (count == 1) return A[low];
        else {
            int half = (int)Math.ceil(count/2);
            int first = findMaxRecursive(A, low, half);
            int second = findMaxRecursive(A, low + half, count - half);
            return ((first > second) ? first : second);
        }
    }
}