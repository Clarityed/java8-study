package java8.Lambda.MethodQuote;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * 方法引用的使用
 * 1. 使用情景：当要传递给 Lambda 体的操作，已经有实现的方法了，可以使用方法引用！
 * 2. 方法引用本质就是 Lambda 表达式，而 Lambda 表达式作为函数式接口的实例。所以方法引用，也是函数式接口的实例。
 * 3. 使用格式：类（或者对象）:: 方法名
 * 4. 具体分为如下的三种情况：
 *      情况一：对象 :: 非静态方法
 *      情况二： 类 :: 静态方法
 *      情况三： 类 :: 非静态方法
 * 5. 方法引用使用的要求：要求接口中的抽象方法的形参列表和返回值类型与方法引用的方法的形参列表和返回值类型相同！（针对情况一和情况二）
 *
 * @author: clarity
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

    // 情况二：类 :: 静态方法
    // Comparator 中的 int compare(T t1, T t2)
    // Integer 中的 int compare(T t1, T t2)
    @Test
    public void test3() {
        Comparator<Integer> comparator1 = (t1, t2) -> t1.compareTo(t2);
        System.out.println(comparator1.compare(12, 21));

        System.out.println("-----------------------------------");

        Comparator<Integer> comparator2 = Integer::compareTo;
        System.out.println(comparator2.compare(21, 12));
    }

    // Function 中的 R apply(T t)
    // Math 中的 Long round(Double d)
    @Test
    public void test4() {
        Function<Double, Long> function1 = new Function<Double, Long>() {
            @Override
            public Long apply(Double d) {
                return Math.round(d);
            }
        };
        System.out.println(function1.apply(12.1));

        System.out.println("-----------------------------------");

        Function<Double, Long> function2 = d -> Math.round(d);
        System.out.println(function2.apply(12.5));

        System.out.println("-----------------------------------");

        Function<Double, Long> function3 = Math::round;
        System.out.println(function3.apply(12.6));
    }

    // 情况三：类 :: 实例方法
    // Comparator 中的 int compare(T t1, T t2)
    // String 中的 int t1.compareTo(t2)
    @Test
    public void test5() {
        Comparator<String> comparator1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comparator1.compare("abc", "abc"));

        System.out.println("-----------------------------------");

        Comparator<String> comparator2 = String::compareTo;
        System.out.println(comparator2.compare("abc", "abc"));
    }

    // BiPredicate 中的 boolean test(T t1, T t2)
    // String 中的 boolean t1.equals(t2)
    @Test
    public void test6() {
        BiPredicate<String, String> biPredicate1 = (s1, s2) -> s1.equals(s2);
        System.out.println(biPredicate1.test("abc", "abc"));

        System.out.println("-----------------------------------");

        BiPredicate<String, String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("abc", "abd"));
    }

    // Function 中的 R apply(T t)
    // Employee 中的 String getName()
    @Test
    public void test7() {
        Employee employee = new Employee(1001, "keQing", 18, 50000);
        Function<Employee, String> function1 = e -> e.getName();
        System.out.println(function1.apply(employee));

        System.out.println("-----------------------------------");

        Function<Employee, String> function2 = Employee::getName;
        System.out.println(function2.apply(employee));

    }

}
