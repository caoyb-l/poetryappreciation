package com.poetryappreciation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetryappreciation.pojo.BrowseRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 用户浏览记录表
 * </p>
 */
@Mapper
public interface BrowseRecordMapper extends BaseMapper<BrowseRecord> {

    List<Map<String,Object>> queryBrowseRecordList(Map<String,String> paramMap);

}
