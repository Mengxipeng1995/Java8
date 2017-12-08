package com.mxp.java8;

import com.mxp.model.Student;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用 : 若 Lambda 体中的内容有方法已经实现了，我们可以使用"方法引用"
 *          (可以理解为方法引用是 Lambda 表达式的另外一种表现形式）
 * 主要有三种语法格式：
 *
 * 对象::实例方法名
 *
 * 类 ::静态方法名
 *
 * 类 ::实例方法名
 *
 * 注意:
 *   ①Lambda 体中调用方法的参数列表与返回值类型要与函数式接口中抽象方法的参数列表和返回值保持一致
 *   ②若 Lambda 参数列表中的第一位参数是实例方法的调用者，第二位参数是实例方法的参数时，可以使用 类名::方法名
 *
 * 二、构造器引用:
 *
 *  格式：
 *
 *  ClassName::new
 *
 * 三、数组引用：
 *
 *  Type::new
 */

public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test1(){
        Consumer<String> con = System.out::println;
        con.accept("666");
        //即
        Student stu = new Student();
        Supplier<String> sup = stu::getName;
        System.out.println( sup.get());

    }

    //类::静态方法名
    @Test
    public void test2(){
        Comparator<Integer> com = (x,y) -> Integer.compare(x,y);

        Comparator<Integer> com1 = Integer::compare;

        System.out.println(com1.compare(4,3));
    }
    //类::实例方法名
    @Test
    public void test3(){
        BiPredicate<String,String> bip = (x,y) -> x.equals(y);

        BiPredicate<String,String> bip1 = String::equals;

        System.out.println(bip.test("sss","sss"));
    }
    //构造器引用
    @Test
    public  void test4(){
        Supplier<Student> sup = () -> new Student();

        //构造器引用方式
        Supplier<Student> sup1 = Student::new;


    }
    //数组引用
    @Test
    public void  test5(){
        Function<Integer,String[]> f1 = String[]::new;

        String[] ff = f1.apply(15);

        System.out.println(ff.length);
    }

}
