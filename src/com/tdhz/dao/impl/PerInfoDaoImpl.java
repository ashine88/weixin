package com.tdhz.dao.impl;

import java.util.List;

import com.tdhz.dao.SysParacfgDao;
import com.tdhz.pojo.SysParacfg;
import com.tdhz.util.ParaConstant;
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

	@Autowired
	private SysParacfgDao sysParacfgDao;

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

	@Override
	public List<PerInfo> getByAss(Integer userId, Integer deptId) {
		StringBuilder sql = new StringBuilder("select pinfo.* from tbcha_perinfo pinfo ");
		sql.append(" left join sys_user_dept ud on ud.dept_id = pinfo.dept " );
		sql.append("where pinfo.isdel = 0 ");
		SysParacfg stuGroupCfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		sql.append( " and pinfo.[group] in (").append(stuGroupCfg.getParaValue()).append(") ");

		if(userId != null ){
			sql.append(" and ud.user_id = ").append(userId);
		}
		if(deptId != null){
			sql.append(" and ud.dept_id = ").append(deptId);
		}

		List<PerInfo> perInfos = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(PerInfo.class).list();
		return perInfos;
	}
	@Override
	public int getCountByAss(Integer userId, Integer deptId) {
		return getByAss(userId, deptId).size();
	}
	@Override
	public int getCountBySg(Integer userId, Integer roomId){
		return getBySg(userId, roomId).size();
	}
	@Override
	public List<PerInfo> getBySg(Integer userId, Integer roomId) {
		StringBuilder sql = new StringBuilder("select pinfo.* from tbcha_perinfo pinfo ");
		sql.append(" left join tbcha_room room on pinfo.room = room.roomid " );
		sql.append(" left join sys_user_area ua on ua.area_id = room.area ");
		sql.append("where pinfo.isdel = 0 ");
		SysParacfg stuGroupCfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		sql.append( " and pinfo.[group] in (").append(stuGroupCfg.getParaValue()).append(") ");

		if(userId != null ){
			sql.append(" and ua.user_id = ").append(userId);
		}
		if(roomId != null){
			sql.append(" and room.roomid = ").append(roomId);
		}

		List<PerInfo> perInfos = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(PerInfo.class).list();
		return perInfos;
	}


}
