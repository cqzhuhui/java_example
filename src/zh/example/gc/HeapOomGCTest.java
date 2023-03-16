package zh.example.gc;

import java.util.ArrayList;
import java.util.List;

public class HeapOomGCTest {
    public static String heapOOMtest() throws InterruptedException {
        List<Person> list = new ArrayList<Person>();
        while (true) {
            list.add(new Person());
            System.out.println("add Person success~");
            Thread.sleep(10);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        heapOOMtest();
    }
}
