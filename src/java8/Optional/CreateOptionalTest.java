package java8.Optional;

import org.junit.Test;

import java.util.Optional;

/**
 * 创建 Optional 类对象
 *
 * @author: clarity
 * @date: 2022年10月23日 10:29
 */
public class CreateOptionalTest {

    /*
    * 1. Optional.of(T t):创建一个Optional 实例，t必须非空
    * 2. Optional.empty():创建一个空的Optional实例
    * 3. Optional.ofNullable(T t): t可以为null
    * */
    @Test
    public void test1() {
        Role role = new Role();
        // 1. Optional.of(T t):创建一个Optional 实例，t必须非空
        Optional<Role> role1 = Optional.of(role);
        System.out.println(role1);

        // 出现空指针异常
        /*role = null;
        Optional<Role> role2 = Optional.of(role);
        System.out.println(role2);*/
        // 3. Optional.ofNullable(T t): t可以为null
        Optional<Role> role2 = Optional.ofNullable(role);
        System.out.println(role2);
        role = null;
        Optional<Role> role3 = Optional.ofNullable(role);
        System.out.println(role3);
    }

}
