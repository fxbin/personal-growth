package cn.fxbin.record.spring.javaconfig.service;

import cn.fxbin.record.spring.javaconfig.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * UserService
 *
 * @author fxbin
 * @version v1.0
 * @since 2020/7/25 11:15
 */
public class UserService {

    public List<User> list() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User(i, "name" + i, "password" + i);
            userList.add(user);
        }
        return userList;
    }

}
