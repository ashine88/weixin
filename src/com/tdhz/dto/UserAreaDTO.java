package com.tdhz.dto;/**
 * Created by liushuai2 on 2018/6/18.
 */

import java.io.Serializable;

/**
 * Package : com.tdhz.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月18日 10:25
 */
public class UserAreaDTO implements Serializable {
    private String userId;

    private String userName;
    private String areaName;
    private String areaId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
