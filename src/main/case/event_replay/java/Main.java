package event_replay.java;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Command> commands = new ArrayList<>();
        commands.add(new ItemAdd("1", "Laptop", 2));
        commands.add(new ItemAdd("2", "Headphones", 3));
        commands.add(new ItemAdd("3", "Backpack", 4));
        commands.add(new ItemRemove("2"));
        commands.add(new ItemAdd("4", "Water Bottle", 5));
        commands.add(new ItemRemove("1"));

        Cart cart = new Cart();
        cart.restore(commands);
        System.out.println(cart);

        cart.on(new ItemAdd("5", "Elderberry", 6));
        System.out.println(cart);
    }
}
