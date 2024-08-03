package super_type_token_to_kotlin;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract class JavaGenericTypeDetector<T> {
    private final Type type;

    public JavaGenericTypeDetector() {
        Type superclass = getClass().getGenericSuperclass(); // 왜 super class의 제네릭 타입은 알 수 있는거지?
        if (superclass instanceof ParameterizedType) {
            this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
        } else {
            throw new RuntimeException("Missing type parameter.");
        }
    }

    public void action() {
        if (type.equals(Integer.class)) {
            System.out.println("Integer type");
        } else if (type.equals(String.class)) {
            System.out.println("String type");
        } else {
            System.out.println("Unknown type");
        }
    }

    public static void main(String[] args) {
        var t1 = new JavaGenericTypeDetector<String>() {}; // {} -> 추상 클래스라 인스턴스 생성 못하고 익명 클래스로 구현해야함
        t1.action();

        var t2 = new JavaGenericTypeDetector<Integer>() {};
        t2.action();
    }
}

