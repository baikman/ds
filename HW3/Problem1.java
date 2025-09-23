public class Problem1 {

    public static void main(String[] args) {
        if (isMultiple(Long.parseLong(args[0]), Long.parseLong(args[1]))) System.out.println("True");
        else System.out.println("False");
    }

    public static boolean isMultiple(long n, long m) {
        return (n%m != 0) ? false : true;
    }
}