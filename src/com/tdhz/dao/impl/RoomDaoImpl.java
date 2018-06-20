package com.tdhz.dao.impl;/**
 * Created by liushuai2 on 2018/6/19.
 */

import com.tdhz.dao.RoomDao;
import com.tdhz.pojo.Room;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package : com.tdhz.dao.impl
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月19日 18:02
 */
@Repository
public class RoomDaoImpl  extends HibernateDaoSupport implements RoomDao {
    public static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);
    @Autowired
    public void setSessionFactory01(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Room> getByParent(List<Integer> parentRoomIds) {
        StringBuilder sql = new StringBuilder("select room.* from tbcha_room room where 1 = 1");
        if(parentRoomIds != null){
            sql.append(" and room.proom in( ").append( parentRoomIds.toArray(new Integer[]{parentRoomIds.size()})).append(")");
        }
        //过滤其他上级目录
        sql.append(" and room.sextype is not null ");
        List<Room> rooms = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(Room.class).list();
        logger.info("根据父room获取所有房间信息 size:{}", rooms.size());
        return rooms;
    }


}
