package com.mxp.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 *      void accept(T t);
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 * Function<T,R> : 函数型接口
 *      R apply(T t);
 *
 * Predicate<T> : 断言型接口
 *      boolean test(T t);
 *
 */

public class LambdaInterface {
    //Predicate断言型接口
    @Test
    public void test4(){
        List<String> list = Arrays.asList("11-D","DDDD","12-A","13-W","SSW");
        List<String> stringList = filterStr(list,(strs) -> strs.indexOf("-") != -1);
        for (String str : stringList){
            System.out.println(str);
        }

    }


    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> stringList = new ArrayList<>();

        for (String str: list) {
            if (pre.test(str)){
                stringList.add(str);
            }
        }
        return stringList;
    }

    //Function函数型接口
    @Test
    public void test3(){
        String newStr = strHandler("   \t去掉首尾空格\t\t",(str) -> str.trim());
        System.out.println(newStr);
    }

    public String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }

    //供给型接口Supplier
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 1000));

        for (Integer i:numList) {
            System.out.println(i);
        }
    }
    public List<Integer> getNumList(int num, Supplier<Integer> sup){

        List<Integer> list = new ArrayList<>();

        for (int i = 0;i < num ;i++){
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    //Consumer 消费型接口
    @Test
    public void test1(){
        conMethod(600.00,(m) -> System.out.println("测试"+m));
    }
    public void conMethod(double d, Consumer<Double> Con){
        Con.accept(d);
    }
}
