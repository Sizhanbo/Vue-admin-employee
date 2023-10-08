package com.sz.sys.mapper;

import com.sz.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sz
 * @since 2023-09-13
 */
public interface MenuMapper extends BaseMapper<Menu> {

    public List<Menu> getMenuListByUserId(@Param("userId") Integer userId,
                                          @Param("pid") Integer pid);
}
