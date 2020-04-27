package com.poetryappreciation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.poetryappreciation.mapper.PoetryMessageMapper;
import com.poetryappreciation.mapper.UserCollectionMapper;
import com.poetryappreciation.pojo.PoetryMessage;
import com.poetryappreciation.pojo.UserCollection;
import com.poetryappreciation.service.inter.PoetryMessageService;
import com.poetryappreciation.service.inter.UserCollectionService;
import com.poetryappreciation.util.CommonUtility;
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
public class PoetryMessageServiceImpl implements PoetryMessageService {
    @Resource
    private PoetryMessageMapper poetryMessageMapper;


    //查询诗词列表
    @Override
    public Map<String, Object> addMessage(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            PoetryMessage poetryMessage = new PoetryMessage();
            poetryMessage.setUser(paramMap.get("loginName"));
            poetryMessage.setPoetryId(paramMap.get("poetryId"));
            poetryMessage.setMessage(paramMap.get("message"));
            poetryMessage.setMessageTime(new Date());
            poetryMessageMapper.insert(poetryMessage);

            returnMap.put("message","留言成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","留言失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //留言列表
    @Override
    public Map<String, Object> getPoetryMessagePageList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            System.out.println("参数："+paramMap);
            int current = Integer.parseInt(paramMap.get("current")) ;//页码
            int size = Integer.parseInt(paramMap.get("size"));//条数
            QueryWrapper<PoetryMessage> wrapper = new QueryWrapper<PoetryMessage>();
            if(!CommonUtility.isEmpty(paramMap.get("name"))){
                wrapper.like("user",paramMap.get("name")).or().like("message",paramMap.get("name"));
            }
            if(!CommonUtility.isEmpty(paramMap.get("poetryId"))){
                wrapper.eq("poetry_id",paramMap.get("poetryId"));
            }
            Page page = new Page(current,size);
            IPage<PoetryMessage> poetryMessageIPage = poetryMessageMapper.selectPage(page,wrapper);
            List<PoetryMessage> poetryMessageList = poetryMessageIPage.getRecords();
            for(PoetryMessage obj:poetryMessageList){
                obj.setMessageTimeStr(DateUtility.getFormatDate(obj.getMessageTime(),DateUtility.DateFormat4));
            }
            Map<String,Object> resultMap = new HashMap<>();
            returnMap.put("code",0);
            returnMap.put("message","查询成功");
            resultMap.put("data",poetryMessageList);
            resultMap.put("pageNo",current);
            resultMap.put("pageSize",size);
            resultMap.put("totalCount",poetryMessageIPage.getTotal());
            resultMap.put("totalPage",poetryMessageIPage.getPages());
            resultMap.put("timestamp",new Date());
            returnMap.put("result",resultMap);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> delPoetryMessage(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            poetryMessageMapper.deleteById(paramMap.get("id"));
            returnMap.put("code",0);
            returnMap.put("message","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

}
