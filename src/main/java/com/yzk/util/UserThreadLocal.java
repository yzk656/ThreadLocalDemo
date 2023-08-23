package com.yzk.util;

import com.yzk.entity.User;

public class UserThreadLocal {
    public UserThreadLocal() {
    }

    //线程变量隔离
    private static final ThreadLocal<User> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 清楚用户信息
     */
    public static void clear(){
        USER_INFO_THREAD_LOCAL.remove();;
    }

    /**
     * 添加用户信息
     * @param user
     */
    public static void set(User user){
        USER_INFO_THREAD_LOCAL.set(user);
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static User getCurrentUser(){
        return USER_INFO_THREAD_LOCAL.get();
    }

}
