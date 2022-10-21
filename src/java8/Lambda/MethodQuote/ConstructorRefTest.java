package java8.Lambda.MethodQuote;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用和数组引用
 * 1. 构造器引用：和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致，
 *              且抽象方法的返回值类型即为构造器所属的类的类型。
 * 2. 数组引用：可以把数组看做一个特殊的类，则写法与构造器引用一致。
 *
 * @author: clarity
 * @date: 2022年10月21日 10:34
 */
public class ConstructorRefTest {

    // 构造器引用
    // Supplier 中的 T get()
    // Employee 的空参数构造器：Employee()
    @Test
    public void test1() {
        Supplier<Employee> supplier1 = () -> new Employee();
        System.out.println(supplier1.get());

        System.out.println("-----------------------------");

        Supplier<Employee> supplier2 = Employee::new;
        System.out.println(supplier2.get());
    }

    // Function 中的 R apply(T t)
    @Test
    public void test2() {
        Function<Integer, Employee> function1 = id -> new Employee(id);
        Employee employee = function1.apply(1001);
        System.out.println(employee);

        System.out.println("-----------------------------");

        Function<Integer, Employee> function2 = Employee::new;
        System.out.println(function2.apply(1002));
    }

    // BiFunction 中的 R apply(T t, U u)
    @Test
    public void test3() {
        BiFunction<Integer, String, Employee> biFunction1 = (id, name) -> new Employee(id, name);
        System.out.println(biFunction1.apply(1001, "clarity"));

        System.out.println("-----------------------------");

        BiFunction<Integer, String, Employee> biFunction2 = Employee::new;
        System.out.println(biFunction2.apply(1002, "clarity"));
    }

    // 数组引用与构造器引用类似，把数组看成类类型
    // Function 中的 R apply(T t)
    @Test
    public void test4() {
        Function<Integer, String[]> function1 = length -> new String[length];
        String[] arr1 = function1.apply(5);
        System.out.println(Arrays.toString(arr1));

        System.out.println("-----------------------------");

        Function<Integer, String[]> function2 = String[]::new;
        String[] arr2 = function2.apply(10);
        System.out.println(Arrays.toString(arr2));
    }

}
