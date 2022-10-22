package java8.StreamAPI;

import java8.Lambda.MethodQuote.Employee;
import java8.Lambda.MethodQuote.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 创建 Stream 的四种方式
 *
 * @author: clarity
 * @date: 2022年10月21日 11:45
 */
public class CreateStreamAPITest {

    // 创建 Stream 方式一：通过集合创建
    @Test
    public void test1() {
        List<Employee> employeeList = EmployeeData.getEmployees();
        // default Stream<E> stream()：返回一个顺序流
        Stream<Employee> stream = employeeList.stream();
        // default Stream<E> parallelStream()：返回一个并行流
        Stream<Employee> parallelStream = employeeList.parallelStream();
    }

    // 创建 Stream 方式二：通过数组
    @Test
    public void test2() {
        // 调用 Arrays 类的 static <T> Stream<T> stream(T[] array)：返回一个流
        int[] arr = new int[]{1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);

        Employee employee1 = new Employee(1001, "clarity", 22, 6000);
        Employee employee2 = new Employee(1002, "clarity", 22, 6000);
        Employee[] employees = new Employee[]{employee1, employee2};
        Stream<Employee> employeeStream = Arrays.stream(employees);
    }

    // 创建 Stream 方式三：通过 Stream 的 of()
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }

    // 创建 Stream 方式四：创建无限流
    @Test
    public void test4() {
        // 迭代
        // public static<T> Stream<T> iterate(final T seed，final UnaryOperator<T> f)
        Stream.iterate(1, t -> t = t + 2).limit(10).forEach(System.out::println);

        // 生成
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

}
