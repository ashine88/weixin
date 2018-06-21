package com.tdhz.dto;/**
 * Created by liushuai2 on 2018/6/20.
 */

import java.io.Serializable;

/**
 * Package : com.tdhz.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月20日 15:46
 */
public class KqDetailItemDTO implements Serializable {
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 学生学号
     */
    private String studentNo;

    /**
     * 班级
     */
    private String className;
    /**
     * 宿舍房间
     */
    private String roomName;
    /**
     * 学院名称
     */

    private String collegeName;

    /**
     * 公寓名称
     */
    private String apartmentName;


    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }


    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }
}
