package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.SNSUserInfoDao;
import com.tdhz.pojo.SNSUserInfo;

@Repository
public class SNSUserInfoDaoImpl extends HibernateDaoSupport implements SNSUserInfoDao{

	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}
	@Override
	public void insertSNSUserInfo(SNSUserInfo sns) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().save(sns);
	}

	@Override
	public void updateSNSUserInfo(SNSUserInfo sns) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().update(sns);
	}

	@Override
	public List<SNSUserInfo> findByOpenid(String openId) {
		// TODO Auto-generated method stub
		String hql = "from SNSUserInfo where openId = '"+openId+"'";
		return (List<SNSUserInfo>) super.getHibernateTemplate().find(hql);
	}

	@Override
	public SNSUserInfo findById(Integer siid) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().get(SNSUserInfo.class, siid);
	}

}
