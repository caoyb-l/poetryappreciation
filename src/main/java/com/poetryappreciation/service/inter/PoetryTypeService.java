package com.poetryappreciation.service.inter;

import com.poetryappreciation.pojo.PoetryType;

import java.util.List;
import java.util.Map;

public interface PoetryTypeService {

    public List<PoetryType>  getPoetryTypeList();

    //诗词分类列表 分页查询
    public Map<String,Object> getPoetryTypePageList(Map<String,String> paramMap);

    //添加诗词分类
    public Map<String,Object> addPoetryType(Map<String,String> paramMap);

    //修改诗词分类
    public Map<String,Object> editPoetryType(Map<String,String> paramMap);

    //删除诗词分类
    public Map<String,Object> delPoetryType(Map<String,String> paramMap);


}
