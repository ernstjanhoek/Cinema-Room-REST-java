class ThreadUtil {
    public static void printIfDaemon(Thread thread) {
        String message = thread.isDaemon() ? "daemon" : "not daemon";
        System.out.println(message);
    }
}