package com.mxp.java8;

import com.mxp.model.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TestStreamAPI2 {


    //2. 中间操作
    List<Student> students = Arrays.asList(
            new Student(110, "李四", 59, Student.Status.BUSY),
            new Student(101, "张三", 18, Student.Status.FREE),
            new Student(103, "王五", 28, Student.Status.VOCATION),
            new Student(104, "赵六", 8,Student.Status.BUSY)

    );

    /*
      筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void test1() {
        Stream<Student> s1 = students.stream().filter((x) -> x.getAge() > 18);
        s1.forEach(System.out::println);
    }

    @Test
    public void test2() {
        Stream<Student> s2 = students.stream().limit(3).filter((x) -> x.getAge() > 0);
        s2.forEach(System.out::println);
    }

    @Test
    public void test3() {
        Stream<Student> s2 = students.stream().skip(3).filter((x) -> x.getAge() > 0);
        s2.forEach(System.out::println);
    }

    @Test
    public void test4() {
        Stream<Student> s2 = students.stream().skip(3).distinct().filter((x) -> x.getAge() > 0);
        s2.forEach(System.out::println);
    }

    /*
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
    @Test
    public void test5() {
        Stream<Integer> s3 = students.stream().map(x -> x.getAge().toString().length());
        s3.forEach(System.out::println);
    }

    @Test
    public void test6() {
        List<String> list = Arrays.asList("abcd", "eddd", "aad", "dda");
        list.stream()
                .flatMap(TestStreamAPI2::getChar)
                .forEach(System.out::println);
    }

    public static Stream<Character> getChar(String str) {
        List<Character> list = new ArrayList<>();

        for (char ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }

    /*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	*/
    @Test
    public void test7() {
        //自然排序
        List<String> list = Arrays.asList("vvv", "sa", "er", "ab");
        list.stream().sorted().forEach(System.out::println);
        //自定义排序
        List<Student> list1 = new ArrayList<>();
        students.stream().sorted((x, y) -> x.getAge().compareTo(y.getAge())
                //    {
                //           if (x.getAge().equals(y.getAge())) {
                //              return x.getAge().compareTo(y.getAge());
//            }else {
//                return x.getId().compareTo(y.getId());
//            }
                //       }

        ).forEach(System.out::println);
    }


}
