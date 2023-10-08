package com.sz.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sz.common.utils.JwtUtil;
import com.sz.sys.entity.Menu;
import com.sz.sys.entity.RoleMenu;
import com.sz.sys.entity.User;
import com.sz.sys.entity.UserRole;
import com.sz.sys.mapper.UserMapper;
import com.sz.sys.mapper.UserRoleMapper;
import com.sz.sys.service.IMenuService;
import com.sz.sys.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sz
 * @since 2023-09-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IMenuService menuService;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public Map<String, Object> login(User user) {
        //查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        User loginUser = this.baseMapper.selectOne(wrapper);
        if (loginUser!=null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())){
//          String key="user:" + UUID.randomUUID();

            loginUser.setPassword(null);
            String token = jwtUtil.createToken(loginUser);
//          redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);

            Map<String, Object> data = new HashMap<>();
            data.put("token",token);
            return  data;
        }


        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
//        Object obj = redisTemplate.opsForValue().get(token);
        User loginUser= null;
        try {
            loginUser = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (loginUser!=null){
//          User loginUser= JSON.parseObject(JSON.toJSONString(obj),User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name",loginUser.getUsername());
            data.put("avatar",loginUser.getAvatar());
            List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
            data.put("roles",roleList);

            List<Menu> menuListByUserId = menuService.getMenuListByUserId(loginUser.getId());

            data.put("menuList",menuListByUserId);


            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
//        redisTemplate.delete(token);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.baseMapper.insert(user);
        List<Integer> roleIdList = user.getRoleIdList();
        if (roleIdList !=null){
            for (Integer roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user = this.baseMapper.selectById(id);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);
        List<Integer> roleIdList = userRoleList.stream().map(userRole -> {return  userRole.getRoleId();})
                                                                        .collect(Collectors.toList());
        user.setRoleIdList(roleIdList);
        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.baseMapper.updateById(user);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,user.getId());
        userRoleMapper.delete(wrapper);
        List<Integer> roleIdList = user.getRoleIdList();
        if (roleIdList !=null){
            for (Integer roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(null,user.getId(),roleId));
            }
        }
    }

    @Override
    public void removdeleteUserByIdeById(Integer id) {
        this.baseMapper.deleteById(id);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        userRoleMapper.delete(wrapper);
    }
}
