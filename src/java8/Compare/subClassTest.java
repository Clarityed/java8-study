package java8.Compare;

/**
 * java 8 新特性测试类
 *
 * @author: clarity
 * @date: 2022年10月17日 10:06
 */
public class subClassTest {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        // subClass.method1();SubClass.method1();
        // 上面注释的都不能调用接口里的静态方法。
        // 知识点1：接口中定义的静态方法，只能通过接口来调用。
        // 与集合 Collection 有关，它是一个接口，以后可以把 Collections 类，把里面的方法拿到接口里实现，有这个趋势。
        CompareA.method1();
        // 知识点2：通过实现类的对象，可以调用接口中的默认方法。
        // 但是，不能直接接口调用，原因很简单，不是静态方法。CompareA.method2()
        // 也不能实现类去调用它 SubClass.method2()，原因一样
        // 如果实现类重写了接口中的默认方法，调用时，仍然调用了重写后的方法。
        subClass.method2();
        // 知识点3：如果子类（或者实现类）继承的父类和实现的接口中声明了同名同参数的默认方法，
        // 那么子类在没有重写此方法的情况下，默认调用的是父类中的同名同参数方法。---> 类优先原则
        // 知识点4：如果实现类实现了多个接口，而这多个接口中定义了同名同参数的默认方法，
        // 那么实现类没有重写此方法的情况下，报错。---> 接口冲突
        // 这就需要外面必修在实现类中重写此方法
        subClass.method3();
        subClass.myMethod();
    }

}

class SubClass extends SuperClass implements CompareA, CompareB {

    //默认方法，覆盖重写
    public void method2() {
        System.out.println("CompareA：lingHua");
    }

    public void method3() {
        System.out.println("SubClass：niLu");
    }

    // 知识点5：如何在子类（或者实现类）的方法中调用父类、接口中被重写的方法
    public void myMethod() {
        // 自己定义的重写的方法
        method3();
        // 调用的是父类中声明的方法
        super.method3();
        // 调用接口中的默认方法
        CompareA.super.method3();
        CompareB.super.method3();
    }

}