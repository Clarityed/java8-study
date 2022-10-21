package java8.Lambda.MethodQuote;

import java.util.Objects;

/**
 * 员工类
 *
 * @author: clarity
 * @date: 2022年10月18日 16:34
 */
public class Employee {

    private int id;
    private String name;
    private int age;
    private double salary;

    public Employee() {
        System.out.println("Employee()");
    }

    public Employee(int id) {
        System.out.println("Employee(int id)");
        this.id = id;
    }

    public Employee(int id, String name) {
        System.out.println("Employee(int id, String name)");
        this.id = id;
        this.name = name;
    }

    public Employee(int id, String name, int age) {
        System.out.println("Employee(int id, String name, int age)");
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && age == employee.age && Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary);
    }
}
