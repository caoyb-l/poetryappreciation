package com.poetryappreciation.controller;

import com.google.gson.Gson;
import com.poetryappreciation.service.inter.PoetryMessageService;
import com.poetryappreciation.service.inter.UserFabulousService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//点赞/取消赞
@RestController
@RequestMapping("/userFabulous")
@CrossOrigin
public class UserFabulousController {
    @Resource
    private UserFabulousService userFabulousService;

    //点赞
    @RequestMapping("/addUserFabulous")
    public String addUserFabulous(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("loginName")){
                map.put("loginName",object.getString("loginName"));
            }
            if(object.has("poetryId")){
                map.put("poetryId",object.getString("poetryId"));
            }
            returnMap = userFabulousService.addUserFabulous(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    //点赞
    @RequestMapping("/delUserFabulous")
    public String delUserFabulous(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("loginName")){
                map.put("user",object.getString("loginName"));
            }
            if(object.has("poetryId")){
                map.put("poetry_id",object.getString("poetryId"));
            }
            returnMap = userFabulousService.delUserFabulous(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    //点赞消息记录
    @RequestMapping("/queryUserFabulous")
    public String queryUserFabulous(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("loginName")){
                map.put("loginName",object.getString("loginName"));
            }

            returnMap = userFabulousService.queryUserFabulous(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }
}
