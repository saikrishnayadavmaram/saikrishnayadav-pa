package String;


public class StringReverse {
    public static String reverseString(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.reverse();

        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        System.out.println(StringReverse.reverseString("KMC India KMC India welcomes you"));


    }
}



