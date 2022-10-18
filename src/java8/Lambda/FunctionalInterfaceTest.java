package java8.Lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 *  Java 内置的四大核心函数式接口
 *  1. 消费型接口 Consumer<T>    void accept(T t)
 *  2. 供给型接口 Supplier<T>    T get()
 *  3. 函数型接口 Function<T, R> R apply(T t)
 *  4. 断定型接口 Predicate<T>   boolean test(T t)
 *
 * @author: clarity
 * @date: 2022年10月18日 15:41
 */
public class FunctionalInterfaceTest {

    @Test
    public void test1() {

        buyVirtualMoney(1000, new Consumer<Integer>() {
            @Override
            public void accept(Integer money) {
                System.out.println("buyVirtualMoney：" + money);
            }
        });

        System.out.println("---------------------------------");

        buyVirtualMoney(500, money -> System.out.println("buyVirtualMoney：" + money));

    }

    public void buyVirtualMoney(Integer money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }


    @Test
    public void test2() {
        List<String> role = Arrays.asList("YuanShenHuTao", "YuanShenKeQing", "YuanShenNiLu", "BHAiLiXia");
        List<String> yuanShenRoleList = isYuanShenRole(role, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("YuanShen");
            }
        });
        System.out.println(yuanShenRoleList);

        System.out.println("-----------------------");

        System.out.println(isYuanShenRole(role, s -> s.contains("YuanShen")));

    }

    public List<String> isYuanShenRole(List<String> role, Predicate<String> predicate) {

        List<String> filterRole = new ArrayList<>();

        for (String r : role) {
            if (predicate.test(r)) {
                filterRole.add(r);
            }
        }

        return filterRole;

    }

}
