package com.guohangyu.service;

import com.guohangyu.entity.Tb_user;

import java.util.List;
import java.util.Set;


public interface Tb_userService {

    /**
     * 查询所有
     * @return
     */
    List<Tb_user> findAll();
    /**
     * 根据名字模糊查询
     * @param username
     * @return
     */
    public List<Tb_user> findByName(String username);

    /**
     *新增用户
     * @return
     */
    public Integer add(Tb_user users);

    public Tb_user findByid(Integer userid);
    /**
     * 修改
     * @param users
     * @return
     */
    public Integer update(Tb_user users);

    /**
     * 删除
     * @param userid
     * @return
     */
    public Integer delete(Integer userid);
    /**
     * 根据名字查询 （登陆）
     * @param username
     * @return
     */
    public Tb_user findByLogin(String username);
    /**
     * 根据用户名查询用户所对应的所有角色
     * @param username
     * @return
     */
    public Set<String> selectAllRole(String username);
    /**
     * 根据用户名查询所有的权限
     * @param username
     * @return
     */
    public Set<String> selectAllModule(String username);
}
