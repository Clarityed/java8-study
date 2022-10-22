package java8.Lambda.MethodQuote;

import java.util.ArrayList;
import java.util.List;

/**
 * 获得员工列表
 *
 * @author: clarity
 * @date: 2022年10月18日 16:40
 */
public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001, "keQing", 18, 10000));
        list.add(new Employee(1002, "huTao", 18, 20000));
        list.add(new Employee(1003, "NiLu", 18, 30000));
        list.add(new Employee(1004, "GanYu", 18, 40000));
        list.add(new Employee(1005, "QiQi", 18, 50000));
        list.add(new Employee(1006, "keLi", 18, 60000));
        list.add(new Employee(1007, "AiLi", 18, 70000));
        list.add(new Employee(1008, "LingHua", 18, 80000));

        return list;
    }

}
