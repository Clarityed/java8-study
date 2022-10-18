package java8.Lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *
 * Lambda 表达式的使用，接口要求只有一个抽象方法
 *
 * 1. 举例：(o1, o2) -> Integer.compare(o1, o2);
 * 2. 格式：
 *      -> ：Lambda 操作符或者称为箭头操作符
 *      -> 左边：Lambda 形参列表（其实就是接口中的抽象方法的参数列表）
 *      -> 右边：Lambda 体（其实就是重写抽象方法的方法体）
 * 3. Lambda 表达式的使用：（分为 6 种情况介绍）
 *      总结：
 *          （1）-> 左边：Lambda 形参列表的参数类型可以省略（类型推断）；如果 Lambda 形参列表只有一个参数，其一对 () 也可以省略
 *          （2）-> 右边：Lambda 体应该使用一对 {} 包裹；如果 Lambda 体只有一条执行语句（可能是 return 语句），可以省略这对 {}，但是 return 语句一定要省略
 * 4. Lambda 表达式的本质：作为函数式接口的实例（就是函数式接口实现类的对象）
 * 5. 如果一个接口中，只声明了一个抽象方法，则此接口就称为函数式接口。
 *    我们可以在一个接口上使用 @FunctionalInterface 注解，这样做就可以检查它是否是一个函数式接口。
 * 6. 以前用匿名实现类表示的现在都可以用 Lambda 表达式来写。
 *
 * @author: clarity
 * @date: 2022年10月17日 20:01
 */
public class LambdaGrammarTest {

    // 语法格式一：无参，无返回值
    @Test
    public void test1() {

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("keQing");
            }
        };
        r1.run();

        System.out.println("---------------------------");

        // Lambda 表达式的写法
        Runnable r2 = () -> {
            System.out.println("keQing");
        };
        r2.run();

    }

    // 语法格式二：Lambda 需要一个参数，但是没有返回值
    @Test
    public void test2() {

        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("我能够接收一个字符串：" + s);
            }
        };
        consumer1.accept("keQing");

        System.out.println("---------------------------");

        Consumer<String> consumer2 = (String s) -> {
            System.out.println("我能够接收一个字符串：" + s);
        };
        consumer2.accept("huTao");

    }

    // 语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为”类型推断“
    // 还有关于类型推断的知识，泛型，数组。例如：int[] i1 = new int[2];int[] i2 = {1, 2};
    @Test
    public void test3() {

        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("我能够接收一个字符串：" + s);
            }
        };
        consumer1.accept("keQing");

        System.out.println("---------------------------");

        Consumer<String> consumer2 = (s) -> {
            System.out.println("我能够接收一个字符串：" + s);
        };
        consumer2.accept("huTao");

    }

    // 语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略
    @Test
    public void test4() {

        Consumer<String> consumer1 = (s) -> {
            System.out.println("我能够接收一个字符串：" + s);
        };
        consumer1.accept("huTao");

        System.out.println("---------------------------");

        Consumer<String> consume2 = s -> {
            System.out.println("我能够接收一个字符串：" + s);
        };
        consume2.accept("keQing");

    }

    // 语法格式五：需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test5() {

        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(comparator1.compare(11, 22));

        System.out.println("---------------------------");

        Comparator<Integer> comparator2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(comparator2.compare(22, 11));

    }

    // 语法格式六：当 Lambda 体只有一条语句时，return 与大括号有都可以省略，不过省略掉大括号 return 一定要省略，否则回报错。
    @Test
    public void test6() {

        Comparator<Integer> comparator1 = (o1, o2) -> {
            return o1.compareTo(o2);
        };
        System.out.println(comparator1.compare(22, 11));

        System.out.println("---------------------------");

        Comparator<Integer> comparator2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator2.compare(11, 22));

    }

    @Test
    public void test7() {

        Consumer<String> consume1 = s -> {
            System.out.println("我能够接收一个字符串：" + s);
        };
        consume1.accept("keQing");

        System.out.println("---------------------------");

        Consumer<String> consume2 = s -> System.out.println("我能够接收一个字符串：" + s);
        consume2.accept("huTao");

    }

}
