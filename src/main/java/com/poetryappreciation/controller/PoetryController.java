package com.poetryappreciation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.poetryappreciation.service.inter.PoetryMessageService;
import com.poetryappreciation.service.inter.PoetryService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poetry")
@CrossOrigin
public class PoetryController {
    @Resource
    private PoetryService poetryService;
    @Value("${upload.url}")
    private String path;
    //查询诗词列表
    @RequestMapping("/queryPoetryList")
    public String queryPoetryList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("param")){
                map.put("param",object.getString("param"));
            }
            if(object.has("poetryType")){
                map.put("poetryType",object.getString("poetryType"));
            }
            returnMap = poetryService.queryPoetryList(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    //查询诗词详情
    @RequestMapping("/queryPoetryDetail")
    public String queryPoetryDetail(@RequestBody String param) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Gson gson = new Gson();
        System.out.println("参数：" + param);
        try {
            Map<String, String> map = new HashMap<String, String>();
            JSONObject object = JSONObject.fromObject(param);
            if (object.has("loginName")) {
                map.put("loginName", object.getString("loginName"));
            }
            if (object.has("poetryId")) {
                map.put("poetryId", object.getString("poetryId"));
            }
            returnMap = poetryService.queryPoetryDetail(map);

        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("message", "error！");
            returnMap.put("code", "1");
        }
        return gson.toJson(returnMap);
    }

    //诗词精选 top10
    @RequestMapping("/queryChoicePoetryList")
    public String queryChoicePoetryList(@RequestBody String param) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Gson gson = new Gson();
        System.out.println("参数：" + param);
        try {
            Map<String, String> map = new HashMap<String, String>();
            returnMap = poetryService.queryChoicePoetryList(map);
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("message", "error！");
            returnMap.put("code", "1");
        }
        return gson.toJson(returnMap);
    }

    @RequestMapping("/queryPoetryPageList")
    public String queryPoetryPageList(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            if(object.has("name")){
                map.put("name",object.getString("name"));
            }
            if(object.has("poetryType")){
                map.put("poetryType",object.getString("poetryType"));
            }
            map.put("current",object.getString("pageNo"));
            map.put("size",object.getString("pageSize"));
            returnMap = poetryService.queryPoetryPageList(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    @RequestMapping("/addPoetry")
    public String addPoetry(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            if(object.has("name")){
                map.put("name",object.getString("name"));
            }
            if(object.has("type")){
                map.put("type",object.getString("type"));
            }
            if(object.has("annotation")){
                map.put("annotation",object.getString("annotation"));
            }
            if(object.has("translate")){
                map.put("translate",object.getString("translate"));
            }
            if(object.has("content")){
                map.put("content",object.getString("content"));
            }
            if(object.has("author")){
                map.put("author",object.getString("author"));
            }
            if(object.has("authorDesc")){
                map.put("authorDesc",object.getString("authorDesc"));
            }
            if(object.has("publishUser")){
                map.put("publishUser",object.getString("publishUser"));
            }
            if(object.has("fileUrl")){
                map.put("fileUrl",object.getString("fileUrl"));
            }
            returnMap = poetryService.addPoetry(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","添加失败！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    @RequestMapping("/editPoetry")
    public String editPoetry(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            map.put("id",object.getString("id"));
            if(object.has("name")){
                map.put("name",object.getString("name"));
            }
            if(object.has("type")){
                map.put("type",object.getString("type"));
            }
            if(object.has("annotation")){
                map.put("annotation",object.getString("annotation"));
            }
            if(object.has("translate")){
                map.put("translate",object.getString("translate"));
            }
            if(object.has("content")){
                map.put("content",object.getString("content"));
            }
            if(object.has("author")){
                map.put("author",object.getString("author"));
            }
            if(object.has("authorDesc")){
                map.put("authorDesc",object.getString("authorDesc"));
            }
            if(object.has("fileUrl")){
                map.put("fileUrl",object.getString("fileUrl"));
            }
            returnMap = poetryService.editPoetry(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","修改失败！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    @RequestMapping("/delPoetry")
    public String delPoetry(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        System.out.println("参数："+param);
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            map.put("id",object.getString("id"));
            returnMap = poetryService.delPoetry(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","删除失败！");
            returnMap.put("code","1");
        }
        return gson.toJson(returnMap);
    }

    //诗词音频上传
    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile reqFile, HttpServletRequest request){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        OutputStream out = null;
        //获取文件上传路径
        if (reqFile.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String nowTime = System.currentTimeMillis()+"";// 按照时间重命名上传的文件
        String newFileName = ""; //文件名称
        String httpPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/images/" ; //访问路径
        try {
            //上传路径
            //文件原始名称
            String fileName = reqFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//文件扩展名
            newFileName = nowTime+suffixName;//新文件名
            //获取文件字节数组
            byte [] bytes = reqFile.getBytes();
            File pfile = new File(path);
            //判断文件夹是否存在
            if(!pfile.exists()){
                //不存在时,创建文件夹
                pfile.mkdirs();
            }
            //创建文件
            File file = new File(pfile, newFileName);
            //写入指定文件夹
            out = new FileOutputStream(file);
            out.write(bytes);
            out.close() ;
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("code",1);
            returnMap.put("message","上传失败");
            return  gson.toJson(returnMap);
        }finally {
            if(null!=out){
                try{
                    out.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        returnMap.put("code",0);
        returnMap.put("message","上传成功");
        returnMap.put("fileName",httpPath + newFileName);
        return  gson.toJson(returnMap);
    }
}
