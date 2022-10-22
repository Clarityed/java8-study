package java8.StreamAPI;

import java8.Lambda.MethodQuote.Employee;
import java8.Lambda.MethodQuote.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * 测试 Stream 的中间操作
 *
 * @author: clarity
 * @date: 2022年10月22日 9:38
 */
public class MiddleOperationTest {

    // 1. 筛选与切片
    @Test
    public void test1() {
        // 首先获得员工集合
        List<Employee> employeeList = EmployeeData.getEmployees();
        // filter(Predicate p)———接收Lambda ,从流中排除某些元素。
        employeeList.stream().filter(employee -> employee.getSalary() > 50000).forEach(System.out::println);
        System.out.println("----------------------------------------");
        // limit(n)——截断流，使其元素不超过给定数量。
        employeeList.stream().limit(5).forEach(System.out::println);
        System.out.println("----------------------------------------");
        // skip(n)——跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。
        employeeList.stream().skip(5).forEach(System.out::println);
        System.out.println("----------------------------------------");
        // 与limit(n)互补distinct()——筛选，通过流所生成元素的 hashCode()和equals()去除重复元素
        employeeList.add(new Employee(1009, "muoNa", 22, 90000));
        employeeList.add(new Employee(1009, "muoNa", 22, 90000));
        employeeList.add(new Employee(1009, "muoNa", 22, 90000));
        employeeList.add(new Employee(1009, "muoNa", 22, 90000));
        employeeList.add(new Employee(1009, "muoNa", 22, 90000));
        employeeList.add(new Employee(1009, "muoNa", 22, 90000));
        System.out.println(employeeList); // 验证是否添加成功
        // distinct() 两条数据完全相同
        employeeList.stream().distinct().forEach(System.out::println);
    }

    // 2. 映射
    @Test
    public void test2() {
        // map(Function f)————接收一个函数参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase(Locale.ROOT)).forEach(System.out::println);
        System.out.println("----------------------------------------");
        // 练习1：获取员工姓名长度大于 4 的员工的姓名
        EmployeeData.getEmployees().stream().map(Employee::getName).filter(str -> str.length() > 4).forEach(System.out::println);
        System.out.println("----------------------------------------");
        // 练习2：
        list.stream().map(MiddleOperationTest::fromStringToStream).forEach(str -> {
            str.forEach(System.out::println);
        });
        System.out.println("----------------------------------------");
        // flatMap(Function f)————接收一个函数参数，将流中的每个值都换成了另一个流，然后把所有流连接成一个流，下面测试三举例理解
        list.stream().flatMap(MiddleOperationTest::fromStringToStream).forEach(System.out::println);
    }

    // 将字符串中的多个字符构成的集合转换为对应的 Stream 实例
    public static Stream<Character> fromStringToStream(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    // 关于 flatMap 举例理解
    @Test
    public void test3() {
        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        list1.add(list2);
        System.out.println(list1);
        list1.addAll(list2);
        System.out.println(list1);
        // 可以知道的是一个是加入集合，一个是加入另一个集合里的数据
        // map 类似是加入一个集合，流集合里面在嵌套一个流集合
        // 而 flatMap 则是解决这个问题，将流集合里的流集合打开连成一个流
    }

    // 3. 排序
    @Test
    public void test4() {
        // sorted()————自然排序，默认从小到大
        List<Integer> list = Arrays.asList(12, 34, 545, 67, 89, -23, 0);
        list.stream().sorted().forEach(System.out::println);
        System.out.println("----------------------------------------");
        // 下面这句代码报错，原因是该对象没有实现排序接口
        // 解决方案：1. 排序对象实现排序接口。2. 采用定制化的 sorted()
        // EmployeeData.getEmployees().stream().sorted().forEach(System.out::println);
        // sorted(Comparator com)————定制化排序
        // 按工资高低排序
        EmployeeData.getEmployees().stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .forEach(System.out::println);
        System.out.println("----------------------------------------");
        // 按工资高低排序，但是工资相同的情况下，按 id 比较
        List<Employee> employeeList = EmployeeData.getEmployees();
        employeeList.add(new Employee(1000, "NaXiDa", 500, 80000));
        employeeList.stream().sorted((e1, e2) -> {
            int result = Double.compare(e1.getSalary(), e2.getSalary());
            if (result != 0) {
                return result;
            } else {
                return -Integer.compare(e1.getId(), e2.getId());
            }
        }).forEach(System.out::println);
    }
}
