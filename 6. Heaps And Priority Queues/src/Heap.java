public class Heap {

    public static <E extends Comparable<E>> void sort(E[] array) {

        int n = array.length;
        for (int i = n / 2; i >= 0; i--) {
            heapifyDown(array, i, n);
        }

        for (int j = n - 1; j > 0; j--) {
            E temp = array[0];
            array[0] = array[j];
            array[j] = temp;
            heapifyDown(array, 0, j);
        }
    }

    private static <E extends Comparable<E>> void heapifyDown(E[] heap, int elementIndex, int length) {
        while (elementIndex < length / 2) {
            int childIndex = 2 * elementIndex + 1;
            if (length > childIndex + 1 && heap[childIndex + 1].compareTo(heap[childIndex]) > 0) {
                childIndex += 1;
            }

            if (heap[elementIndex].compareTo(heap[childIndex]) > 0) {
                break;
            }

            E temp = heap[childIndex];
            heap[childIndex] = heap[elementIndex];
            heap[elementIndex] = temp;
            elementIndex = childIndex;
        }
    }
}
