package java8;

/**
 * JDK8：除了定义常量和抽象方法之外，还可以定义静态方法、默认方法
 */
public interface CompareA {

    // 静态方法
    public static void method1() {
        System.out.println("CompareA：keQing");
    }

    //默认方法
    public default void method2() {
        System.out.println("CompareA：niLu");
    }

    // 这也是个公共的方法，只是省略了 public
    default void method3() {
        System.out.println("CompareA：niLu");
    }

}
