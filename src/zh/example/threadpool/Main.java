package zh.example.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    static ThreadLocal threadLocal = new ThreadLocal<>();
    static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("threadLocal主线程的值");
        Thread.sleep(100);
        new Thread(() -> System.out.println("子线程获取threadLocal的主线程值：" + threadLocal.get())).start();
        Thread.sleep(100);
        inheritableThreadLocal.set("inheritableThreadLocal主线程的值");
        new Thread(() -> System.out.println("子线程获取inheritableThreadLocal的主线程值：" + inheritableThreadLocal.get())).start();

    }
}