package com.poetryappreciation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetryappreciation.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 用户表
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
