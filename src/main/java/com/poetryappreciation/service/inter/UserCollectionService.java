package com.poetryappreciation.service.inter;

import java.util.Map;

public interface UserCollectionService {

    //用户诗词收藏
    public Map<String,Object> addCollection(Map<String, String> paramMap);


    //删除诗词收藏
    public Map<String,Object> delCollection(Map<String, Object> paramMap);

    //用户诗词收藏
    public Map<String,Object> queryCollectionList(Map<String, String> paramMap);

}
