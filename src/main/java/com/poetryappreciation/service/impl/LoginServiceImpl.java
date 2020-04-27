package com.poetryappreciation.service.impl;


import com.google.gson.Gson;
import com.poetryappreciation.mapper.SysRoleResourceNewMapper;
import com.poetryappreciation.mapper.UserMapper;
import com.poetryappreciation.pojo.User;
import com.poetryappreciation.service.inter.LoginService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private SysRoleResourceNewMapper sysRoleResourceMapper;



    @Override
    public String getSysUserByLoginName(Map<String, String> paramMap) {

        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            String userName = paramMap.get("userName");
            String passWord = paramMap.get("password");

            Map<String,Object> param = new HashMap<String,Object>();
            param.put("login_name",userName);
            param.put("pass_word",passWord);
            List<User> list = userMapper.selectByMap(param);


            Map<String,Object> resultMap = new HashMap<String,Object>();

            if(list!=null && list.size()>0){
                returnMap.put("code",0);
                returnMap.put("message","登录成功");
                User user = list.get(0);
                resultMap.put("avatar","/avatar2.jpg");
                resultMap.put("id",user.getId());
                resultMap.put("name",user.getLoginName());
                resultMap.put("password",passWord);
                resultMap.put("roleId",user.getRole());
                resultMap.put("orgId","1");
                resultMap.put("orgName","");
                resultMap.put("telephone",user.getMobile());
                String token = RandomStringUtils.randomAlphanumeric(16);
                resultMap.put("token", token);
                resultMap.put("username",user.getUserName());
                returnMap.put("result",resultMap);
                returnMap.put("userId",user.getId());
            }else{
                returnMap.put("code",1);
                returnMap.put("message","用户名或密码不对");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("code",1);
            returnMap.put("message","用户名或密码不对");
        }

        return gson.toJson(returnMap);
    }

    //获取登录用户角色的菜单信息
    @Override
    public String getRoleMenu(Map<String, String> paramMap) {

        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            String roleId = paramMap.get("roleId");
            Map<String,String> param = new HashMap<String,String>();
            param.put("roleId",roleId);
            List<Map<String,Object>> roleMenulist = sysRoleResourceMapper.getRoleMenu(param);
            if(roleMenulist!=null && roleMenulist.size()>0){
                Map<String,Object> resultMap = new HashMap<String,Object>();

                Map<String,Object> roleMap = new HashMap<String,Object>();

                List<String> permissionList = new ArrayList<String>();

                List<Map<String,Object>> permissions = new ArrayList<Map<String,Object>>();

                for (int j=0;j<roleMenulist.size();j++){
                    boolean newDel = true;
                    Map<String,Object> menulMap = roleMenulist.get(j);
                    //角色所拥有菜单里面的功能处理
                    if(null!=permissions && permissions.size()>0){
                        for(int h = 0;h<permissions.size();h++){
                            Map<String,Object> map = permissions.get(h);
                            //处理已经循环过的菜单
                            if(map.get("permissionId").equals(menulMap.get("name")+"")){
                                List<String> delactionList = (List<String>)map.get("actionList");
                                delactionList.add(menulMap.get("function")+"");
                                map.put("actionList",delactionList);

                                List<Map<String,Object>> dealActionEntitySet  = (List<Map<String,Object>> )map.get("actionEntitySet");
                                Map<String,Object> dealMap = new HashMap<String,Object>();
                                dealMap.put("action",menulMap.get("function")+"");
                                dealMap.put("defaultCheck",false);
                                dealMap.put("describe",menulMap.get("functionName")+"");
                                dealActionEntitySet.add(dealMap);
                                map.put("actionEntitySet",dealActionEntitySet);

                                String dealActions = dealActionEntitySet.toString();
                                map.put("actions",dealActions);
                                permissions.remove(h);
                                permissions.add(map);
                                newDel = false;
                            }
                        }
                    }
                    //处理新循环的菜单
                    if(newDel){
                        permissionList.add(menulMap.get("name")+"");
                        Map<String,Object> permissionMap = new HashMap<String,Object>();
                        permissionMap.put("permissionId",menulMap.get("name")+"");
                        permissionMap.put("permissionName",menulMap.get("descn")+"");
                        permissionMap.put("roleId",roleId);

                        List<String> actionList = new ArrayList<String>();
                        actionList.add(menulMap.get("function")+"");
                        permissionMap.put("actionList",actionList);

                        List<Map<String,Object>> actionEntitySet = new ArrayList<Map<String,Object>>();
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("action",menulMap.get("function")+"");
                        map.put("defaultCheck",false);
                        map.put("describe",menulMap.get("functionName")+"");
                        actionEntitySet.add(map);
                        permissionMap.put("actionEntitySet",actionEntitySet);

                        String actions = actionEntitySet.toString();
                        permissionMap.put("actions",actions);
                        permissions.add(permissionMap);
                    }
                }
                //拼装 role 部分报文
                roleMap.put("id",roleId);
                roleMap.put("permissionList",permissionList);
                roleMap.put("permissions",permissions);
                roleMap.put("status","1");

                //拼装result 部分报文
                resultMap.put("avatar","/avatar2.jpg");
                resultMap.put("id",roleId);
                resultMap.put("role",roleMap);
                resultMap.put("roleId",roleId);

                //拼装返回报文
                returnMap.put("code",0);
                returnMap.put("message","获取角色菜单成功");
                returnMap.put("result",resultMap);
                returnMap.put("timestamp",new Date());
            }else{
                returnMap.put("code",1);
                returnMap.put("message","获取角色菜单异常");
                returnMap.put("result","");
                returnMap.put("timestamp",new Date());
            }
        }catch(Exception e){
            e.printStackTrace();
            returnMap.put("code",1);
            returnMap.put("message","获取角色菜单异常");
            returnMap.put("result","");
            returnMap.put("timestamp",new Date());
        }
        return gson.toJson(returnMap);
    }

}
