package com.mxp.java8;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream 的三个操作步骤：
 *
 *  1.创建 Stream
 *
 *  2.中间操作
 *
 *  3.终止操作
 */
public class TestSTtreamAPI {
    //创建Stream
    @Test
    public void test1(){
        //1.可以通过Collection 系列集合提供的stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        list.add("xy");
        Stream<String> s1 = list.stream();
        s1.forEach((x) -> System.out.println(x));

        //2.通过Arrays中的静态方法stream() 获取数据流
        String[] sz = new String[10];
        Stream<String> s2 = Arrays.stream(sz);

        //3.通过Stream类中的静态方法of()
        Stream<String> s3 = Stream.of("a","b","c");

        //4.创建无限流
        //迭代
        Stream<Integer> s4 = Stream.iterate(0,(x) -> x+2);
        s4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random()).limit(15).forEach(System.out::println);
    }
}
