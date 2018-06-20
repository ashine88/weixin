package com.tdhz.dao;

import com.tdhz.pojo.Room;

import java.util.List;

/**
 * Created by liushuai2 on 2018/6/19.
 */
public interface RoomDao {

    List<Room> getByParent(List<Integer> parentRoomIds);


}
