package com.tdhz.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.SendLogDao;
import com.tdhz.pojo.SendLog;

@Repository
public class SendLogDaoImpl extends HibernateDaoSupport implements SendLogDao{
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public void saveLog(SendLog log) {
		super.getHibernateTemplate().save(log);
		
	}

	@Override
	public void update(SendLog log) {
		super.getHibernateTemplate().update(log);
		
	}
}
