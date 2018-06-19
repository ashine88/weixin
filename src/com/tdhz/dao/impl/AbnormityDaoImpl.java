package com.tdhz.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tdhz.dao.SysParacfgDao;
import com.tdhz.pojo.SysParacfg;
import com.tdhz.util.CommonUtil;
import com.tdhz.util.ParaConstant;
import com.tdhz.util.TypeConstant;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.AbnormityDao;
import com.tdhz.pojo.AbnormityInfo;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.util.PageBean;
import org.springframework.util.StringUtils;

@Repository
public class AbnormityDaoImpl extends HibernateDaoSupport implements AbnormityDao{

    public static final Logger logger = LoggerFactory.getLogger(AbnormityDaoImpl.class);
    @Autowired
    private SysParacfgDao sysParacfgDao;

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
    @Override
	public int findAbnormityCountByAss(Integer assId, Integer deptId, String startTime, String endTime, Integer abnType){
		List<AbnormityInfo> abnormityInfos = findAbnormityByAss(assId, deptId, startTime, endTime, abnType);

        return abnormityInfos.size();
	}

    @Override
    public List<AbnormityInfo> findAbnormityByAss(Integer userId, Integer deptId, String startTime, String endTime, Integer abnType){
        if(StringUtils.hasText(startTime)){
            startTime= startTime+" 00:00:00.000";
        }
        if(StringUtils.hasText(endTime)){
            endTime = endTime +" 23:59:59.000";
        }
        Object[] arr = new Object[]{userId, deptId, startTime, endTime};
        logger.info("根据行政人员信息获取对应的晚归数据：assId：{}，deptId：{}，startTime：{}，endTime：{}",arr );

        StringBuilder sql = new StringBuilder("select * from bs_abnormity_info ainfo  " +
                " left join tbcha_perinfo pinfo on ainfo.person_id = pinfo.piid " +
                " left join sys_user_dept ud on ud.dept_id =pinfo.dept " +
                "where 1 = 1 " );

        if(userId != null){
            sql.append("and ud.user_id = "+ userId);
        }
        if(deptId != null){
            sql.append("and ud.dept_id = " +deptId);
        }
        if(abnType != null){
            sql.append(" and ainfo.abn_type = " + abnType);
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


        logger.info("根据行政人员获取对应的晚归查看信息sql : {}", sql);
        List<AbnormityInfo> result = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(AbnormityInfo.class).list();

        return result;
    }


    @Override
    public List<AbnormityInfo> findAbnormityBySg(Integer userId, Integer roomId, String startTime, String endTime, Integer abnType){
        if(StringUtils.hasText(startTime)){
            startTime= startTime+" 00:00:00.000";
        }
        if(StringUtils.hasText(endTime)){
            endTime = endTime +" 23:59:59.000";
        }
        Object[] arr = new Object[]{userId, roomId, startTime, endTime, abnType};
        logger.info("根据后勤人员获取对应的晚归数据：userId：{}，classId：{}，startTime：{}，endTime：{}, abnType:{}",arr );

        StringBuilder sql = new StringBuilder("select ainfo.* from bs_abnormity_info ainfo " +
                " left join tbcha_perinfo pinfo on ainfo.person_id = pinfo.piid " +
                " left join tbcha_room room on pinfo.room = room.roomid " +
                " left join sys_user_area ua on ua.area_id = room.area " +
                " where 1 = 1 ");
        if(userId != null){
            sql.append(" and ua.user_id =  " + userId);
        }

        if(roomId != null){
            sql.append(" and room.roomid = " + roomId);
        }
        if(abnType != null){
            sql.append(" and ainfo.abn_type = " + abnType);
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

        logger.info("根据后勤人员获取对应的晚归查看信息");
        List<AbnormityInfo> result = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(AbnormityInfo.class).list();
        return result;
    }
    @Override
    public int findAbnormityCountBySg(Integer userId, Integer roomId, String startTime, String endTime, Integer abnType){
        List<AbnormityInfo> laterResult = findAbnormityBySg(userId, roomId, startTime, endTime, abnType);
        return laterResult.size();
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
