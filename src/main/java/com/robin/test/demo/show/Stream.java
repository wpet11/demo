package com.robin.test.demo.show;

import com.robin.test.demo.entiry.User;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Stream {
    public static void main(String[] args) {

        List<User> list = users();
    }
    public static List<User> users(){
        List<User> list = Arrays.asList(
                new User("李星云", 18, 0, "渝州",new BigDecimal(1000)),
                new User("陆林轩", 16, 1, "渝州",new BigDecimal(500)),
                new User("姬如雪", 17, 1, "幻音坊",new BigDecimal(800)),
                new User("袁天罡", 99, 0, "藏兵谷",new BigDecimal(100000)),
                new User("张子凡", 19, 0, "天师府",new BigDecimal(900)),
                new User("陆佑劫", 45, 0, "不良人",new BigDecimal(600)),
                new User("张天师", 48, 0, "天师府",new BigDecimal(1100)),
                new User("蚩梦", 18, 1, "万毒窟",new BigDecimal(800))
        );
        return list;
    }
}
