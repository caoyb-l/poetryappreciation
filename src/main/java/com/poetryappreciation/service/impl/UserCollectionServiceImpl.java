package com.poetryappreciation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.poetryappreciation.mapper.*;
import com.poetryappreciation.pojo.*;
import com.poetryappreciation.service.inter.PoetryService;
import com.poetryappreciation.service.inter.UserCollectionService;
import com.poetryappreciation.util.DateUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserCollectionServiceImpl implements UserCollectionService {
    @Resource
    private UserCollectionMapper userCollectionMapper;


    //查询诗词列表
    @Override
    public Map<String, Object> addCollection(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            UserCollection userCollection = new UserCollection();
            userCollection.setUser(paramMap.get("loginName"));
            userCollection.setPoetryId(paramMap.get("poetryId"));
            userCollection.setCollectionTime(new Date());
            userCollectionMapper.insert(userCollection);

            returnMap.put("message","收藏成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","收藏失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> delCollection(Map<String, Object> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            userCollectionMapper.deleteByMap(paramMap);
            returnMap.put("message","取消收藏成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","取消收藏失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //收藏列表查询
    @Override
    public Map<String, Object> queryCollectionList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            List<Map<String,Object>> userCollectionList = userCollectionMapper.queryCollectionList(paramMap);
            if(null!=userCollectionList ){
                for(Map<String,Object> obj: userCollectionList){
                    obj.put("collectionTime", DateUtility.getFormatDate((Date) obj.get("collectionTime"),DateUtility.DateFormat4));
                }
            }
            returnMap.put("result",userCollectionList);
            returnMap.put("message","查询成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","查询失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }


}
