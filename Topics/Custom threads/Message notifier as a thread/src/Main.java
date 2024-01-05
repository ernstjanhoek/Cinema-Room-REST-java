class MessageNotifier extends Thread {

    String msg;
    int repeats;

    public MessageNotifier(String msg, int repeats) {
        super();
        this.msg = msg;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        System.out.print((msg + "\n").repeat(repeats));
    }
}