package com.tdhz.dao.impl;

import java.util.List;

import net.sf.json.JSONObject;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.UsersDao;
import com.tdhz.pojo.Users;

@Repository
public class UsersDaoImpl extends HibernateDaoSupport implements UsersDao{
	public static final Logger logger = LoggerFactory.getLogger(UsersDaoImpl.class);
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}
	
	
	@Override
	public List<Users> selectOper(String operName, String operPass) {
		
		return (List<Users>) super.getHibernateTemplate().find("from Users where user_code = '"+operName+"' and user_password ='"+operPass+"'");
	}


	@Override
	public List<Users> selectOpenid(String openid) {
		
		return (List<Users>) super.getHibernateTemplate().find("from Users where openid = '"+openid+"'");
	}


	@Override
	public void updateUser(Users user) {
		super.getHibernateTemplate().update(user);
		
	}


	@Override
	public void addUser(Users user) {
		super.getHibernateTemplate().save(user);
	}


	@Override
	public List<Users> getUserByRoleNo(Integer role) {
		
		return (List<Users>) super.getHibernateTemplate().find("from Users where role ="+role);
	}


	@Override
	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		return (List<Users>) super.getHibernateTemplate().find("from Users");
	}


	@Override
	public List<Object[]> findAllAssByWX(String sql) {
		
		return getSession().createSQLQuery(sql)
			   .addScalar("user_id",Hibernate.INTEGER)
			   .addScalar("user_name",Hibernate.STRING)
			   .addScalar("role_name",Hibernate.STRING)
			   .addScalar("con_phone_no",Hibernate.STRING)
				.list()	;
	}


	@Override
	public List<Object[]> findAllAss(String sql) {
		return getSession().createSQLQuery(sql)
				   .addScalar("user_id",Hibernate.INTEGER)
				   .addScalar("user_name",Hibernate.STRING)
				   .addScalar("role_name",Hibernate.STRING)
				   .addScalar("DEPTNAME",Hibernate.STRING)
				   .addScalar("FULLDEPTNAME",Hibernate.STRING)
					.list()	;
	}


	@Override
	public Users get(Integer userId) {
		logger.info("根据用户id查询对用的用户信息：userId={}", userId);
		Users users = super.getHibernateTemplate().get(Users.class, userId);
		logger.info("查询到的用户信息：user={}", JSONObject.fromObject(users).toString());

		return users;
	}
}
