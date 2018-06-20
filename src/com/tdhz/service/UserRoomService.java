package com.tdhz.service;

import com.tdhz.dto.UserRoomDTO;
import com.tdhz.pojo.Room;

import java.util.List;

/**
 * Created by liushuai2 on 2018/6/18.
 */
public interface UserRoomService {

    /**
     * 根据用户id获取所管理的房间信息
     * @param userId
     * @return
     */
    List<UserRoomDTO> get(Integer userId);

    /**
     * 根据父roomid找到紧邻的下一级房间
     * @param userId
     * @return
     */
    List<UserRoomDTO> getUserRoom0(Integer userId);
    List<Room> getUserRoom(Integer userId);
    /**
     * 根据用户查询所管理的公寓
     * @param userId
     * @return
     */
    List<UserRoomDTO> getUserApartment(Integer userId);

    List<UserRoomDTO> getUserRoom(Integer userId, Integer proom);

}
