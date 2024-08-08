package multiple_inheritance;

public interface DefaultTemplateMethod {

    default void templateMethod() {
        step1();
        step2();
    }

    void step1();
    void step2();

    public static void main(String[] args) {
        ConcreteClass concreteClass = new ConcreteClass();
        concreteClass.templateMethod();
    }
}

class ConcreteClass implements DefaultTemplateMethod {
    @Override
    public void step1() {
        System.out.println("ConcreteClass implementation of step1");
    }

    @Override
    public void step2() {
        System.out.println("ConcreteClass implementation of step2");
    }
}