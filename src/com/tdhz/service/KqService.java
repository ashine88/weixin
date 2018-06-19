package com.tdhz.service;

import com.tdhz.dto.KqMsgDTO;
import com.tdhz.dto.UserRoomDTO;

import java.util.List;

/**
 * Created by liushuai2 on 2018/6/19.
 */
public interface KqService {
    KqMsgDTO getByAss(Integer userId, Integer deptId, String startTime, String endTime);
    List<UserRoomDTO> getUserApartment(Integer userId);
    KqMsgDTO getBySg(Integer userId, Integer roomId, String startTime, String endTime);
}
