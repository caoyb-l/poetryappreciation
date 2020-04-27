package com.poetryappreciation.service.inter;

import java.util.Map;

public interface PoetryMessageService {

    //新增留言
    public Map<String,Object> addMessage(Map<String, String> paramMap);


    //诗词列表 分页查询
    public Map<String,Object> getPoetryMessagePageList(Map<String,String> paramMap);


    //删除留言
    public Map<String,Object> delPoetryMessage(Map<String,String> paramMap);

}
