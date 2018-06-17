package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.PerInfoDao;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PerInfo;

@Repository
public class PerInfoDaoImpl extends HibernateDaoSupport implements PerInfoDao{

	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public PerInfo findById(Integer piid) {
		
		return super.getHibernateTemplate().get(PerInfo.class, piid);
	}

	@Override
	public Dept findpDept(Integer pdept) {
		
		return super.getHibernateTemplate().get(Dept.class, pdept);
	}

	@Override
	public void updatePer(PerInfo perInfo) {
		super.getHibernateTemplate().update(perInfo);
		
	}

	@Override
	public List<PerInfo> findOnePerInfo(String piName) {
		String sql ="from PerInfo where PINAME='"+piName+"'";
		return (List<PerInfo>) super.getHibernateTemplate().find(sql);
	}

	@Override
	public List<Dept> findOneDept(String deptName) {
		String sql ="from Dept where DEPTNAME='"+deptName+"'";
		return  (List<Dept>) super.getHibernateTemplate().find(sql);
	}
}
