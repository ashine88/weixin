package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.LeaveDao;
import com.tdhz.pojo.ClassLeave;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PersonLeave;
import com.tdhz.pojo.User_Dept;

@Repository
public class LeaveDaoImpl extends HibernateDaoSupport implements LeaveDao{
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}
	@Override
	public void addPersonLeave(PersonLeave personLeave) {
		super.getHibernateTemplate().save(personLeave);
		
	}

	@Override
	public void addClassLeave(ClassLeave classLeave) {
		
		super.getHibernateTemplate().save(classLeave);
	}
	@Override
	public List<User_Dept> seletByUserId(User_Dept user_dept) {
		
		return (List<User_Dept>) super.getHibernateTemplate().find("from User_Dept where user_id ="+user_dept.getUser_id());
	}
	@Override
	public Dept fingById(Integer dept_id) {
		// TODO Auto-generated method stub
		return super.getHibernateTemplate().get(Dept.class, dept_id);
	}

}
