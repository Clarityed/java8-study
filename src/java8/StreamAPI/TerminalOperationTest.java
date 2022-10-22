package java8.StreamAPI;

import java8.Lambda.MethodQuote.Employee;
import java8.Lambda.MethodQuote.EmployeeData;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
    }

}
