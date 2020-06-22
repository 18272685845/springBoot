package com.guohangyu.service.impl;


import com.guohangyu.dao.Tb_userDao;
import com.guohangyu.entity.Tb_user;
import com.guohangyu.service.Tb_userService;
import com.guohangyu.util.ListToSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class Tb_userServiceImpl implements Tb_userService {

    @Autowired
    private Tb_userDao tb_userDao;

    @Override
    public List<Tb_user> findAll() {
        return tb_userDao.findAll();
    }

    @Override
    public List<Tb_user> findByName(String username) {
        return tb_userDao.findByName(username);
    }

    @Override
    public Integer add(Tb_user users) {
        return tb_userDao.add(users);
    }

    @Override
    public Tb_user findByid(Integer userid) {
        return tb_userDao.findByid(userid);
    }

    @Override
    public Integer update(Tb_user users) {
        return tb_userDao.update(users);
    }

    @Override
    public Integer delete(Integer userid) {
        return tb_userDao.delete(userid);
    }

    @Override
    public Tb_user findByLogin(String username) {
        return tb_userDao.findByLogin(username);
    }

    @Override
    public Set<String> selectAllRole(String username) {
        List<String> role = tb_userDao.selectAllRole(username);
        return ListToSet.changeListToSet(role);
    }

    @Override
    public Set<String> selectAllModule(String username) {
        List<String> module = tb_userDao.selectAllModule(username);
        return ListToSet.changeListToSet(module);
    }


}
