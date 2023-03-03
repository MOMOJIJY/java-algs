package chapter3.section5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Dedup {
    public static void main(String[] args) {
        // HashSET<String> set = new HashSET<>();
        SET<String> set = new SET<>();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (!set.contains(key)) {
                set.add(key);
                StdOut.print(key + " ");
            }
        }

        // StdOut.println("keys:");
        // for (String key : set.keys()) {
        //     StdOut.print(key + " ");
        // }
    }
}
