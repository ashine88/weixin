package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.UserRoleDao;
import com.tdhz.pojo.Users_Role;

@Repository
public class UserRoleDaoImpl  extends HibernateDaoSupport implements UserRoleDao{
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public List<Users_Role> getUserRole(Integer user_id) {
		String hql = "from Users_Role where user_id = "+user_id;
		return  (List<Users_Role>) super.getHibernateTemplate().find(hql);
		
	}
}
