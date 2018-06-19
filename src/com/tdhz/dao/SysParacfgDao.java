package com.tdhz.dao;

import com.tdhz.pojo.SysParacfg;

import java.util.List;

/**
 * Created by liushuai2 on 2018/6/19.
 */
public interface SysParacfgDao {

    List<SysParacfg> get();

    SysParacfg get(Integer id);


}
