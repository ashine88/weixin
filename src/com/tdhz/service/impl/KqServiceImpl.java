package com.tdhz.service.impl;/**
 * Created by liushuai2 on 2018/6/18.
 */

import com.tdhz.dao.LeaveDao;
import com.tdhz.dao.PerInfoDao;
import com.tdhz.dto.*;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Area;
import com.tdhz.service.*;
import com.tdhz.util.KqConstant;
import com.tdhz.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public static final Logger logger  = LoggerFactory.getLogger(KqServiceImpl.class);
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
    @Autowired
    private LeaveDao leaveDao;



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

        //实习人数
        int sx = leaveService.getSXBySg(userId, roomId, startTime, endTime);
        int qj = leaveService.getQJBySg(userId, roomId, startTime, endTime);
        int cg = abnormityService.findCgCountBySg(userId, roomId, startTime, endTime);
        int wg = abnormityService.findWgCountBySg(userId, roomId, startTime, endTime);
        int wgbj = alertInfoService.getWgBySg(userId, roomId, startTime, endTime);

        int wcbj = alertInfoService.getWcBySg(userId, roomId, startTime, endTime);
        int zxyj = alertInfoService.getZxBySg(userId, roomId, startTime, endTime);
        int total =perInfoDao.getCountBySg(userId, roomId);

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


    @Override
    public Page<KqDetailItemDTO> getDetail(KqDetailReqDTO detailReqDTO) {
        if(detailReqDTO.getPage() == null){
            detailReqDTO.setPage(new Page<>());
        }

        Integer operatorType = detailReqDTO.getOpertorType();
        Integer contentType = detailReqDTO.getContentType();
        Page<KqDetailItemDTO> page = null;
        if(operatorType == KqConstant.OPEROR_TYPE_XZ){
            logger.info("开始查询行政口下的人员信息 userId：{}", detailReqDTO.getUserId());
           if(KqConstant.CONTENT_TYPE_ALL.equals(contentType)){
                logger.info("获取行政口下所有管理人员");
                page = perInfoDao.getPersonLeaveDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_SX.equals(contentType)){
                logger.info("获取行政口下所有实习人员");
                page = leaveService.getSXDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_QJ.equals(contentType)){
                logger.info("获取行政口下所有请假人员");
                page = leaveService.getQJDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_WG.equals(contentType)){
                logger.info("获取行政口下所有未归人员");
                page = alertInfoService.getWgDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_CG.equals(contentType)){
                logger.info("获取行政口下所有迟归人员");
                page = abnormityService.getCgDetailByAss(detailReqDTO,page);

            }else if(KqConstant.CONTENT_TYPE_WGBJ.equals(contentType)){
                logger.info("获取行政口下所有未归报警人员");
                page = abnormityService.getWgDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_WCBJ.equals(contentType)){
                logger.info("获取行政口下所有未出报警人员");
                page = alertInfoService.getWcDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_ZXYJ.equals(contentType)){
                logger.info("获取行政口下所有在校预警人员");
                page = alertInfoService.getZxDetailByAss(detailReqDTO, page);
            }

        }else{
            logger.info("开始查询后勤下的人员信息 userId：{}", detailReqDTO.getUserId());
            if(KqConstant.CONTENT_TYPE_ALL.equals(contentType)){
                logger.info("获取后勤下所有管理人员");
                page = perInfoDao.getPersonLeaveDetailBySg(detailReqDTO, page);
            }else if(KqConstant.CONTENT_TYPE_SX.equals(contentType)){
                logger.info("获取后勤下所有实习人员");
                page = leaveService.getSXDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_QJ.equals(contentType)){
                logger.info("获取后勤下所有请假人员");
                page = leaveService.getQJDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_WG.equals(contentType)){
                logger.info("获取后勤下所有未归人员");
                page = alertInfoService.getWgDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_CG.equals(contentType)){
                logger.info("获取后勤下所有迟归人员");
                page = abnormityService.getCgDetailByAss(detailReqDTO,page);

            }else if(KqConstant.CONTENT_TYPE_WGBJ.equals(contentType)){
                logger.info("获取后勤下所有未归报警人员");
                page = abnormityService.getWgDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_WCBJ.equals(contentType)){
                logger.info("获取后勤下所有未出报警人员");
                page = alertInfoService.getWcDetailByAss(detailReqDTO, page);

            }else if(KqConstant.CONTENT_TYPE_ZXYJ.equals(contentType)){
                logger.info("获取后勤下所有在校预警人员");
                page = alertInfoService.getZxDetailByAss(detailReqDTO, page);
            }
        }


        leaveDao.getPersonLeaveDetailByAss(detailReqDTO, detailReqDTO.getPage(), null);
        detailReqDTO.setUserId(2);
        leaveDao.getPersonLeaveDetailBySg(detailReqDTO, detailReqDTO.getPage(), null);
        return null;
    }
}
