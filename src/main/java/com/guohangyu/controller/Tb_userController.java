package com.guohangyu.controller;

import com.guohangyu.entity.Tb_user;
import com.guohangyu.service.Tb_userService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class Tb_userController {

    @Autowired
    private Tb_userService tb_userService;

    @RequestMapping("/findAll")
    public String findAll(Map map){
        List<Tb_user> list = tb_userService.findAll();
        map.put("list",list);
        return "list";
    }

    @RequestMapping("/findByName")
    public String findByName(Tb_user users ,Map map){
        List<Tb_user> userServiceByName = tb_userService.findByName(users.getUsername());
        map.put("list",userServiceByName);
        return "list";
    }
    @RequestMapping("/findByid")
    public String findByid(Tb_user users ,Map map){
        Tb_user byid = tb_userService.findByid(users.getUserid());
        map.put("byid",byid);
        return "updateEdit";
    }
    @RequestMapping("/update")
    public String update(Tb_user users){
        Integer update = tb_userService.update(users);
        System.out.println(update);
        return "redirect:findAll";
    }
    @RequiresRoles("主管")
    @RequiresPermissions("新增用户")
    @RequestMapping("/add")
    public String add(Tb_user users){
        Integer add = tb_userService.add(users);
        return "redirect:findAll";
    }
    @RequiresRoles("总监")
    @RequiresPermissions("删除用户")
    @RequestMapping("/delete")
    public String delete(Tb_user users){
        Integer delete = tb_userService.delete(users.getUserid());
        System.out.println(delete);
        return "redirect:findAll";
    }

    @RequestMapping("/login")
    public String login(Tb_user users){
        Subject currentUser = SecurityUtils.getSubject();
        //当前用户还没有认证；没有登陆
        if (!currentUser.isAuthenticated()) {
            //用户名密码的号牌信息  从表单中传递过来的用户名 和 密码
            UsernamePasswordToken token = new UsernamePasswordToken(users.getUsername(),users.getPassword());
            //记住我
            token.setRememberMe(true);
            try {
                //调用登陆方法 将号牌委托给---->安全管理器---->进入认证器，调用Realm获取用户信息进行匹配
                currentUser.login(token);
                return "redirect:findAll";
            } catch (UnknownAccountException uae) {//账号不存在
                System.out.println("账号不存在");
            } catch (IncorrectCredentialsException ice) {//密码不匹配
                System.out.println("密码不匹配");
            } catch (LockedAccountException lae) {//账户锁定
                System.out.println("账户锁定");
            }
            catch (AuthenticationException ae) {
                System.out.println("认证异常");
            }
        }

        return "error";
    }
}
