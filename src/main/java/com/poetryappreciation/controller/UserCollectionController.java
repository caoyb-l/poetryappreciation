package com.poetryappreciation.controller;

import com.google.gson.Gson;
import com.poetryappreciation.pojo.UserCollection;
import com.poetryappreciation.service.inter.PoetryMessageService;
import com.poetryappreciation.service.inter.UserCollectionService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userCollection")
@CrossOrigin
public class UserCollectionController {
    @Resource
    private UserCollectionService userCollectionService;

    //添加诗词收藏
    @RequestMapping("/addCollection")
    public String addCollection(@RequestBody String param){
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
            returnMap = userCollectionService.addCollection(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    //取消诗词收藏
    @RequestMapping("/delCollection")
    public String delCollection(@RequestBody String param){
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
            returnMap = userCollectionService.delCollection(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    //查询诗词收藏列表
    @RequestMapping("/queryCollectionList")
    public String queryCollectionList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("loginName")){
                map.put("loginName",object.getString("loginName"));
            }
            returnMap = userCollectionService.queryCollectionList(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }
}
