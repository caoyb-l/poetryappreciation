package com.poetryappreciation.controller;

import com.google.gson.Gson;
import com.poetryappreciation.service.inter.UserService;
import com.poetryappreciation.util.CommonUtility;
import com.poetryappreciation.util.FileUtility;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/userManage")
@CrossOrigin
public class UserManageController {
    @Resource
    private UserService userService;

    @Value("${upload.url}")
    private String url;

    //app获取登录用户信息
    @RequestMapping("/queryUserMsg")
    public String getUserMsg(@RequestBody String param,HttpServletRequest request){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            System.out.println("请求参数："+param);
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            map.put("requestUrl",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/");//请求url
            if(CommonUtility.isEmpty(object.getString("loginName"))){
                returnMap.put("message","用户名不能为空！");
                returnMap.put("code","1");
            }
            if(CommonUtility.isEmpty(object.getString("passWord"))){
                returnMap.put("message","密码不能为空！");
                returnMap.put("code","1");
            }

            map.put("loginName",object.getString("loginName"));
            map.put("passWord",object.getString("passWord"));
            returnMap = userService.getUserMsg(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","用户信息验证异常！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //获取登录用户信息
    @RequestMapping("/addUserMsg")
    public String addUserMsg(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            System.out.println("请求参数："+param);
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            if(object.has("loginName")){
                map.put("loginName",object.getString("loginName"));
            }
            if(object.has("userName")){
                map.put("userName",object.getString("userName"));
            }
            if(object.has("passWord")){
                map.put("passWord",object.getString("passWord"));
            }
            if(object.has("mobile")){
                map.put("mobile",object.getString("mobile"));
            }
            if(object.has("email")){
                map.put("email",object.getString("email"));
            }
            returnMap = userService.addUserMsg(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","用户信息验证异常！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //密码修改
    @RequestMapping("/chgPwd")
    public String chgPwd(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            System.out.println("请求参数："+param);
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            map.put("loginName",object.getString("loginName"));
            if(object.has("passWord")){
                map.put("passWord",object.getString("passWord"));
            }
            if(object.has("userName")){
                map.put("userName",object.getString("userName"));
            }
            if(object.has("mobile")){
                map.put("mobile",object.getString("mobile"));
            }
            if(object.has("email")){
                map.put("email",object.getString("email"));
            }
            returnMap = userService.chgPwd(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","修改异常！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //用户列表
    @RequestMapping("/getUserList")
    public String getUserList(@RequestBody String param,HttpServletRequest request){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            System.out.println("请求参数："+param);
            Map<String,String> map = new HashMap<String,String>();
            JSONObject object = JSONObject.fromObject(param);
            map.put("requestUrl",request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/");//请求url
            map.put("current",object.getString("pageNo"));
            map.put("size",object.getString("pageSize"));
            returnMap = userService.getUserList(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","查询成功！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    //删除诗词分类
    @RequestMapping("/delUserMsg")
    public String delUserMsg(@RequestBody String param){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        try{
            JSONObject object = JSONObject.fromObject(param);
            Map<String,String> map = new HashMap<String,String>();
            map.put("id",object.getString("id"));
            returnMap= userService.delUserMsg(map);
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","error！");
            returnMap.put("code","1");
        }

        return gson.toJson(returnMap);
    }

    @RequestMapping("/uploadHeadPic")
    public String uploadHeadPic (@RequestParam("file") MultipartFile reqFile, HttpServletRequest request) throws Exception{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Gson gson = new Gson();
        //人脸图片上传路径
//        String url = "/Users/cyb/work/KaiFa/webdata/poetry/";
        if (reqFile.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String nowTime = System.currentTimeMillis()+"";// 按照时间重命名上传的文件
        String newFileName = "";
        try {
            //文件原始名称
            String fileName = reqFile.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//文件扩展名
            newFileName = nowTime+suffixName;//新文件名
            FileUtility.fileUpload(url,newFileName,reqFile);
            //上传成功后修改用户表信息
            Map<String,String> paramMap = new HashMap<String,String>();
            paramMap.put("loginName",request.getParameter("loginName"));
            paramMap.put("url",newFileName);
            userService.chgUserMsg(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            returnMap.put("code",1);
            returnMap.put("message","上传失败");
            return  gson.toJson(returnMap);
        }
        returnMap.put("code",0);
        returnMap.put("message","上传成功");
        returnMap.put("fileName",newFileName);
        return  gson.toJson(returnMap);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response){
        return url;
    }

}
