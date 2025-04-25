import java.util.Arrays;

public class Quicksort {
    public static void main(String[] args) {
        int[] array  = {5, 1, 8, 3, 5, 3, 8, 9, 2, 1, 5, 0};
        int firstIndex = 0;
        int lastIndex = array.length-1;
        quicksort(array, firstIndex, lastIndex);
        System.out.println(Arrays.toString(array));
    }

    static void quicksort( int array[],  int firstIndex, int lastIndex){
        if (array.length > 1){
            int pivot = array[(firstIndex + lastIndex) / 2];
            int left = firstIndex;
            int right = lastIndex;

            while (left <= right) {
                while (array[left] < pivot) {
                    left = left + 1;
                }

                while (array[right] > pivot) {
                    right = right -1;
                }

                if (left <= right) {
                    swap(array, left, right);
                    left = left +1;
                    right = right -1;
                }
                
            }
            if (firstIndex < right) {
                quicksort(array, firstIndex, right);
            }
            if (left < lastIndex) {
                quicksort(array, left, lastIndex);
            }

        }
    }

    public static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
