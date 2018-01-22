package com.example;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyang on 2018/1/9.
 */
public class Test {
    public static void main(String[] args) {
        Map<String, Object> items = new HashedMap();
        items.put("A", 20);
        items.put("B", 40);
        items.put("C", 50);
        items.put("D", 55);
        items.put("E", 66);

        items.forEach((k, v) -> {
            System.out.println("Item: " + k + ", count: " + v);
            if ("E".equals(k)) {
                System.out.println("Hello E");
            }
        });

        List<String> list = new ArrayList<>();
        list.add("你好");
        list.add("神经病");
        list.add("好人");
        list.add("善行");

        list.forEach(l -> {
            System.out.println(l);
        });

        Arrays.asList("a", "b", "c", "z").forEach(a -> {
            System.out.println(a);
        });
    }
}
