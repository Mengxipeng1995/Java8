package com.mxp.java8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符成为箭头操作符或Lambda 操作符
 *     箭头操作符将 Lambda 表达式拆分成两部分：
 *                          左侧: Lambda 表达式的参数列表
 *                          右侧：Lambda 表达式中所需要执行的功能，即 Lambda 体
 *      语法格式一：无参数，无返回值
 *              () -> System.out.println("Hello Lambda");
 *      语法格式二：有一个参数，无返回值
 *              (x) -> System.out.println(x)
 *              只有一个参数，小括号可以不写
 *          x -> System.out.println(x);
 *      语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *               Comparator<Integer> i = (x, y) -> {
 *               System.out.println("函数接口");
 *               return Integer.compare(x, y);
 *               };
 *               如果只有一条语句，大括号和 return 可以不写
 *               Comparator<Integer> i = (x, y) -> Integer.compare(x, y);
 *      语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出数据类型，即"类型推断"
 *              Comparator<Integer> i = (Integer x,Integer y) -> Integer.compare(x, y);
 * 二、Lambda 表达式的才需要"函数式接口"的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解@FunctionalInterface 修饰，可以检查是否是函数式接口
 *
 */
public class TestLambda {
    @Test
    public void test1(){
        int i = 0;//jdk1.7必须为final,jdk1.8为final,但是不用写final标识符

        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Hellow World"+i);
            }
        };
        r.run();

        System.out.println("---------------------");
        //Lambda表达式跟内部类规则相似
        Runnable r1 = () -> System.out.println("Hello Lambda"+i);
        r1.run();

    }

    @Test
    public void test2(){
        Consumer<String> con = x -> System.out.println(x);
        con.accept("术业有专攻");
    }
    @Test
    public void test3(){
        Comparator<Integer> i = (x, y) -> {
            System.out.println("函数接口");
            return Integer.compare(x, y);
        };
    }
}
