package com.poetryappreciation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.poetryappreciation.pojo.SysRoleResourceNew;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cnc
 * @since 2019-07-09
 */
public interface SysRoleResourceNewMapper extends BaseMapper<SysRoleResourceNew> {

    List<Map<String,Object>> getRoleMenu(Map<String, String> map);
}
