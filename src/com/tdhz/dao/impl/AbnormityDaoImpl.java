package com.tdhz.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.AbnormityDao;
import com.tdhz.pojo.AbnormityInfo;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.util.PageBean;

@Repository
public class AbnormityDaoImpl extends HibernateDaoSupport implements AbnormityDao{
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}
	//晚归异常
	@Override
	public List<AbnormityInfo> findLaterInfo(String onday, PageBean pe) {
		//通过hibernate提供的hql语句查询
		String begintime= onday+" 00:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String sql =
		"select * from(select top "+pe.getPageNum()*pe.getPageSize()+" ROW_NUMBER() over(order by abnormity_id asc) AS ROWID, * FROM bs_abnormity_info where abn_type=3 and create_date  between '"+begintime+"' and '"+endtime+"') AS TEMP1 WHERE ROWID>"+(pe.getPageNum()-1)*pe.getPageSize() ;
		List<AbnormityInfo> alist = getSession().createSQLQuery(sql).addEntity(AbnormityInfo.class).list();    
		return alist;
	}
	//晚归异常人数统计
	@Override
	public long findLaterCount(String onday) {
		//通过hibernate提供的hql语句查询
		String begintime= onday+" 00:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String hql = "from AbnormityInfo where  create_date  between '"+begintime+"' and '"+endtime+"'and abn_type=3";
		List<AbnormityInfo> alist = (List<AbnormityInfo>) super.getHibernateTemplate().find(hql);
		long total = alist.size();
		return total;
	}
	//刷卡异常
	@Override
	public List<AbnormityInfo> findCarAbn(String onday, PageBean pe) {
		//通过hibernate提供的hql语句查询
		String begintime= onday+" 00:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String sql =
		"select * from(select top "+pe.getPageNum()*pe.getPageSize()+" ROW_NUMBER() over(order by abnormity_id asc) AS ROWID, * FROM bs_abnormity_info where abn_type=1 or abn_type =2 and create_date  between '"+begintime+"' and '"+endtime+"') AS TEMP1 WHERE ROWID>"+(pe.getPageNum()-1)*pe.getPageSize() ;
		List<AbnormityInfo> ablist = getSession().createSQLQuery(sql).addEntity(AbnormityInfo.class).list();    
		return ablist;
	}
	//刷卡异常统计
	@Override
	public long findCarAbnCount(String onday) {
		//通过hibernate提供的hql语句查询
		String begintime= onday+" 00:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String hql = "from AbnormityInfo where  create_date  between '"+begintime+"' and '"+endtime+"'and abn_type=1 or abn_type =2";
		List<AbnormityInfo> alist = (List<AbnormityInfo>) super.getHibernateTemplate().find(hql);
		long total = alist.size();
		return total;
	}
	@Override
	public List<Object[]> findCarAbn(String sql) {
		return getSession().createSQLQuery(sql)				
				.addScalar("PIID",Hibernate.INTEGER)
				.addScalar("PINAME",Hibernate.STRING)
				.addScalar("FULLDEPTNAME",Hibernate.STRING)
				.addScalar("last_tg_datetime",Hibernate.STRING)
				.addScalar("abn_type",Hibernate.INTEGER)
				.list();
	}
	@Override
	public List<Tbcha_channeltg_14> findLaterInfo(String begin, String onday,
			PageBean pe) {
		String begintime  = onday+" 22:00:00.000";
		String endtime=onday+" 23:59:59.000";
		String sql = "select * from bs_abnormity_info where TGTIME between '"+begin +"' and '"+endtime +"'";
		List<Tbcha_channeltg_14> alist = getSession().createSQLQuery(sql).addEntity(Tbcha_channeltg_14.class).list();    
		return alist;
	}
	@Override
	public List<Object[]> findLaterBackByAss(String sql) {
		
		return getSession().createSQLQuery(sql)				
				.addScalar("PIID",Hibernate.INTEGER)
				.addScalar("PINAME",Hibernate.STRING)
				.addScalar("FULLDEPTNAME",Hibernate.STRING)
				.addScalar("TGTIME",Hibernate.STRING)
				.list();
	}

}
