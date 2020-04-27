package com.poetryappreciation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.poetryappreciation.mapper.UserMapper;
import com.poetryappreciation.pojo.User;
import com.poetryappreciation.service.inter.UserService;
import com.poetryappreciation.util.CommonUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    //用户信息验证
    @Override
    public Map<String, Object> getUserMsg(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            String requestUrl = paramMap.get("requestUrl");
            String userName = paramMap.get("loginName");
            String passWord = paramMap.get("passWord");
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
            userQueryWrapper.eq("login_name",userName);
            userQueryWrapper.eq("pass_word",passWord);
            List<User> userList = userMapper.selectList(userQueryWrapper);
            if(null!=userList && userList.size()>0){
                User user = userList.get(0);
                returnMap.put("message","用户认证成功");
                returnMap.put("code","0");
                returnMap.put("headUrl",requestUrl+"images/"+user.getHeadUrl()); //头像url
                returnMap.put("userName",user.getUserName()); //用户名
            }else{
                returnMap.put("message","用户名或密码不对");
                returnMap.put("code","1");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnMap;
    }

    //用户注册
    @Override
    public Map<String, Object> addUserMsg(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            QueryWrapper<User> chkUser = new QueryWrapper<User>();
            chkUser.eq("login_name",paramMap.get("loginName"));
            List chkList = userMapper.selectList(chkUser);
            if(chkList!=null && chkList.size()>0){
                returnMap.put("message","用户注册失败，登录账号重复！");
                returnMap.put("code","1");
                return returnMap;
            }

            User user = new User();
            user.setLoginName(paramMap.get("loginName"));
            user.setUserName(paramMap.get("userName"));
            user.setPassword(paramMap.get("passWord"));
            user.setMobile(paramMap.get("mobile"));
            user.setEmail(paramMap.get("email"));
            user.setRole("2");
            userMapper.insert(user);
            returnMap.put("message","用户注册成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","用户注册失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //修改用户信息
    @Override
    public Map<String, Object> chgUserMsg(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            QueryWrapper<User> chkUser = new QueryWrapper<User>();
            chkUser.eq("login_name",paramMap.get("loginName"));
            User user = userMapper.selectOne(chkUser);
            user.setHeadUrl(paramMap.get("url"));
            userMapper.updateById(user);
            returnMap.put("message","success");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    //用户密码修改
    @Override
    public Map<String, Object> chgPwd(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            //更新条件
            UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<User>();
            userUpdateWrapper.eq("login_name",paramMap.get("loginName"));
            //更新值
            User user = new User();
            if(CommonUtility.isEmpty(paramMap.get("passWord"))){
                user.setPassword(paramMap.get("passWord"));
            }
            if(CommonUtility.isEmpty(paramMap.get("userName"))){
                user.setUserName(paramMap.get("userName"));
            }
            if(CommonUtility.isEmpty(paramMap.get("email"))){
                user.setEmail(paramMap.get("email"));
            }
            if(CommonUtility.isEmpty(paramMap.get("mobile"))){
                user.setMobile(paramMap.get("mobile"));
            }
            userMapper.update(user,userUpdateWrapper);
            returnMap.put("message","修改成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","修改失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //用户列表
    @Override
    public Map<String, Object> getUserList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            int current = Integer.parseInt(paramMap.get("current")) ;//页码
            int size = Integer.parseInt(paramMap.get("size"));//条数
            String requestUrl = paramMap.get("requestUrl");
            Page page = new Page(current,size);
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
            IPage<User> userIPage = userMapper.selectPage(page,userQueryWrapper);
            List<User> userList = userIPage.getRecords();
            if(null!=userList){
                for (User obj: userList){
                    obj.setHeadUrl(requestUrl+"images/"+obj.getHeadUrl());
                }
            }

            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("pageNo",current); //当前页码
            resultMap.put("pageSize",size);//查询条数
            resultMap.put("totalCount",page.getTotal());//总记录数
            resultMap.put("totalPage",page.getPages());//总页数
            resultMap.put("data",userList);
            returnMap.put("result",resultMap);
            returnMap.put("code",0);
            returnMap.put("message","查询成功");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","查询失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> delUserMsg(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            userMapper.deleteById(paramMap.get("id"));
            returnMap.put("code",0);
            returnMap.put("message","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }
}
