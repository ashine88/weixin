package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.AlertInfoDao;
import com.tdhz.pojo.AlertInfo;
import com.tdhz.util.PageBean;

@Repository
public class AlertInfoDaoImpl extends HibernateDaoSupport implements AlertInfoDao {
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}

	@Override
	public List<AlertInfo> findAll(String onday,PageBean pe) {
		//通过hibernate提供的hql语句查询
		String begintime= onday+" 00:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String sql =
		"select * from(select top "+pe.getPageNum()*pe.getPageSize()+" ROW_NUMBER() over(order by alert_info_id asc) AS ROWID, * FROM bs_psn_alert_info where create_date  between '"+begintime+"' and '"+endtime+"') AS TEMP1 WHERE ROWID>"+(pe.getPageNum()-1)*pe.getPageSize() ;
				
		List<AlertInfo> alist = getSession().createSQLQuery(sql).addEntity(AlertInfo.class).list();    
		
		return alist;
	}
	@Override
	public List<AlertInfo> findAll(String onday) {
		//通过hibernate提供的hql语句查询
		String begintime= onday+" 00:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String sql =
		"select * FROM bs_psn_alert_info where create_date  between '"+begintime+"' and '"+endtime+"'order by alert_info_id desc" ;
				
		List<AlertInfo> alist = getSession().createSQLQuery(sql).addEntity(AlertInfo.class).list();    
		
		return alist;
	}

	@Override
	public long findAlertCount(String onday) {
		//通过hibernate提供的hql语句查询
		String begintime= onday+" 00:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String hql = "from AlertInfo where create_date  between '"+begintime+"' and '"+endtime+"'";
		long total = super.getHibernateTemplate().find(hql).size();
		return total;
	}

	@Override
	public AlertInfo findById(Integer alert_info_id) {
	
		return super.getHibernateTemplate().get(AlertInfo.class, alert_info_id);
	}

	@Override
	public void updateAlert(AlertInfo alertInfo) {
		super.getHibernateTemplate().update(alertInfo);
		
	}

	@Override
	public List<Object[]> findAlertByAss(String sql) {
		return getSession().createSQLQuery(sql)				
				.addScalar("PIID",Hibernate.INTEGER)
				.addScalar("PINAME",Hibernate.STRING)
				.addScalar("DEPTNAME",Hibernate.STRING)
				.addScalar("FULLDEPTNAME",Hibernate.STRING)
				.addScalar("LASTTGINOUT",Hibernate.STRING)
				.addScalar("last_tg_datetime",Hibernate.STRING)
				.addScalar("alert_type",Hibernate.INTEGER)
				.addScalar("alert_state",Hibernate.STRING)
				.addScalar("alert_info_id",Hibernate.INTEGER)
				.list();
	}
}
