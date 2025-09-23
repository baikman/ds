public class Problem3 {

    public static void main(String[] args) {
        System.out.println(returnOddSum(Integer.parseInt(args[0])));
    }

    public static int returnOddSum(int n) {
        int sum = 0;

        for (int i = 1; i <= n; i += 2) {
            sum += i;
        }

        return sum;
    }
}