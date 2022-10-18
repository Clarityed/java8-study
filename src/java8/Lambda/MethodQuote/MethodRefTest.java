package java8.Lambda.MethodQuote;

import org.junit.Test;

import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *
 * 方法引用的使用
 * 1. 使用情景：当要传递给 Lambda 体的操作，已经有实现的方法了，可以使用方法引用！
 * 2. 方法引用本质就是 Lambda 表达式，而 Lambda 表达式作为函数式接口的实例。所以方法引用，也是函数式接口的实例。
 * 3. 使用格式：类（或者对象）:: 方法名
 * 4. 具体分为如下的三种情况：
 *      对象 :: 非静态方法
 *       类 :: 静态方法
 *       类 :: 非静态方法
 * 5. 方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同！
 *
 * @author: scott
 * @date: 2022年10月18日 16:48
 */
public class MethodRefTest {

    // 情况一：对象 :: 实例方法
    // Consumer 中的 void accept(T t)
    // PrintStream 中的 void println(T t)
    @Test
    public void test1() {
        Consumer<String> consumer1 = s -> System.out.println(s);
        consumer1.accept("I love keQing");

        System.out.println("-----------------------------------");

        PrintStream ps = System.out;
        Consumer<String> consumer2 = ps::println;
        consumer2.accept("I love keQing");
    }

    // Supplier 中的 T get()
    // Employee 中的 String getName()
    @Test
    public void test2() {
        Supplier<String> supplier1 = () -> new Employee(1001, "keQing", 18, 50000).getName();
        System.out.println(supplier1.get());

        System.out.println("-----------------------------------");

        Supplier<String> supplier2 = new Employee(1002, "huTao", 18, 50000)::getName;
        System.out.println(supplier2.get());
    }

}
