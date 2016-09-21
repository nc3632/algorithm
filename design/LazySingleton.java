/**
 * Example of lazy singleton
 */

public class LazySingleton {
    private static class InstanceLoader {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        return InstanceLoader.INSTANCE;
    }

    public void sayHello() {
        System.out.println("Hello world");
    }

    public static void main(String[] args) {
        LazySingleton sol = LazySingleton.getInstance();
        sol.sayHello();
    }
}
