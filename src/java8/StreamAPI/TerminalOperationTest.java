package java8.StreamAPI;

import java8.Lambda.MethodQuote.Employee;
import java8.Lambda.MethodQuote.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * 测试 Stream 的终止操作
 *
 * @author: clarity
 * @date: 2022年10月22日 11:53
 */
public class TerminalOperationTest {

    // 1. 匹配与查找
    @Test
    public void test1() {
        List<Employee> employeeList = EmployeeData.getEmployees();
        // allMatch(Predicate p)——检查是否匹配所有元素。
        // 练习∶是否所有的员工的年龄都大于18
        boolean allMatch = employeeList.stream().allMatch(employee -> employee.getAge() > 18);
        System.out.println(allMatch);
        // anyMatch(Predicate p)——检查是否至少匹配一个元素。
        // 练习:是否存在员工的工资大于10000
        boolean anyMatch = employeeList.stream().anyMatch(employee -> employee.getSalary() > 10000);
        System.out.println(anyMatch);
        // noneMatch(Predicate p)——检查是否没有匹配的元素。
        // 练习∶是否存在员工姓"雷”
        boolean noneMatch = employeeList.stream().noneMatch(employee -> employee.getName().contains("雷"));
        System.out.println(noneMatch);
        // findFirst——返回第一个元素
        Optional<Employee> employee = employeeList.stream().findFirst();
        System.out.println(employee);
        // findAny——返回当前流中的任意元素，必须使用并行流，且要创建多个并行流，才能看到效果
        Optional<Employee> employee1 = employeeList.stream().findAny();
        Optional<Employee> employee2 = employeeList.stream().findAny();
        Optional<Employee> employee3 = employeeList.parallelStream().findAny();
        Optional<Employee> employee4 = employeeList.parallelStream().findAny();
        Optional<Employee> employee5 = employeeList.parallelStream().findAny();
        System.out.println(employee1);
        System.out.println(employee2);
        System.out.println(employee3);
        System.out.println(employee4);
        System.out.println(employee5);
        // count——返回流中元素的总个数
        long count = employeeList.stream().count();
        System.out.println(count);
        // max(Comparator c)———返回流中最大值
        // 练习:返回最高的工资
        Optional<Double> maxSalary = employeeList.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println(maxSalary);
        // min(Comparator c)——返回流中最小值
        // 练习:返向最低工资的员工
        Optional<Employee> minSalary = employeeList.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(minSalary);
        // forEach(Consumer c) 内部迭代
        employeeList.stream().forEach(System.out::println);
        // 使用集合的遍历操作
        employeeList.forEach(System.out::println);
    }

    // 2. 规约
    @Test
    public void test2() {
        // reduce(T identity, BinaryOperator)————可以将流中元素反复结合起来，得到一个值。返回 T
        // 练习1：计算 1 —— 10 的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        // 原始方法，匿名函数
        Integer sum1 = list.stream().reduce(0, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer i1, Integer i2) {
                return i1 + i2;
            }
        });
        // 进阶写法，Lambda 表达式
        Integer sum2 = list.stream().reduce(0, (i1, i2) -> i1 + i2);
        // 高级写法，方法引用
        Integer sum3 = list.stream().reduce(0, Integer::sum);
        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum3);
        // reduce(BinaryOperator)————可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
        // 练习2：计算公司所有员工工资的总和
        Optional<Double> sumSalary = EmployeeData.getEmployees().stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(sumSalary);
        Optional<Employee> employee = EmployeeData.getEmployees().stream().reduce((e1, e2) -> {
            Employee employee1 = new Employee();
            employee1.setSalary(Double.sum(e1.getSalary(), e2.getSalary()));
            return employee1;
        });
        System.out.println(employee);
    }

    // 3. 收集
    @Test
    public void test3() {
        // collect(Collector c)————将流转换为其他形式。接收一个 Collector 接口的实现，用于 Stream 中元素做汇总的方法。
        // 练习1：查找工资大于 6000 的员工，结果返回为一个 List 或 Set
        List<Employee> employeeList = EmployeeData.getEmployees().stream().filter(employee -> employee.getSalary() > 50000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);
        System.out.println();
        Set<Employee> employeeSet = EmployeeData.getEmployees().stream().filter(employee -> employee.getSalary() > 50000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }

}
