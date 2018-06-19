package com.tdhz.service;

import com.tdhz.pojo.SysParacfg;

import java.util.List;

/**
 * Created by liushuai2 on 2018/6/19.
 */
public interface SysParacfgService {

    SysParacfg get(Integer id);

    List<SysParacfg> get();
}
