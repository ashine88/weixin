package com.tdhz.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.UserOperaLogDao;
import com.tdhz.pojo.UserOperaLog;

@Repository
public class UserOperaLogDaoImpl extends HibernateDaoSupport implements UserOperaLogDao {

	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}
	@Override
	public void addUserOperaLog(UserOperaLog u) {
		super.getHibernateTemplate().save(u);
		
	}

}
