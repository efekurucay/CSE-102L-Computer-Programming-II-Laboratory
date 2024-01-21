import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ex11_20210808053 {
    public static void main(String[] args) {
        int[] a = {3, 1, 0, -2, -1,1,1,2,2,2};
        int count = 0;
        //System.out.println(kthSmallest(a, 2));
        findRepeats(a,1);

    }

    public static int numOfTriplets(int[] arr, int sum) {
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int b = arr[i] + arr[i + 1];
            for (int j = i + 2; j < arr.length; j++)
                if (b + arr[j] < sum)
                    count++;
        }
        return count;
    }

    public static int kthSmallest(int[] arr, int k) {
        if (k >= arr.length || k < 0)
            return 0;
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        return arr[k -1];
    }
    public static void findRepeats(int[] arr, int n){
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            int count = 0;
            int b = 0;
            for (int j = i; j < arr.length; j++){
                if (arr[i] == arr[j]){
                    count++;
                }
                b++;

                }
            if (count > n && !search(arr,b,arr[b])){
                System.out.println(arr[i]);
            }

        }

    }
    public static boolean search(int[] arr, int n, int m){
        for (int i = 0; i < n; i++)
            if (arr[i] == m)
                return true;
        return false;
    }

}
