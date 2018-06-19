package com.tdhz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.tdhz.dao.SysParacfgDao;
import com.tdhz.pojo.AbnormityInfo;
import com.tdhz.pojo.SysParacfg;
import com.tdhz.util.CommonUtil;
import com.tdhz.util.ParaConstant;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.AlertInfoDao;
import com.tdhz.pojo.AlertInfo;
import com.tdhz.util.PageBean;
import org.springframework.util.StringUtils;

@Repository
public class AlertInfoDaoImpl extends HibernateDaoSupport implements AlertInfoDao {
	private static  final Logger logger = LoggerFactory.getLogger(AlertInfoDaoImpl.class);
	@Autowired
	public void setSessionFactory01(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);		
	}
	@Autowired
	private SysParacfgDao sysParacfgDao;
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


	@Override
	public List<AlertInfo> findAlertBySg(Integer userId, Integer roomId, String startTime, String endTime, Integer alertType) {
		Object[] arr = new Object[]{userId, roomId, startTime, endTime, alertType};
		logger.info("根据后勤人员获取对应的晚归数据：userId：{}，classId：{}，startTime：{}，endTime：{}, alertType:{}",arr );

		StringBuilder sql = new StringBuilder("select ainfo.* from bs_abnormity_info ainfo " +
				" left join tbcha_perinfo pinfo on ainfo.person_id = pinfo.piid " +
				" left join tbcha_room room on pinfo.room = room.roomid " +
				" left join sys_user_area ua on ua.area_id = room.area " +
				" where 1 = 1 ");
		if(userId != null){
			sql.append(" ua.user_id =  " + userId);
		}

		if(roomId != null){
			sql.append(" and room.roomid = " + roomId);
		}
		if(alertType != null){
			sql.append(" and ainfo.abn_type = " + alertType);
		}
		if(StringUtils.hasText(startTime)){
			startTime= startTime+" 00:00:00.000";
			sql.append(" and ainfo.create_date >= '" + startTime +"' ");
		}
		if(StringUtils.hasText(endTime)){
			endTime = endTime +" 23:59:59.000";
			sql.append(" and ainfo.create_date <='" + endTime +"' ");

		}
		SysParacfg cfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		String groupValue = cfg.getParaValue();
		sql.append(" and pinfo.[group] in (").append(groupValue).append(")");

		logger.info("根据后勤人员获取对应的晚归查看信息");
		List<AlertInfo> result = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(AlertInfo.class).list();
		return result;
	}

	@Override
	public long findAlertCountBySg(Integer userId, Integer roomId, String startTime, String endTime, Integer alertType) {
		return findAlertBySg(userId, roomId, startTime, endTime, alertType).size();
	}

	@Override
	public List<AlertInfo> findAlertByAss(Integer userId, Integer deptId, String startTime, String endTime, Integer alertType) {
		startTime= startTime+" 00:00:00.000";
		endTime = endTime +" 23:59:59.000";
		Object[] arr = new Object[]{userId, deptId, startTime, endTime, alertType};
		logger.info("根据行政人员信息获取对应的晚归数据：assId：{}，deptId：{}，startTime：{}，endTime：{}, alertType:{}",arr );

		StringBuilder sql = new StringBuilder("select * from bs_abnormity_info ainfo  " +
				" left join tbcha_perinfo pinfo on ainfo.person_id = pinfo.piid " +
				" left join sys_user_dept ud on ud.dept_id =pinfo.dept " +
				"where 1 = 1 ") ;
		if(userId != null){
			sql.append("and ud.user_id = "+ userId);
		}
		if(deptId != null){
			sql.append("and ud.dept_id = " +deptId);
		}
		if(alertType != null){
			sql.append(" and ainfo.abn_type = " + alertType);
		}
		if(StringUtils.hasText(startTime)){
			sql.append(" and ainfo.create_date >= '" + startTime +"' ");
		}
		if(StringUtils.hasText(endTime)){
			sql.append(" and ainfo.create_date <='" + endTime +"' ");
		}

		SysParacfg cfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		String groupValue = cfg.getParaValue();
		sql.append(" and pinfo.[group] in (").append(groupValue).append(")");
		logger.info("根据行政人员获取对应的晚归查看信息");
		List<AlertInfo> result = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(AlertInfo.class).list();
		return result;
	}

	@Override
	public int findAlertCountByAss(Integer userId, Integer deptId, String startTime, String endTime, Integer alertType) {
		return findAlertByAss(userId, deptId, startTime, endTime, alertType).size();
	}
}
