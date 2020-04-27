//package com.poetryappreciation.controller;
//
//import com.poetryappreciation.service.inter.RobotInfoService;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * <p>
// *  测试
// * </p>
// */
//@RestController
//@RequestMapping("/test")
//@CrossOrigin
//public class TestController {
//
//    @Resource
//    private RedisTemplate<String,Object> redisTemplate ;
//
//    @Resource
//    private RobotInfoService robotInfoService;
//
//
//    //获取登录用户信息
//    @RequestMapping(value = "/redisTest",method = RequestMethod.GET)
//    public String getUserMsg(HttpServletRequest request, HttpServletResponse response){
//        String path = "{\"code\":105,\"data\":{\"disinfectant\":0}}";
//        robotInfoService.updateRobot(path,"2ba9177aac4ec9c6");
//
//        return path;
//    }
//
//}
//
