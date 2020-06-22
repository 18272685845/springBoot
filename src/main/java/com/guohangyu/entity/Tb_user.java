package com.guohangyu.entity;

import lombok.Data;

@Data
public class Tb_user {
    private Integer userid;

    private String username;

    private Integer deptid;

    private String email;

    private String phonenum;

    private Integer status;

    private String password;

    private String salt;

    private Integer roleid;

    private Tb_dept tb_dept;

    private State state;

    private String code;


}