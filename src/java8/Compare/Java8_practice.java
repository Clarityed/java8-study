package java8.Compare;

/**
 * java8 新特性应用
 *
 * @author: clarity
 * @date: 2022年10月17日 10:50
 */

interface Filial { // 孝顺
    default void help() {
        System.out.println("老妈，我来救你了");
    }
}

interface Spoony { // 痴情的
    default void help() {
        System.out.println("媳妇，别怕，我来了");
    }
}

class Father {
    public void help() {
        System.out.println("儿子救我媳妇");
    }
}

class Son extends Father implements Filial, Spoony {

    @Override
    public void help() {
        super.help();
        Filial.super.help();
        Spoony.super.help();
    }
}

public class Java8_practice  {

    public static void main(String[] args) {
        Son son = new Son();
        son.help();
    }

}
