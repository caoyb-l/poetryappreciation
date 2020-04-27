package com.poetryappreciation.util;

public class HttpClientDemo {
    public static void main(String[] args) throws Exception {



//        //登录
//        String url = "http://localhost:12345/userManage/queryUserMsg";
//        String param = "{\"loginName\":\"admin\",\"passWord\":\"123456\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

////        //注册
//        String url = "http://localhost:8088/userManage/addUserMsg";
//        String param = "{\"loginName\":\"bbbb\",\"passWord\":\"123456\",\"userName\":\"用户名\",\"mobile\":\"电话\",email:\"邮箱\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);


        //密码修改
//        String url = "http://localhost:12345/userManage/chgPwd";
//        String param = "{\"loginName\":\"admin\",\"passWord\":\"654321\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //诗词列表获取
//        String url = "http://localhost:12345/poetry/queryPoetryList";
//        String param = "{\"param\":\"你好\",\"poetryType\":\"1\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //诗词详情
//        String url = "http://localhost:8088/poetry/queryPoetryDetail";
//        String param = "{\"loginName\":\"admin\",\"poetryId\":\"1\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //诗词精选 top10
//        String url = "http://localhost:12345/poetry/queryChoicePoetryList";
//        String param = "{}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //诗词收藏
//        String url = "http://106.14.136.38:8088/userCollection/queryCollectionList";
//        String param = "{\"loginName\":\"admin\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //留言接口
//        String url = "http://localhost:12345/poetryMessage/addPoetryMessage";
//        String param = "{\"loginName\":\"admin\",\"message\":\"留言\",\"poetryId\":\"1\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

//        //点赞
//        String url = "http://localhost:12345/userFabulous/addUserFabulous";
//        String param = "{\"loginName\":\"admin\",\"poetryId\":\"1\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //取消赞
//        String url = "http://localhost:12345/userFabulous/delUserFabulous";
//        String param = "{\"loginName\":\"admin\",\"poetryId\":\"1\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //取消赞
//        String url = "http://localhost:12345/userFabulous/queryUserFabulous";
//        String param = "{\"loginName\":\"admin\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        // 浏览记录
//        String url = "http://localhost:8088/browseRecord/queryBrowseRecordList";
//        String param = "{\"loginName\":\"admin\"}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //诗词列表获取
//        String url = "http://localhost:8088/poetryType/getPoetryTypeList";
//        String param = "{}";
//        String returnMsg = HttpClientUtil.httpPost(url,param);
//        System.out.println(returnMsg);

        //取消收藏
        String url = "http://localhost:8088/userCollection/delCollection";
        String param = "{\"loginName\":\"admin\",\"poetryId\":\"1\"}";
        String returnMsg = HttpClientUtil.httpPost(url,param);
        System.out.println(returnMsg);

    }
}
