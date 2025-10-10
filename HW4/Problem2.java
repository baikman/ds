package HW4;

public class Problem2 {
    public static void main(String[] args) {
        String test = "13";
        System.out.println(stringToInt(test));
    }

    public static int stringToInt(String s) {
        if (s.length() == 1) return (s.charAt(0) - '0');
        else {
            String restString = s.substring(0, s.length() - 1);
            return stringToInt(s.substring(s.length() - 1, s.length())) + (stringToInt(restString) * 10);
        }
    }
}