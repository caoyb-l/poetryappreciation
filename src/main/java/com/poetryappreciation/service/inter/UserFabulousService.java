package com.poetryappreciation.service.inter;

import java.util.Map;

public interface UserFabulousService {

    //点赞
    public Map<String,Object> addUserFabulous(Map<String, String> paramMap);

    //取消点赞
    public Map<String,Object> delUserFabulous(Map<String, Object> paramMap);

    //点赞消息查询
    public Map<String,Object> queryUserFabulous(Map<String, Object> paramMap);

}
