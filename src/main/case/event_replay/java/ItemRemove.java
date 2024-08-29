package event_replay.java;

record ItemRemove(String productNo) implements Command {

    @Override
    public boolean execute(Invoker invoker) {
        return invoker.on(this);
    }
}
