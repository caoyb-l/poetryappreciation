package com.poetryappreciation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.poetryappreciation.mapper.*;
import com.poetryappreciation.pojo.*;
import com.poetryappreciation.service.inter.PoetryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.poetryappreciation.util.CommonUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class PoetryServiceImpl implements PoetryService {
    @Resource
    private PoetryMapper poetryMapper;
    @Resource
    private UserFabulousMapper userFabulousMapper;
    @Resource
    private PoetryMessageMapper poetryMessageMapper;
    @Resource
    private BrowseRecordMapper browseRecordMapper;
    @Resource
    private PoetryTypeMapper poetryTypeMapper;
    @Resource
    private UserCollectionMapper userCollectionMapper;

    //查询诗词列表
    @Override
    public Map<String, Object> queryPoetryList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            QueryWrapper<Poetry> poetryQueryWrapper = new QueryWrapper<Poetry>();
            if(!CommonUtility.isEmpty(paramMap.get("param"))){
                poetryQueryWrapper.like("name",paramMap.get("param")).or().like("author",paramMap.get("param")).or().like("content",paramMap.get("param"));
            }
            if(!CommonUtility.isEmpty(paramMap.get("poetryType"))){
                poetryQueryWrapper.eq("type",paramMap.get("poetryType"));
            }

            List<Poetry> poetryList = poetryMapper.selectList(poetryQueryWrapper);
            returnMap.put("result",poetryList);
            returnMap.put("message","查询成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","查询失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //查询诗词详情
    @Override
    public Map<String, Object> queryPoetryDetail(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            String loginName = paramMap.get("loginName"); //登录用户
            String poetryId = paramMap.get("poetryId"); // 诗词ID
            // 诗词详情
            Poetry poetry  = poetryMapper.selectById(poetryId);
            Map<String,Object> resultMap = new HashMap<String,Object>();
            resultMap.put("name",poetry.getName()); //诗词名称
            resultMap.put("annotation",poetry.getAnnotation()); //注解
            resultMap.put("translate",poetry.getTranslate()); //翻译
            resultMap.put("author",poetry.getAuthor()); //作者
            resultMap.put("authorDesc",poetry.getAuthorDesc()); //作者简介
            resultMap.put("browseCount",poetry.getBrowseCount()); //浏览数
            resultMap.put("fabulousCount",poetry.getFabulousCount()); //点赞数
            resultMap.put("publishUser",poetry.getPublishUser()); //发布人
            resultMap.put("content",poetry.getContent());// 诗词内容
            resultMap.put("url",poetry.getUrl());// 链接
            //诗词留言信息
            QueryWrapper<PoetryMessage> poetryMessageQueryWrapper = new QueryWrapper<PoetryMessage>();
            poetryMessageQueryWrapper.eq("poetry_id",poetryId);
            List<PoetryMessage> poetryMessageList = poetryMessageMapper.selectList(poetryMessageQueryWrapper);
            resultMap.put("poetryMessageList",poetryMessageList);

            //判断用户是否点赞
            QueryWrapper<UserFabulous> userFabulousQueryWrapper = new QueryWrapper<UserFabulous>();
            userFabulousQueryWrapper.eq("user",loginName);
            userFabulousQueryWrapper.eq("poetry_id",poetryId);
            List userFabulousList = userFabulousMapper.selectList(userFabulousQueryWrapper);
            if(null!=userFabulousList && userFabulousList.size()>0){
                resultMap.put("fabulous",true);
            }else{
                resultMap.put("fabulous",false);
            }

            //判断用户是否收藏
            QueryWrapper<UserCollection> userCollectionQueryWrapper = new QueryWrapper<UserCollection>();
            userFabulousQueryWrapper.eq("user",loginName);
            userFabulousQueryWrapper.eq("poetry_id",poetryId);
            List userCollectionList = userCollectionMapper.selectList(userCollectionQueryWrapper);
            if(null!=userCollectionList && userCollectionList.size()>0){
                resultMap.put("collection",true);
            }else{
                resultMap.put("collection",false);
            }

            //点击详情记录用户浏览记录
            BrowseRecord browseRecord = new BrowseRecord();
            browseRecord.setUser(loginName);
            browseRecord.setPoetryId(poetryId);
            browseRecord.setBrowseTime(new Date());
            browseRecordMapper.insert(browseRecord);

            //更新诗词的浏览数+1
            poetry.setBrowseCount(poetry.getBrowseCount()+1);
            poetryMapper.updateById(poetry);

            returnMap.put("result",resultMap);
            returnMap.put("message","查询成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","查询失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    //精选诗词，点击量前十的诗词
    @Override
    public Map<String, Object> queryChoicePoetryList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            Page page = new Page(1,10);
            QueryWrapper<Poetry> poetryQueryWrapper = new QueryWrapper<Poetry>();
            poetryQueryWrapper.orderByDesc("browse_count");
            IPage<Poetry> poetryPage = poetryMapper.selectPage(page,poetryQueryWrapper);
            List<Poetry> poetryList = poetryPage.getRecords();
            returnMap.put("result",poetryList);
            returnMap.put("message","查询成功");
            returnMap.put("code","0");
        }catch (Exception e){
            e.printStackTrace();
            returnMap.put("message","查询失败");
            returnMap.put("code","1");
        }
        return returnMap;
    }

    // 后台诗词列表，分页查询
    @Override
    public Map<String, Object> queryPoetryPageList(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            String requestUrl = paramMap.get("requestUrl");
            int current = Integer.parseInt(paramMap.get("current")) ;//页码
            int size = Integer.parseInt(paramMap.get("size"));//条数
            QueryWrapper<Poetry> wrapper = new QueryWrapper<Poetry>();
            if(!CommonUtility.isEmpty(paramMap.get("name"))){
                wrapper.like("name",paramMap.get("name")).or().like("author",paramMap.get("name")).or().like("content",paramMap.get("name"));
            }
            if(!CommonUtility.isEmpty(paramMap.get("poetryType"))){
                wrapper.eq("type",paramMap.get("poetryType"));
            }
            Page page = new Page(current,size);
            IPage<Poetry> poetryIPage = poetryMapper.selectPage(page,wrapper);
            List<Poetry> poetryList = poetryIPage.getRecords();
            for(Poetry obj:poetryList){
                QueryWrapper<PoetryType> poetryTypeQueryWrapper = new QueryWrapper<PoetryType>();
                poetryTypeQueryWrapper.eq("id",obj.getType());
                PoetryType poetryType = poetryTypeMapper.selectOne(poetryTypeQueryWrapper);
                obj.setTypeName(poetryType.getName());
            }
            Map<String,Object> resultMap = new HashMap<>();
            returnMap.put("code",0);
            returnMap.put("message","查询成功");
            resultMap.put("data",poetryList);
            resultMap.put("pageNo",current);
            resultMap.put("pageSize",size);
            resultMap.put("totalCount",poetryIPage.getTotal());
            resultMap.put("totalPage",poetryIPage.getPages());
            resultMap.put("timestamp",new Date());
            returnMap.put("result",resultMap);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    //添加诗词
    @Override
    public Map<String, Object> addPoetry(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            Poetry poetry = new Poetry();
            poetry.setName(paramMap.get("name"));
            poetry.setAnnotation(paramMap.get("annotation"));
            poetry.setTranslate(paramMap.get("translate"));
            poetry.setAuthor(paramMap.get("author"));
            poetry.setAuthorDesc(paramMap.get("authorDesc"));
            poetry.setContent(paramMap.get("content"));
            poetry.setBrowseCount(0);
            poetry.setFabulousCount(0);
            poetry.setType(paramMap.get("type"));
            poetry.setPublishUser(paramMap.get("publishUser"));
            poetry.setUrl(paramMap.get("fileUrl"));
            poetryMapper.insert(poetry);
            returnMap.put("code",0);
            returnMap.put("message","添加成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    //修改
    @Override
    public Map<String, Object> editPoetry(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            Poetry poetry = new Poetry();
            poetry.setId(Integer.parseInt(paramMap.get("id")));
            poetry.setName(paramMap.get("name"));
            poetry.setAnnotation(paramMap.get("annotation"));
            poetry.setTranslate(paramMap.get("translate"));
            poetry.setAuthor(paramMap.get("author"));
            poetry.setAuthorDesc(paramMap.get("authorDesc"));
            poetry.setContent(paramMap.get("content"));
            poetry.setType(paramMap.get("type"));
            poetry.setUrl(paramMap.get("fileUrl"));
            poetryMapper.updateById(poetry);
            returnMap.put("code",0);
            returnMap.put("message","修改成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

    @Override
    public Map<String, Object> delPoetry(Map<String, String> paramMap) {
        Map<String,Object> returnMap = new HashMap<>();
        try{
            poetryMapper.deleteById(paramMap.get("id"));
            returnMap.put("code",0);
            returnMap.put("message","删除成功");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return returnMap;
    }

}
