package com.tdhz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.UsersDeptDao;
import com.tdhz.pojo.User_Dept;

@Repository
public class UsersDeptDaoImpl extends HibernateDaoSupport implements UsersDeptDao {

	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}
	@Override
	public List<User_Dept> findByUserId(Integer userid) {
		String hql ="from User_Dept where user_id = "+userid;
		
		return  (List<User_Dept>) super.getHibernateTemplate().find(hql);
	}


	@Override
	public List<User_Dept> getBJ(Integer userId) {
		List<User_Dept> all = findByUserId(userId);
		List<User_Dept> bjs = new ArrayList<>();
		for(User_Dept ud : all){
			String dname = ud.getDept_id().getDeptName();
			if(dname.endsWith("班")){
				bjs.add(ud);
			}
		}
		return bjs;
	}

}
