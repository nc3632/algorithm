/**
 * Example to implement eager singleton
 */

public final class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        if (INSTANCE != null) {
            throw new IllegalStateException("an instance already exists");
        }
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void sayHello() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) {
        EagerSingleton instance = EagerSingleton.getInstance();
        instance.sayHello();
    }
}
