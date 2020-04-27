package com.poetryappreciation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetryappreciation.pojo.PoetryMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 诗词留言表
 * </p>
 */
@Mapper
public interface PoetryMessageMapper extends BaseMapper<PoetryMessage> {


}
