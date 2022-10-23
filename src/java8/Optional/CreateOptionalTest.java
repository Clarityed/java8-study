package java8.Optional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

/**
 * 创建 Optional 类对象
 * Optional 类：为了在程序中避免出现空指针异常而创建的。
 * 常用方法：ofNullable(T t)
 *         orElse(T other)
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

    // 这是我们没有进行空指针判断的方法
    public Role getFirstRole(YuanShen yuanShen) {
        return yuanShen.getRoleList().get(0);
    }

    @Test
    public void test2() {
        // 第一层为空
/*        YuanShen yuanShen1 = null;
        Role firstRole1 = getFirstRole(yuanShen1);
        System.out.println(firstRole1);*/
        // 第二层为空
/*        YuanShen yuanShen2 = new YuanShen();
        Role firstRole2 = getFirstRole(yuanShen2);
        System.out.println(firstRole2);*/
        // 正常情况
        Role role = new Role("keQing", 18);
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        YuanShen yuanShen3 = new YuanShen(roleList);
        Role firstRole = getFirstRole(yuanShen3);
        System.out.println(firstRole);
    }

    // 没使用 Optional 类进行优化后的 getFirstRole()
    public Role getFirstRoleBefore(YuanShen yuanShen) {
        if (yuanShen == null) {
            return null;
        }
        if (yuanShen.getRoleList() == null) {
            return null;
        }
        return yuanShen.getRoleList().get(0);
    }

    @Test
    public void test3() {
        // 第一层为空
/*        YuanShen yuanShen1 = null;
        Role firstRole1 = getFirstRoleBefore(yuanShen1);
        System.out.println(firstRole1);*/
        // 第二层为空
/*        YuanShen yuanShen2 = new YuanShen();
        Role firstRole2 = getFirstRoleBefore(yuanShen2);
        System.out.println(firstRole2);*/
        // 正常情况
        Role role = new Role("keQing", 18);
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        YuanShen yuanShen3 = new YuanShen(roleList);
        Role firstRole = getFirstRoleBefore(yuanShen3);
        System.out.println(firstRole);
    }

    // 使用 Optional 类进行优化后的 getFirstRole()
    public Role getFirstRoleOptional(YuanShen yuanShen) {
        ArrayList<Role> defaultRoleArrayList = new ArrayList<>();
        defaultRoleArrayList.add(new Role("default keQing", 18));
        Optional<YuanShen> yuanShenOptional = Optional.ofNullable(yuanShen);
        // 此时一定 yuanShen 对象一定非空
        YuanShen yuanShen1 = yuanShenOptional.orElse(new YuanShen(defaultRoleArrayList));
        Optional<ArrayList<Role>> roleListOptional = Optional.ofNullable(yuanShen1.getRoleList());
        // 此时一定 roleList 对象一定非空
        ArrayList<Role> roleArrayList = roleListOptional.orElse(defaultRoleArrayList);
        return roleArrayList.get(0);
    }

    @Test
    public void test4() {
        // 第一层为空
/*        YuanShen yuanShen1 = null;
        Role firstRole1 = getFirstRoleOptional(yuanShen1);
        System.out.println(firstRole1);*/
        // 第二层为空
/*        YuanShen yuanShen2 = new YuanShen();
        Role firstRole2 = getFirstRoleOptional(yuanShen2);
        System.out.println(firstRole2);*/
        // 正常情况
        Role role = new Role("keQing", 18);
        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        YuanShen yuanShen3 = new YuanShen(roleList);
        Role firstRole = getFirstRoleOptional(yuanShen3);
        System.out.println(firstRole);
    }

}
