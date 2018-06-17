package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.WeixinDao;
import com.tdhz.pojo.Users;
import com.tdhz.pojo.Weixin;


@Repository
public class WeixinDaoImpl extends HibernateDaoSupport implements WeixinDao{
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public List<Weixin> selectOpenid(String openid) {
		
		return (List<Weixin>) super.getHibernateTemplate().find("from Weixin where openid ='"+openid+"'");
	}

	@Override
	public void updateWeixin(Weixin weixin) {
		super.getHibernateTemplate().update(weixin);
		
	}

	@Override
	public void addWeixin(Weixin weixin) {
		super.getHibernateTemplate().save(weixin);
		
	}

	@Override
	public List<Weixin> selectByUser(Integer userid) {
		
		return (List<Weixin>) super.getHibernateTemplate().find("from Weixin where users = "+userid);
	}

	@Override
	public List<Users> getUserByRoleNo(Integer role) {
		
		return (List<Users>) super.getHibernateTemplate().find("from Users where role ="+role);
	}

	@Override
	public void deleteWeixin(String openid) {
		 String hql = "DELETE FROM Weixin AS n WHERE n.openid = '"+openid+"'" ;  
        Query query = getSession().createQuery(hql);  
         
        query.executeUpdate();  
		
		
	}
	
}
