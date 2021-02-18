import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int n = 10;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;            //sorted array of n elements
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrayList.add(Integer.valueOf(array[i]));
        }

        for(int i : arrayList){
            System.out.println(arrayList.get(i));
        }

    }
}

