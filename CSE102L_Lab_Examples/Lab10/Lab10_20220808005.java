import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Lab10_20220808005
 */
public class Lab10_20220808005 {

    public static void main(String[] args) {
        
ArrayList<Integer> arrayList = new ArrayList<>();
LinkedList<Integer> linkedList = new LinkedList<>();

arrayList.add(0);
arrayList.add(1);
linkedList.add(0);
linkedList.add(1);

long start =System.currentTimeMillis();

for (int i = 0; i < 1000000; i++) {
  arrayList.add(i);

}
long end = System.currentTimeMillis();

System.out.println(end-start);


    }
}