package com.guohangyu.realm;


import com.guohangyu.entity.Tb_user;
import com.guohangyu.service.Tb_userService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

//AuthenticatingRealm:认证的一个Realm 类 实现了 Realm接口
//AuthorizingRealm:授权的Realm类，继承了AuthenticatingRealm类
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private Tb_userService userService;
    /**
     * 登陆认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取前端传过来的token信息
       UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        //从token中获取的用户名  根据用户名在数据库中查询用户对象
        String username = token.getUsername();
        Tb_user login = userService.findByLogin(username);
        if (login==null){
            throw new UnknownAccountException("用户"+login+"不存在");
        }

        //返回一个认证信息的一个实例
        /**
    * login.getUsername(), :从数据库中拿到的用户名
    * login.getPassword(), 从数据库中拿到的密码
    * getName() 获取当前Realm的名字
    */
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(login.getUsername(),login.getPassword(), ByteSource.Util.bytes(login.getUsername()),getName());
        return info;
    }

    /**
     * 角色权限校验
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString();
        //查询角色
        Set<String> role = userService.selectAllRole(username);
        System.out.println(role.toString());
        //查询权限
        Set<String> module = userService.selectAllModule(username);
        System.out.println(module.toString());
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //绑定角色列表
        info.setRoles(role);
        //绑定权限列表
        info.setStringPermissions(module);
        return info;
    }
}
