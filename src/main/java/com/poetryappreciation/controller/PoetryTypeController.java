package com.poetryappreciation.controller;

import com.google.gson.Gson;
import com.poetryappreciation.pojo.PoetryType;
import com.poetryappreciation.service.inter.PoetryTypeService;
import com.poetryappreciation.service.inter.UserService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poetryType")
@CrossOrigin
public class PoetryTypeController {
    @Resource
    private PoetryTypeService poetryTypeService;

    //获取登录用户信息
    @RequestMapping("/getPoetryTypeList")
    public String getPoetryTypeList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            List<PoetryType> poetryTypeList = poetryTypeService.getPoetryTypeList();
            returnMap.put("message","success！");
            returnMap.put("code","0");
            returnMap.put("result",poetryTypeList);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //获取诗词分类（分页）
    @RequestMapping("/getPoetryTypePageList")
    public String getPoetryTypePageList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            if(object.has("name")){
                map.put("name",object.getString("name"));
            }
            map.put("current",object.getString("pageNo"));
            map.put("size",object.getString("pageSize"));
            returnMap= poetryTypeService.getPoetryTypePageList(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //添加诗词分类
    @RequestMapping("/addPoetryType")
    public String addPoetryType(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            if(object.has("name")){
                map.put("name",object.getString("name"));
            }
            if(object.has("code")){
                map.put("code",object.getString("code"));
            }
            returnMap= poetryTypeService.addPoetryType(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //修改诗词分类
    @RequestMapping("/editPoetryType")
    public String editPoetryType(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            map.put("id",object.getString("id"));
            if(object.has("name")){
                map.put("name",object.getString("name"));
            }
            if(object.has("code")){
                map.put("code",object.getString("code"));
            }
            returnMap= poetryTypeService.editPoetryType(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //删除诗词分类
    @RequestMapping("/delPoetryType")
    public String delPoetryType(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            map.put("id",object.getString("id"));
            returnMap= poetryTypeService.delPoetryType(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }
}
