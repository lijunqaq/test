package com.lj.redisson;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 功能描述
 *
 * @author Lj
 * @date 2019/11/4
 */
public class Eight {
    public static void main(String[] args) {
        Supplier<Test1> aNew = Test1::new;
        Test1 test1 = aNew.get();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.forEach(test1::test);


        List<User> listUser = new ArrayList<>();

        listUser.add(new User("里斯1", 1, "1"));
        listUser.add(new User("里斯2", 2, "3"));
        listUser.add(new User("里斯3", 1, "1"));
        listUser.add(new User("里斯4", 2, "4"));
        listUser.add(new User("里斯5", 1, "5"));
        listUser.add(new User("里斯6", 3, "1"));

        List<User> returnList = new ArrayList<>();


        //分组 根据id去重  再去循环将某个属性拼接 返回新得对象  ifPresent判断是否为空
        listUser.stream().collect(
                Collectors.groupingBy(User::getId)).forEach((id, userList) -> {
            userList.stream().reduce((a, b) -> {
                return new User(a.getName(), a.getId(), a.getType() + b.getType());
            }).ifPresent(returnList::add);
        });
        System.out.println(returnList);


        try {
            Thread.sleep(100000000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
