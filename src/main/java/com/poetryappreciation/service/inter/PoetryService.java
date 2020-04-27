package com.poetryappreciation.service.inter;

import java.util.Map;

public interface PoetryService {

    //诗词列表
    public Map<String,Object> queryPoetryList(Map<String,String> paramMap);

    //诗词详情
    public Map<String,Object> queryPoetryDetail(Map<String,String> paramMap);

    //精选诗词列表
    public Map<String,Object> queryChoicePoetryList(Map<String,String> paramMap);


    //诗词列表 分页查询
    public Map<String,Object> queryPoetryPageList(Map<String,String> paramMap);

    //添加诗词
    public Map<String,Object> addPoetry(Map<String,String> paramMap);

    //修改诗词
    public Map<String,Object> editPoetry(Map<String,String> paramMap);

    //删除诗词
    public Map<String,Object> delPoetry(Map<String,String> paramMap);



}
