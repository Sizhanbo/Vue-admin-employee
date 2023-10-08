package com.sz.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sz.common.vo.Result;
import com.sz.sys.entity.User;
import com.sz.sys.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sz
 * @since 2023-09-12
 */

@CrossOrigin
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public Result<List<User>> getAllUsers(){
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user){
       Map<String, Object> data=userService.login(user);

       if ( data!=null){
           return Result.success(data);

       }
        return Result.fail(20002,"用户名或密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token){
      Map<String,Object>  data= userService.getUserInfo(token);
        if ( data!=null){
            return Result.success(data);

        }
        return Result.fail(20003,"用户登录信息错误");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-token") String token) {
        userService.logout(token);
        return Result.success();
    }
    @GetMapping("/list")
    public Result<Map<String, Object>> getUserList(@RequestParam(value = "username",required = false) String username,
                                               @RequestParam(value = "phone",required = false) String phone,
                                               @RequestParam(value = "pageNo") Long pageNo,
                                               @RequestParam(value = "pageSize") Long pageSize){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username);
        wrapper.eq(StringUtils.hasLength(phone),User::getPhone,phone);
        Page<User> pages= new Page<>(pageNo,pageSize);
        userService.page(pages,wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total",pages.getTotal());
        data.put("rows",pages.getRecords());
        return Result.success(data);
    }
    @PostMapping
    public  Result<?> addUser(@RequestBody User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return  Result.success("新增用户成功!");
    }
    @PutMapping
    public Result<?> updateUser(@RequestBody User user){

        userService.updateUser(user);
        return Result.success("修改用户成功");
    }
    @GetMapping("/{id}")
    public Result<?> getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }
    @DeleteMapping("/{id}")
    public Result<User> deleteUserById(@PathVariable("id") Integer id){
        userService.removdeleteUserByIdeById(id);
        return Result.success("删除用户成功");
    }
}
