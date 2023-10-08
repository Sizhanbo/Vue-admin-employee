package com.sz.sys.service;

import com.sz.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sz
 * @since 2023-09-13
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getAllMenu();
    List<Menu> getMenuListByUserId(Integer userId);
}
