public class IndexMinPQ <Key extends Comparable<Key>> {
    private int[] pq;
    private int[] qp;
    private Key[] keys;
    private int N = 0;

    public IndexMinPQ(int maxN) {
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(int k, Key v) {
        N++;
        pq[N] = k;
        qp[k] = N;
        keys[k] = v;
        swim(N);
    }

    public int delMin() {
        int indexOfMin = pq[1];
        exch(1, N--);
        sink(1);
        keys[indexOfMin] = null;
        qp[indexOfMin] = -1;
        return indexOfMin;
    }

    public boolean contains(int k)
    { return qp[k] != -1; }

    public Key min() {
        return keys[pq[1]];
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j + 1, j))
                j++;
            if (!less(j, k))
                break;
            exch(k, j);
            k = j;
        }
    }
}
