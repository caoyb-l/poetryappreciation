package com.poetryappreciation.controller;

import com.poetryappreciation.service.inter.LoginService;
import com.poetryappreciation.util.CommonUtility;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;


    //获取登录用户信息
    @RequestMapping("/getUserMsg")
    @ResponseBody
    public String getUserMsg(HttpServletRequest request, HttpServletResponse response,@RequestBody String param){

        System.out.println("请求参数："+param);
        JSONObject object = JSONObject.fromObject(param);

        String userName = object.getString("username");
        String password = object.getString("password");
        if(CommonUtility.isEmpty(userName)||CommonUtility.isEmpty(password)){
            Map<String,Object> failMap = new HashMap<String,Object>();
            failMap.put("code",1);
            failMap.put("message","用户名或密码为空！");
            return failMap.toString();
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("userName",userName);
        map.put("password",password);
        String returnStr = loginService.getSysUserByLoginName(map);

        return returnStr;
    }

    //获取登录角色的菜单列表
    @RequestMapping("/getRoleMenu")
    public String getRoleMenu(HttpServletRequest request, HttpServletResponse response,@RequestBody String param){
        System.out.println("请求参数："+param);
        JSONObject object = JSONObject.fromObject(param);
        String roleId = object.getString("roleId");
        Map<String,String> map = new HashMap<String,String>();
        map.put("roleId",roleId);
        String returnStr = loginService.getRoleMenu(map);
        return returnStr;
    }


}

