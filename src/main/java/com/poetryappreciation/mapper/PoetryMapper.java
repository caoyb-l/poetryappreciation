package com.poetryappreciation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetryappreciation.pojo.Poetry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 诗词表
 * </p>
 */
@Mapper
public interface PoetryMapper extends BaseMapper<Poetry> {



}
