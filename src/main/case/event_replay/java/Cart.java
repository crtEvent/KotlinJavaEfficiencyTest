package event_replay.java;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

class Cart extends Invoker {

    static class Item {
        private final String productNo;
        private final String name;
        private final int quantity;

        public Item(String productNo, String name, int quantity) {
            this.productNo = productNo;
            this.name = name;
            this.quantity = quantity;
        }

        public String getProductNo() {
            return productNo;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Item{" +
                "productNo='" + productNo + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
        }
    }

    private final Set<Item> items = new LinkedHashSet<>();

    private boolean add(Item item) {
        return items.add(item);
    }

    private boolean remove(String productNo) {
        if (items.size() > 2) {
            Item itemToRemove = null;
            for (Item item : items) {
                if (item.getProductNo().equals(productNo)) {
                    itemToRemove = item;
                    break;
                }
            }
            if (itemToRemove != null) {
                return items.remove(itemToRemove);
            }
        }
        return false;
    }

    @Override
    protected boolean invoke(Command cmd) {
        if (cmd instanceof ItemAdd) {
            ItemAdd itemAdd = (ItemAdd) cmd;
            return add(new Item(itemAdd.productNo(), itemAdd.name(), itemAdd.quantity()));
        } else if (cmd instanceof ItemRemove) {
            ItemRemove itemRemove = (ItemRemove) cmd;
            return remove(itemRemove.productNo());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}
