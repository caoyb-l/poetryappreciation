package com.poetryappreciation.service.inter;

import java.util.Map;

//登录服务
public interface LoginService {

    //获取登录用户信息
    String getSysUserByLoginName(Map<String, String> paramMap);

    //获取登录用户角色的菜单信息
    String getRoleMenu(Map<String, String> paramMap);
}
