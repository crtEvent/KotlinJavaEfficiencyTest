package event_replay.java;

interface Command {
    boolean execute(Invoker invoker);
}
