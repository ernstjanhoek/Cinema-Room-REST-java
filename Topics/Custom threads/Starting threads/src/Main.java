public class Main {

    public static void main(String[] args) {
        RunnableWorker task0 = new RunnableWorker();
        RunnableWorker task1 = new RunnableWorker();
        RunnableWorker task2 = new RunnableWorker();
        Thread t0 = new Thread(task0, "worker-0");
        t0.start();
        Thread t1 = new Thread(task1, "worker-1");
        t1.start();
        Thread t2 = new Thread(task2, "worker-2");
        t2.start();
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        } else {
            return;
        }
    }
}