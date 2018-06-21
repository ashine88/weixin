package com.tdhz.service;

import com.tdhz.dto.*;
import com.tdhz.util.Page;

import java.util.List;

/**
 * Created by liushuai2 on 2018/6/19.
 */
public interface KqService {
    KqMsgDTO getByAss(Integer userId, Integer deptId, String startTime, String endTime);

    KqMsgDTO getBySg(Integer userId, Integer roomId, String startTime, String endTime);

    /**
     * 获取考勤明细信息
     * @param detailReqDTO
     * @return
     */
    Page<KqDetailItemDTO> getDetail(KqDetailReqDTO detailReqDTO);
}
