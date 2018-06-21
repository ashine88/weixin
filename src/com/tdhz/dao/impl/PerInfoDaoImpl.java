package com.tdhz.dao.impl;

import java.util.List;

import com.tdhz.dao.SysParacfgDao;
import com.tdhz.dto.KqDetailItemDTO;
import com.tdhz.dto.KqDetailReqDTO;
import com.tdhz.pojo.SysParacfg;
import com.tdhz.util.CommonUtil;
import com.tdhz.util.Page;
import com.tdhz.util.ParaConstant;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.PerInfoDao;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.PerInfo;
import org.springframework.util.StringUtils;

@Repository
public class PerInfoDaoImpl extends HibernateDaoSupport implements PerInfoDao{
	public static final Logger logger = LoggerFactory.getLogger(PerInfoDaoImpl.class);
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


	@Override
	public Page<KqDetailItemDTO> getPersonLeaveDetailBySg(KqDetailReqDTO reqDTO, Page page) {
		/**
		 * 公寓id
		 */
		Integer roomId = reqDTO.getRoomId();
		Integer userId = reqDTO.getUserId();
		String startTime = reqDTO.getStartTime();
		String endTime = reqDTO.getEndTime();
		if(page.getPageNum() == null) page.setPageNum(1);
		if(page.getPageSize() == null) page.setPageSize(10);


		StringBuilder fromSql = new StringBuilder("");

		fromSql.append(" select pinfo.piid,")
				.append(" pinfo.piname, ")
				.append(" pinfo.credno1, ")
				.append(" room.roomname as roomname,")
				.append(" apartment.roomname as apartmentname,")
				.append(" first_dept.deptname as classname,")
				.append(" second_dept.deptname as collegename");

		fromSql.append("    from  tbcha_perinfo pinfo   ")
				.append(" left join tbcha_room room on pinfo.room = room.roomid  ")
				.append(" left join sys_user_area ua on ua.area_id = room.area ")
				.append(" left join tbcha_room apartment_floor on room.proom = apartment_floor.roomid ")
				.append(" left join tbcha_room apartment on apartment_floor.proom = apartment.roomid ")
				.append(" left join tbcha_dept first_dept on pinfo.dept = first_dept.deptid ")
				.append(" left join tbcha_dept second_dept on first_dept.pdept = second_dept.deptid ");
		fromSql.append(" where 1 = 1");
		if(userId != null){
			fromSql.append(" and ua.user_id = ").append( userId );
		}
		if(roomId != null){
			fromSql.append(" and apartment.roomid = ").append( roomId );
		}


		if(StringUtils.hasText(startTime)){
			startTime= startTime+" 00:00:00.000";
			fromSql.append(" and info.create_date >= '").append(startTime).append("'");
		}
		if(StringUtils.hasText(endTime)){
			endTime = endTime +" 23:59:59.000";
			fromSql.append(" and info.create_date <= '").append(endTime).append("'");
		}
		SysParacfg cfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		String groupValue = cfg.getParaValue();
		fromSql.append(" and pinfo.[group] in (").append(groupValue).append(")");

		Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(fromSql.toString());

		//得到滚动结果集
		ScrollableResults scroll = query.scroll();
		//滚动到最后一行
		scroll.last();
		int countNum = scroll.getRowNumber() + 1;
		logger.info("总计路数：{}", countNum);

		//设置分页位置
		int firstResult = (page.getPageNum() -1 ) * page.getPageSize();
		int maxResult = page.getPageSize();
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		List<Object[]> result = query.list();
		List<KqDetailItemDTO> items = CommonUtil.get(result);
		logger.info("查询到的总数为 countNum：{} ", countNum);
		page.setTotalNum(countNum);
		page.setItems(items);
		return page;

	}

	@Override
	public Page<KqDetailItemDTO> getPersonLeaveDetailByAss(KqDetailReqDTO reqDTO, Page page) {
		/**
		 * 公寓id
		 */
		Integer deptId = reqDTO.getDeptId();
		Integer userId = reqDTO.getUserId();
		String startTime = reqDTO.getStartTime();
		String endTime = reqDTO.getEndTime();
		if(page.getPageNum() == null) page.setPageNum(1);
		if(page.getPageSize() == null) page.setPageSize(10);


		StringBuilder fromSql = new StringBuilder("");

		fromSql.append(" select pinfo.piid,")
				.append(" pinfo.piname, ")
				.append(" pinfo.credno1, ")
				.append(" room.roomname as roomname,")
				.append(" apartment.roomname as apartmentname,")
				.append(" first_dept.deptname as classname,")
				.append(" second_dept.deptname as collegename ");

		fromSql.append("    from tbcha_perinfo pinfo   ")
				.append(" left join sys_user_dept ud on ud.dept_id = pinfo.dept  ")
				.append(" left join tbcha_dept first_dept on pinfo.dept = first_dept.deptid ")
				.append(" left join tbcha_dept second_dept on first_dept.pdept = second_dept.deptid ")
				.append(" left join tbcha_room room on pinfo.room = room.roomid ")
				.append(" left join tbcha_room apartment_floor on room.proom = apartment_floor.roomid ")
				.append(" left join tbcha_room apartment on apartment_floor.proom = apartment.roomid ");
		fromSql.append(" where 1 = 1");
		if(userId != null){
			fromSql.append(" and ud.user_id = ").append( userId );
		}
		if(deptId != null){
			fromSql.append(" and pinfo.deptid = ").append( deptId );
		}

		if(StringUtils.hasText(startTime)){
			startTime= startTime+" 00:00:00.000";
			fromSql.append(" and info.create_date >= '").append(startTime).append("'");
		}
		if(StringUtils.hasText(endTime)){
			endTime = endTime +" 23:59:59.000";
			fromSql.append(" and info.create_date <= '").append(endTime).append("'");
		}
		SysParacfg cfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		String groupValue = cfg.getParaValue();
		fromSql.append(" and pinfo.[group] in (").append(groupValue).append(")");

		Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(fromSql.toString());

		//得到滚动结果集
		ScrollableResults scroll = query.scroll();
		//滚动到最后一行
		scroll.last();
		int countNum = scroll.getRowNumber() + 1;
		logger.info("总计路数：{}", countNum);

		//设置分页位置
		int firstResult = (page.getPageNum() -1 ) * page.getPageSize();
		int maxResult = page.getPageSize();
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		List<Object[]> result = query.list();
		logger.info("查询到的总数为 countNum：{} ", countNum);
		page.setTotalNum(countNum);
		List<KqDetailItemDTO> items = CommonUtil.get(result);
		page.setItems(items);

		return page;
	}
}
