package com.poetryappreciation.service.inter;

import java.util.Map;

public interface UserService {

    //获取用户信息，登录认证
    public Map<String,Object> getUserMsg(Map<String,String> paramMap);

    //新增用户信息（注册）
    public Map<String,Object> addUserMsg(Map<String,String> paramMap);

    //修改用户信息
    public Map<String,Object> chgUserMsg(Map<String,String> paramMap);

    //用户密码修改
    public Map<String,Object> chgPwd(Map<String,String> paramMap);

    //用户列表
    public Map<String,Object> getUserList(Map<String,String> paramMap);

    //删除诗词
    public Map<String,Object> delUserMsg(Map<String,String> paramMap);


}
