package com.tdhz.dto;/**
 * Created by liushuai2 on 2018/6/20.
 */

import com.tdhz.util.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Package : com.tdhz.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月20日 15:50
 */
public class KqDetailRespDTO implements Serializable {
    /**
     * 报表信息
     */
    private Page<KqDetailItemDTO> page;
    /**
     * 报表日期
     */
    private String createTime;
    /**
     * 总人数
     */
    private int totalNum;


    public Page<KqDetailItemDTO> getPage() {
        return page;
    }

    public void setPage(Page<KqDetailItemDTO> page) {
        this.page = page;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
