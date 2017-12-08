package com.mxp.java8;

import com.mxp.model.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestStreamAPI3 {

    //2. 终止操作
    List<Student> students = Arrays.asList(
            new Student(110, "李四", 59, Student.Status.BUSY),
            new Student(101, "张三", 18, Student.Status.FREE),
            new Student(103, "王五", 28, Student.Status.VOCATION),
            new Student(104, "赵六", 8,Student.Status.BUSY)

    );

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test1(){
        //匹配所有元素
        Boolean b1 = students.stream().allMatch((e) -> e.getStatus().equals(Student.Status.BUSY));
        System.out.println(b1);

        //至少匹配一个
        Boolean b2 = students.stream().anyMatch((e) -> e.getStatus().equals(Student.Status.BUSY));
        System.out.println(b2);

        //检查没有匹配元素
        Boolean b3 = students.stream().noneMatch((e) -> e.getStatus().equals(Student.Status.BUSY));
        System.out.println(b3);

        //返回第一个元素
        Optional<Student> op = students.stream().sorted((x, y) -> x.getAge().compareTo(y.getAge())).findFirst();

        System.out.println(op.get());

        //返回当前流中的任意元素
        //stream()串行流 parallelStream()并行流
        Optional<Student> op1 = students.parallelStream().filter((x) -> x.getStatus().equals(Student.Status.BUSY)).findAny();

        System.out.println(op1.get());

        //返回流中元素的总个数

        long count = students.stream().count();

        System.out.println(count);

        //返回流中最大值

        Optional<Student> op2 = students.stream().max((x, y) -> x.getAge().compareTo(y.getAge()));

        System.out.println(op2.get().getAge());

        //返回流中最小值
        Optional<Integer> op3 = students.stream().map(Student::getAge).min(Double::compare);

        System.out.println(op3.get());

    }
    /*
    归约
    reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
    */
    @Test
    public void test2(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11);

        Integer l1 = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(l1);

        Optional<Integer> reduce = students.stream().map(Student::getAge).reduce(Integer::sum);
        System.out.println(reduce.get());

    }
    /*
      收集
      collect - 将流转换为其他形式。接受一个Collector接口的实现，用于给Stream中元素做汇总方法
     */
    //分区
    @Test
    public void test6(){

    }

    //多级分组
    @Test
    public void test5(){
        Map<Student.Status, Map<String, List<Student>>> s1 = students.stream().collect(Collectors.groupingBy(Student::getStatus, Collectors.groupingBy((e) -> {
            if ((e).getAge() > 10) {
                return "少年";
            } else if ((e).getAge() < 50) {
                return "青年";
            } else {
                return "老年";
            }
        })));
        System.out.println(s1);

    }
    //分组
    @Test
    public void test4(){
        Map<Student.Status, List<Student>> s1 = students.stream().collect(Collectors.groupingBy((x) -> x.getStatus()));
        System.out.println(s1);
    }

    @Test
    public void test3(){
        List<String> list = students.stream()
                                    .map(Student::getName)
                                    .collect(Collectors.toList());
        list.forEach(System.out::println);
        //总数
        Long count = students.stream().collect(Collectors.counting());
        System.out.println(count);
        //平均值
        Double s1 = students.stream().collect(Collectors.averagingInt(Student::getAge));
        System.out.println(s1);
        //总和
        Integer s2 = students.stream().collect(Collectors.summingInt(Student::getAge));
        System.out.println(s2);
        //最大值
        Optional<Student> s3 = students.stream().collect(Collectors.maxBy((x, y) -> x.getAge().compareTo(y.getAge())));
        System.out.println(s3.get().getAge());

    }
}
