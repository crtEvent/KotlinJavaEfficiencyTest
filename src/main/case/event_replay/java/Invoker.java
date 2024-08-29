package event_replay.java;

import java.util.ArrayList;
import java.util.List;

abstract class Invoker {
    private final List<Command> commands = new ArrayList<>();

    public List<Command> save() {
        List<Command> savedCommands = new ArrayList<>(commands);
        commands.clear();
        return savedCommands;
    }

    public boolean restore(List<Command> data) {
        if (!commands.isEmpty() || data.isEmpty()) {
            return false;
        } else {
            for (Command cmd : data) {
                if (!on(cmd, true)) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean on(Command cmd, boolean isReplay) {
        if (invoke(cmd)) {
            if (!isReplay) {
                commands.add(cmd);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean on(Command cmd) {
        return on(cmd, false);
    }

    protected abstract boolean invoke(Command cmd);
}
