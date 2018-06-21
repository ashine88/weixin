package com.tdhz.dto;/**
 * Created by liushuai2 on 2018/6/20.
 */

import com.tdhz.util.Page;
import com.tdhz.util.PageBean;

import java.io.Serializable;

/**
 *  考勤详情页分析
 *
 * Package : com.tdhz.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月20日 15:42
 */
public class KqDetailReqDTO implements Serializable{

    /**
     * 明细标题
     */
    private String title;
    /**
     * 班级
     */
    private Integer deptId;

    /**
     * 公寓id
     */
    private Integer roomId;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     *  1：行政口，2：后勤口
     */
    private Integer opertorType;

     /**
     *  0：全部
     *  1: 实习
     *  2：请假
     *  3：迟归
     *  4：未归
     *  5：未归报警
     *  6：未出报警
     *  7：在校预警
     *  {{total.DATA}}，实习人数：{{sx.DATA}}，请假人数：{{qj.DATA}}，迟归人数：{{cg.DATA}}，未归人数：{{wg.DATA}}，未归报警：{{wgbj.DATA}}，未出报警：{{wcbj.DATA}}，在校预警：{{zxyj.DATA}}
     *
     *
     *
     *
     */
    private Integer contentType;

    /**
     * 分页对象
     */
    private Page<?> page;
    /**
     * 当前登录用户信息
     */
    private Integer userId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getOpertorType() {
        return opertorType;
    }

    public void setOpertorType(Integer opertorType) {
        this.opertorType = opertorType;
    }

    public Page<?> getPage() {
        return page;
    }

    public void setPage(Page<?> page) {
        this.page = page;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
}
