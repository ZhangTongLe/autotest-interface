package com.example;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by liuyang on 2018/1/10.
 */
public class Car {

    public static void main(String[] args) {
        /*Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set ? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey stranger!"));
*/
        Optional<String> name = Optional.of("Sanaulla");
        Optional empty = Optional.ofNullable(null);
        if (name.isPresent()) {
            System.out.println(name.get());
        }

        try {
            System.out.println(empty.get());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        name.ifPresent((value) -> {
            System.out.println("The length of the value is : " + value);
        });

        System.out.println(empty.orElse("There is no value present"));
        System.out.println(name.orElse("There is some value"));

        System.out.println(empty.orElseGet(() -> "Default value"));
        System.out.println(name.orElseGet(() -> "Default value"));

        try {
            //orElesThrow与orElse方法类似， 区别在于返回值
            //orElseThrow抛出由传入的lambda表达式/方法生成异常
            //empty.orElseThrow(ValueException::new);
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }

        //map方法通过传入的lambda表达式修改Optional实例默认值
        //lambda表达式返回值会包装Optional实例
        Optional<String> upperName = name.map((val) -> val.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        //flatMap与map(Function)非常相似，区别在于lambda表达式的返回值
        //map方法的lambda表达式返回值可以是任何类型，但是返回值会包装成Optional实例
        //但是flatMap方法的lambda返回值总是Optional类型
        upperName = name.flatMap((val) -> Optional.of(val.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));

        Optional<String> longName = name.filter((val) -> val.length() > 6);
        System.out.println(longName.orElse("The name is less than 6 characters"));

        Optional<String> anotherName = Optional.of("Sana");
        Optional<String> shortName = anotherName.filter((val) -> val.length() > 6);
        System.out.println(shortName.orElse("The name is less than 6 characters"));


        //name = Optional.ofNullable(null);
        System.out.println(name.isPresent());
        //System.out.println(name.get());
        System.out.println(name.orElse("There is no value"));



        name.ifPresent((value) -> {
            System.out.println("value = " + value);
            System.out.println("The length of the value is : " + value.length());
        });
    }

}
