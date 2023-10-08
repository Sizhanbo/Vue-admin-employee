package com.sz.sys.mapper;

import com.sz.sys.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sz
 * @since 2023-09-13
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    public List<Integer> getMenuIdListByRoleId(Integer roleId);
}
