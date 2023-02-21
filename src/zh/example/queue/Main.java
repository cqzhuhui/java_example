package zh.example.queue;

public class Main {
    public static void main(String[] args) {
        BlockQueue<Integer> blockQueue = new BlockQueue();
        BlockQueue.QQ qq = blockQueue.getQQ();
        qq.print();
    }
}