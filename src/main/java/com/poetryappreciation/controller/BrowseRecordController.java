package com.poetryappreciation.controller;

import com.google.gson.Gson;
import com.poetryappreciation.service.inter.BrowseRecordService;
import com.poetryappreciation.service.inter.PoetryMessageService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/browseRecord")
@CrossOrigin
public class BrowseRecordController {
    @Resource
    private BrowseRecordService browseRecordService;

    //用户浏览记录
    @RequestMapping("/queryBrowseRecordList")
    public String queryBrowseRecordList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("loginName")){
                map.put("loginName",object.getString("loginName"));
            }
            returnMap = browseRecordService.queryBrowseRecordList(map);

        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

}
