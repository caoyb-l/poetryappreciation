package com.poetryappreciation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetryappreciation.pojo.UserCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 用户收藏表
 * </p>
 */
@Mapper
public interface UserCollectionMapper extends BaseMapper<UserCollection> {
    List<Map<String,Object>> queryCollectionList(Map<String,String> paramMap);

}
