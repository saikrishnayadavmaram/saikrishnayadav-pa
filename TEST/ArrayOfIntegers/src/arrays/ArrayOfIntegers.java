package arrays;


import java.util.Arrays;

public class ArrayOfIntegers {

    public static void main(String[] args) {
        // Custom input array
        int[] arr = {4, 3, 5, 6, 1, 2};

        // Applying sort() method over to above array
        // by passing the array as an argument
        Arrays.sort(arr);

        // Printing the array after sorting
        System.out.println(Arrays.toString(arr));
    }
}