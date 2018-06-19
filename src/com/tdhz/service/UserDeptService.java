package com.tdhz.service;

import com.tdhz.dto.UserDeptDTO;

import java.util.List;

/**
 * Created by liushuai2 on 2018/6/18.
 */
public interface UserDeptService {
    List<UserDeptDTO> getBJ(Integer userId);
}
