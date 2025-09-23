public class Problem2 {

    public static void main(String[] args) {
        if (isEven(Integer.parseInt(args[0]))) System.out.println("True");
        else System.out.println("False");
    }

    public static boolean isEven(int i) {
        return (i & 1) == 0;
    }
}