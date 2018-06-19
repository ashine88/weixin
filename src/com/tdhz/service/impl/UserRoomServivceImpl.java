package com.tdhz.service.impl;/**
 * Created by liushuai2 on 2018/6/18.
 */

import com.tdhz.dao.UserAreaDao;
import com.tdhz.dao.UsersDao;
import com.tdhz.dto.UserRoomDTO;
import com.tdhz.pojo.Room;
import com.tdhz.pojo.User_Area;
import com.tdhz.pojo.Users;
import com.tdhz.service.UserRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Package : com.tdhz.service.impl
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月18日 11:23
 */
@Service
public class UserRoomServivceImpl implements UserRoomService {
    @Autowired
    private UserAreaDao userAreaDao;
    @Autowired
    private UsersDao usersDao;
    @Override
    public List<UserRoomDTO> get(Integer userId) {
        return null;
    }

    @Override
    public List<UserRoomDTO> getUserApartment(Integer userId) {
        Integer proom = 1;
        List<UserRoomDTO> roomDTOS = getUserRoom(userId, proom);
        return roomDTOS;
    }

    @Override
    public List<UserRoomDTO> getUserRoom(Integer userId, Integer proom) {
        List<User_Area> userAreas = userAreaDao.getUserArea(userId);
        Users user = usersDao.get(userId);
        List<UserRoomDTO> userRoomDTOS = new ArrayList<>();
        for(User_Area ua : userAreas){

            if(ua.getArea_id() != null && proom.equals(ua.getArea_id().getProom())){
                UserRoomDTO userRoomDTO = new UserRoomDTO();
                userRoomDTO.setUserId(user.getUser_id());
                userRoomDTO.setUserName(user.getUser_name());
                userRoomDTO.setRoomName(ua.getArea_id().getRoomName());
                userRoomDTO.setFullRoomName(ua.getArea_id().getFullRoomName());
                userRoomDTO.setRoomId(ua.getArea_id().getRoomId());
                userRoomDTO.setAreaId(ua.getArea_id().getArea());
                userRoomDTOS.add(userRoomDTO);
            }
        }

        return userRoomDTOS;
    }
}
