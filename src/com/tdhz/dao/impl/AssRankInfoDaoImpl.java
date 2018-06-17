package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.AssRankInfoDao;

@Repository
public class AssRankInfoDaoImpl extends HibernateDaoSupport implements AssRankInfoDao{
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public List<Object[]> findfdypm1( String onday) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
