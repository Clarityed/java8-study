package java8.Lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * Lambda 表达式测试
 *
 * @author: clarity
 * @date: 2022年10月17日 17:24
 */
public class LambdaTest {

    @Test
    public void test1() {

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("keQing");
            }
        };
        r1.run();

        // Lambda 表达式的写法
        Runnable r2 = () -> System.out.println("keQing");
        r2.run();

    }

    @Test
    public void test2() {

        Comparator<Integer> comparator1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator1.compare(100, 200));

        // Lambda 表达式的写法
        Comparator<Integer> comparator2 = ((o1, o2) -> Integer.compare(o1, o2));
        System.out.println(comparator2.compare(200, 100));

        System.out.println("----------------------------------------");

        // 方法引用
        Comparator<Integer> comparator3 = Integer::compare;
        System.out.println(comparator3.compare(200, 100));

    }

}
