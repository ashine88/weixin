package com.tdhz.dto;/**
 * Created by liushuai2 on 2018/6/18.
 */

import java.io.Serializable;

/**
 * Package : com.tdhz.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月18日 10:46
 *
 *         宿管和公寓房间的关系
 *
 */
public class UserRoomDTO implements Serializable {
    private Integer userId;
    private Integer roomId;
    private String userName;
    private String roomName;
    private String fullRoomName;
    private Integer areaId;
    private String areaName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getFullRoomName() {
        return fullRoomName;
    }

    public void setFullRoomName(String fullRoomName) {
        this.fullRoomName = fullRoomName;
    }
}
