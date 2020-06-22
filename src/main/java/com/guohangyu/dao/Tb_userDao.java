package com.guohangyu.dao;


import com.guohangyu.entity.Tb_user;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface Tb_userDao {
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
    public List<Tb_user> findByName( String username);

    /**
     * 根据id查询所有
     * @return
     */
    @Select("select * from tb_user where userid=#{userid}")
    public Tb_user findByid(Integer userid);
    /**
     * 根据id修改
     * @param users
     * @return
     */


    @Update("update tb_user set username=#{username}," +
            "deptid=#{deptid},email=#{email},phonenum=#{phonenum}," +
            "status=#{status} where userid=#{userid}")
    public Integer update(Tb_user users);
    /**
     * 根据id删除
     * @param userid
     * @return
     */
    @Delete("delete from tb_user where userid=#{userid}")
    public Integer delete(Integer userid);
    /**
     *新增用户
     * @return
     */
    @Insert("insert into tb_user(username,deptid,email,phonenum,status)values(#{username},#{deptid},#{email},#{phonenum},#{status})")
    public Integer add(Tb_user users);

    /**
     * 根据名字查询 （登陆）
     * @param username
     * @return
     */
    @Select("select * from tb_user where username=#{username}")
    public Tb_user findByLogin(String username);

    /**
     * 根据用户名查询用户所对应的所有角色
     * @param username
     * @return
     */
    @Select("select r.rolename from tb_user u, role r where u.roleid=r.roleid and u.username=#{username}")
    public List<String> selectAllRole(String username);

    /**
     * 根据用户名查询所有的权限
     * @param username
     * @return
     */
    @Select("select r.quanxian from tb_user u, role r where u.roleid=r.roleid and u.username=#{username}")
    public List<String> selectAllModule(String username);
}