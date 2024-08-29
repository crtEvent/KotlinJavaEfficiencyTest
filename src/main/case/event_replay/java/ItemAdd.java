package event_replay.java;

record ItemAdd(String productNo, String name, int quantity) implements Command {

    @Override
    public boolean execute(Invoker invoker) {
        return invoker.on(this);
    }
}
