package zh.example.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class BlockQueue<T> {

    private int size = 1;
    private LinkedList<T> list = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition addCondition = lock.newCondition();
    private Condition getCondition = lock.newCondition();

    public class QQ {
        public void print() {
            System.out.println("size is " + size);
        }
    }

    public QQ getQQ() {
        return new QQ();
    }

    public void addElement(T t) throws InterruptedException {
        lock.lock();
        try {
            if (size == list.size()) {
                // 阻塞
                addCondition.await();
            }
            try {
                list.offer(t);
            } finally {
                getCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public T getElement() throws InterruptedException {
        lock.lock();
        try {
            if (list.isEmpty()) {
                getCondition.await();
            }
            try {
                return list.pop();
            } finally {
                addCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockQueue<Integer> blockQueue = new BlockQueue();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                    blockQueue.addElement(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    System.out.println(blockQueue.getElement());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
