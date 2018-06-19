package com.tdhz.service.impl;/**
 * Created by liushuai2 on 2018/6/18.
 */

import com.tdhz.dao.PerInfoDao;
import com.tdhz.dto.KqMsgDTO;
import com.tdhz.dto.UserRoomDTO;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Area;
import com.tdhz.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package : com.tdhz.service.impl
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月18日 10:48
 */
@Service
public class KqServiceImpl implements KqService {
    @Autowired
    private UserRoomService userRoomService;

    @Autowired
    private UserAreaService userAreaService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private AbnormityService abnormityService;

    @Autowired
    private AlertInfoService alertInfoService;
    @Autowired
    private PerInfoDao perInfoDao;

    /**
     * 获取用户所管理的公寓信息
     * @param userId
     * @return
     */
    public List<UserRoomDTO> getUserApartment(Integer userId){
        return userRoomService.getUserApartment(userId);
    }


    @Override
    public KqMsgDTO getByAss(Integer userId, Integer deptId, String startTime, String endTime){
        //实习人数
        int sx = leaveService.getSXByAss(userId, deptId, startTime, endTime);
        int qj = leaveService.getQJByAss(userId, deptId, startTime, endTime);
        int cg = abnormityService.findCgCountByAss(userId, deptId, startTime, endTime);
        int wg = abnormityService.findWgCountByAss(userId, deptId, startTime, endTime);
        int wgbj = alertInfoService.getWgByAss(userId, deptId, startTime, endTime);

        int wcbj = alertInfoService.getWcByAss(userId, deptId, startTime, endTime);
        int zxyj = alertInfoService.getZxByAss(userId, deptId, startTime, endTime);
        int total =perInfoDao.getCountByAss(userId, deptId);

        KqMsgDTO kqMsgDTO = new KqMsgDTO();
        kqMsgDTO.setSx(sx);
        kqMsgDTO.setTotal(total);
        kqMsgDTO.setQj(qj);
        kqMsgDTO.setCg(cg);
        kqMsgDTO.setWg(wg);
        kqMsgDTO.setWgbj(wgbj);
        kqMsgDTO.setWcbj(wcbj);
        kqMsgDTO.setZxyj(zxyj);
        return kqMsgDTO;
    }

    public KqMsgDTO getBySg(Integer userId, Integer roomId, String startTime, String endTime){

        return null;
    }


}
