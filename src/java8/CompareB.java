package java8;

public interface CompareB {

    // 这也是个公共的方法，只是省略了 public
    default void method3() {
        System.out.println("CompareB：niLu");
    }

}
