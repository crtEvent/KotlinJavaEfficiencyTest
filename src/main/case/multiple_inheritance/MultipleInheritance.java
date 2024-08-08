package multiple_inheritance;

public class MultipleInheritance {

    public static void main(String[] args) {
        MIChild child = new MIChild();
        child.doSomething();
    }
}

interface MIParent1 {
    default void doSomething() {
        System.out.println("Parent 1");
    }

}

interface MIParent2 {
    default void doSomething() {
        System.out.println("Parent 2");
    }

}

class MIChild implements MIParent1, MIParent2 {

    @Override
    public void doSomething() {
        MIParent1.super.doSomething();
        MIParent2.super.doSomething();
    }
}