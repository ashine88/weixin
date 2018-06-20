package com.tdhz.dao.impl;

import java.util.List;

import com.tdhz.dao.UsersDao;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.UserAreaDao;
import com.tdhz.pojo.PerInfo;
import com.tdhz.pojo.Room;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.pojo.User_Area;

@Repository
public class UserAreaDaoImpl extends HibernateDaoSupport implements UserAreaDao{
	public static final Logger logger = LoggerFactory.getLogger(UserAreaDaoImpl.class);
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public List<User_Area> getUserArea(Integer userId) {
		String hql =" from User_Area where user_id = "+userId;
		return (List<User_Area>) super.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Room> findAllfloorByProom(Integer proom) {
		String hql = " from Room where proom =" + proom +"order by roomname";
		return (List<Room>) super.getHibernateTemplate().find(hql);
	}

	@Override
	public List<PerInfo> findAllperByroomId(Integer roomId) {
		String hql ="from PerInfo where room = "+roomId;
		return (List<PerInfo>) super.getHibernateTemplate().find(hql);
	}

	@Override
	public List<Tbcha_channeltg_14> findTgByPer(Integer perInfoId) {
		String sql = "select top 1 * from tbcha_channeltg_14 where PERINFO = "+perInfoId;
		return (List<Tbcha_channeltg_14>) super.getSession().createSQLQuery(sql).addEntity(Tbcha_channeltg_14.class).list();
	}

	@Override
	public List<Object[]> findByRoomId(String sql) {
		
		return getSession().createSQLQuery(sql)
				.addScalar("roomId", Hibernate.INTEGER)
				.addScalar("roomName", Hibernate.STRING)
				.addScalar("roomfullName", Hibernate.STRING)
				.addScalar("personAllcount", Hibernate.INTEGER)
				.addScalar("personIncount", Hibernate.INTEGER)
				.list();
	}

	@Override
	public List<PerInfo> findNotBackPerByfloorId(String roomIds) {
		String hql = " from  PerInfo where lastroomtginout = '4_2' and room in("+roomIds+") order by room";
		return  (List<PerInfo>) super.getHibernateTemplate().find(hql);
				
				
	}


	@Override
	public List<Room> getUserRoom(Integer roomId) {

		StringBuilder sql = new StringBuilder("select room.* from tbcha_room room ");
		sql.append("left join sys_user_area area on room.area = area.area_id");
		//过滤其他上级目录
		sql.append(" and room.sextype is not null ");
		List<Room> rooms = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(Room.class).list();
		logger.info("根据父room获取所有房间信息 size:{}", rooms.size());
		return null;
	}
}
