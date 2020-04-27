package com.poetryappreciation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.poetryappreciation.mapper.PoetryTypeMapper;
import com.poetryappreciation.pojo.PoetryType;
import com.poetryappreciation.service.inter.PoetryTypeService;
import com.poetryappreciation.util.CommonUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class PoetryTypeServiceImpl implements PoetryTypeService {
    @Resource
    private PoetryTypeMapper poetryTypeMapper;
    @Override
    public List<PoetryType> getPoetryTypeList() {
        List<PoetryType>  poetryTypeList = new ArrayList<PoetryType>();
        try{
            QueryWrapper<PoetryType> poetryTypeQueryWrapper = new QueryWrapper<PoetryType>();
            poetryTypeList = poetryTypeMapper.selectList(poetryTypeQueryWrapper);
        }catch (Exception e){
            e.printStackTrace();
        }
        return poetryTypeList;
    }

    @Override
    public Map<String, Object> getPoetryTypePageList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            int current = Integer.parseInt(paramMap.get("current")) ;//页码
            int size = Integer.parseInt(paramMap.get("size"));//条数
            QueryWrapper<PoetryType> wrapper = new QueryWrapper<PoetryType>();
            if(!CommonUtility.isEmpty(paramMap.get("name"))){
                wrapper.like("name",paramMap.get("name"));
            }
            Page page = new Page(current,size);
            IPage<PoetryType> poetryIPage = poetryTypeMapper.selectPage(page,wrapper);
            List<PoetryType> poetryTypeList = poetryIPage.getRecords();
            Map<String,Object> resultMap = new HashMap<>();
            returnMap.put("code",0);
            returnMap.put("message","查询成功");
            resultMap.put("data",poetryTypeList);
            resultMap.put("pageNo",current);
            resultMap.put("pageSize",size);
            resultMap.put("totalCount",poetryIPage.getTotal());
            resultMap.put("totalPage",poetryIPage.getPages());
            resultMap.put("timestamp",new Date());
            returnMap.put("result",resultMap);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    //添加诗词分类
    @Override
    public Map<String, Object> addPoetryType(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            PoetryType poetryType = new PoetryType();
            poetryType.setName(paramMap.get("name"));
            poetryTypeMapper.insert(poetryType);
            returnMap.put("code",0);
            returnMap.put("message","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    //修改诗词分类
    @Override
    public Map<String, Object> editPoetryType(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            PoetryType poetryType = new PoetryType();
            poetryType.setId(Integer.parseInt(paramMap.get("id")));
            poetryType.setName(paramMap.get("name"));
            poetryTypeMapper.updateById(poetryType);
            returnMap.put("code",0);
            returnMap.put("message","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    //删除诗词分类
    @Override
    public Map<String, Object> delPoetryType(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            poetryTypeMapper.deleteById(paramMap.get("id"));
            returnMap.put("code",0);
            returnMap.put("message","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }
}
