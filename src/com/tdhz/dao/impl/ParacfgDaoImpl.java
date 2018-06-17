package com.tdhz.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.ParacfgDao;
import com.tdhz.pojo.Paracfg;

@Repository
public class ParacfgDaoImpl extends HibernateDaoSupport implements ParacfgDao{
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public Paracfg getById(Integer id) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().get(Paracfg.class, id);
	}
}
