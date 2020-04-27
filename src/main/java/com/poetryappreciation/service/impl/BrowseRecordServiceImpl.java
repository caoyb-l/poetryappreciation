package com.poetryappreciation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.poetryappreciation.mapper.BrowseRecordMapper;
import com.poetryappreciation.pojo.BrowseRecord;
import com.poetryappreciation.service.inter.BrowseRecordService;
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
public class BrowseRecordServiceImpl implements BrowseRecordService {
    @Resource
    private BrowseRecordMapper browseRecordMapper;


    //查询诗词列表
    @Override
    public Map<String, Object> queryBrowseRecordList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            QueryWrapper<BrowseRecord> browseRecordQueryWrapper = new QueryWrapper<BrowseRecord>();
            browseRecordQueryWrapper.eq("user",paramMap.get("loginName"));
            List<Map<String,Object>> browseRecordList =  browseRecordMapper.queryBrowseRecordList(paramMap);
            if(null != browseRecordList){
                for(Map<String,Object> obj :browseRecordList){
                    obj.put("browseTime", DateUtility.getFormatDate((Date) obj.get("browseTime"),DateUtility.DateFormat4));
                }
            }
            returnMap.put("result",browseRecordList);
            returnMap.put("message","留言成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","留言失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }



}
