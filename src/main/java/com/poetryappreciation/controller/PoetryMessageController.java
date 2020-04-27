package com.poetryappreciation.controller;

import com.google.gson.Gson;
import com.poetryappreciation.pojo.PoetryType;
import com.poetryappreciation.service.inter.PoetryMessageService;
import com.poetryappreciation.service.inter.PoetryTypeService;
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
@RequestMapping("/poetryMessage")
@CrossOrigin
public class PoetryMessageController {
    @Resource
    private PoetryMessageService poetryMessageService;

    //留言接口
    @RequestMapping("/addPoetryMessage")
    public String addPoetryTypeList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("loginName")){
                map.put("loginName",object.getString("loginName"));
            }
            if(object.has("message")){
                map.put("message",object.getString("message"));
            }
            if(object.has("poetryId")){
                map.put("poetryId",object.getString("poetryId"));
            }
            returnMap = poetryMessageService.addMessage(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }
    //获取诗词分类（分页）
    @RequestMapping("/getPoetryMessagePageList")
    public String getPoetryMessagePageList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            if(object.has("name")){
                map.put("name",object.getString("name"));
            }
            if(object.has("poetryId")){
                map.put("poetryId",object.getString("poetryId"));
            }
            map.put("current",object.getString("pageNo"));
            map.put("size",object.getString("pageSize"));
            returnMap= poetryMessageService.getPoetryMessagePageList(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //删除留言
    @RequestMapping("/delPoetryMessage")
    public String delPoetryMessage(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            map.put("id",object.getString("id"));
            returnMap= poetryMessageService.delPoetryMessage(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }
}
