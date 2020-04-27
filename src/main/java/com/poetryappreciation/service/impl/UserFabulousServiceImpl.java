package com.poetryappreciation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poetryappreciation.mapper.PoetryMapper;
import com.poetryappreciation.mapper.PoetryMessageMapper;
import com.poetryappreciation.mapper.UserFabulousMapper;
import com.poetryappreciation.pojo.Poetry;
import com.poetryappreciation.pojo.PoetryMessage;
import com.poetryappreciation.pojo.UserFabulous;
import com.poetryappreciation.service.inter.PoetryMessageService;
import com.poetryappreciation.service.inter.UserFabulousService;
import com.poetryappreciation.util.DateUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class UserFabulousServiceImpl implements UserFabulousService {
    @Resource
    private UserFabulousMapper userFabulousMapper;
    @Resource
    private PoetryMapper poetryMapper;


    //用户点赞
    @Override
    public Map<String, Object> addUserFabulous(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            UserFabulous userFabulous = new UserFabulous();
            userFabulous.setUser(paramMap.get("loginName"));
            userFabulous.setPoetryId(paramMap.get("poetryId"));
            userFabulous.setFabulousTime(new Date());
            userFabulous.setFlag("0"); //新消息
            userFabulousMapper.insert(userFabulous);
            returnMap.put("message","点赞成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","点赞失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //取消赞
    @Override
    public Map<String, Object> delUserFabulous(Map<String, Object> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{

            userFabulousMapper.deleteByMap(paramMap);
            returnMap.put("message","取消赞成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","取消赞失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //点赞消息获取
    @Override
    public Map<String, Object> queryUserFabulous(Map<String, Object> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        List<UserFabulous> userFabulousList = new ArrayList<UserFabulous>();
        try{
            String loginName = paramMap.get("loginName")+"";
            QueryWrapper<Poetry> poetryQueryWrapper = new QueryWrapper<Poetry>();
            poetryQueryWrapper.eq("publish_user",loginName);
            List<Poetry> poetryList = poetryMapper.selectList(poetryQueryWrapper);
            List<String> poetryIdList = new ArrayList<String>();
            if(null!=poetryList){
                //获取诗集集合
                for(Poetry obj:  poetryList){
                    poetryIdList.add(obj.getId()+"");
                }

                //查询诗词有哪些赞的消息
                QueryWrapper<UserFabulous> userFabulousQueryWrapper = new QueryWrapper<UserFabulous>();
                userFabulousQueryWrapper.in("poetry_id",poetryIdList);
                userFabulousList = userFabulousMapper.selectList(userFabulousQueryWrapper);

                //更新点赞新旧标识为旧
                if(null != userFabulousList){
                    for(UserFabulous fobj: userFabulousList){
                        if(fobj.getFlag().equals("0")){
                            UserFabulous updateObj = new UserFabulous();
                            updateObj.setId(fobj.getId());
                            updateObj.setFlag("1"); //旧
                            userFabulousMapper.updateById(updateObj);
                        }
                    }
                }
            }
            returnMap.put("result",userFabulousList);
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
