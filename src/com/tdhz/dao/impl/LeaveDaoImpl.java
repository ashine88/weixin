package com.tdhz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.tdhz.dao.LeaveDao;
import com.tdhz.dao.SysParacfgDao;
import com.tdhz.dto.KqDetailItemDTO;
import com.tdhz.dto.KqDetailReqDTO;
import com.tdhz.pojo.*;
import com.tdhz.util.CommonUtil;
import com.tdhz.util.Page;
import com.tdhz.util.ParaConstant;
import com.tdhz.util.TypeConstant;

@Repository
public class LeaveDaoImpl extends HibernateDaoSupport implements LeaveDao{

	private static final Logger logger = LoggerFactory.getLogger(LeaveDaoImpl.class);
	@Autowired
	private SysParacfgDao sysParacfgDao;
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
	@Override
	public Page<KqDetailItemDTO> getPersonLeaveDetailBySg(KqDetailReqDTO reqDTO, Page page, List<String> leaveTypes){
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

		fromSql.append("    from bs_leave_info info  ")
				.append(" left join tbcha_perinfo pinfo on info.person_id = pinfo.piid  ")
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
		if(leaveTypes != null && leaveTypes.size() > 0){
			fromSql.append(" and leave_type in(");
			CommonUtil.appendParam(fromSql, leaveTypes);
			fromSql.append(" ) ");
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
		System.out.println(result.size());
		logger.info("查询到的总数为 countNum：{} ", countNum);
		page.setTotalNum(countNum);
		page.setItems(items);
		return page;

	}



	@Override
	public Page<KqDetailItemDTO> getPersonLeaveDetailByAss(KqDetailReqDTO reqDTO, Page page, List<String> leaveTypes){
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

		fromSql.append("    from bs_leave_info info  ")
				.append(" left join tbcha_perinfo pinfo on info.person_id = pinfo.piid  ")
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
		if(leaveTypes != null && leaveTypes.size() > 0){
			fromSql.append(" and leave_type in(");
			CommonUtil.appendParam(fromSql, leaveTypes);
			fromSql.append(" ) ");
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





	@Override
	public List<PersonLeave> getPersonLeaveByAss(Integer userId, Integer deptId, String startTime, String endTime, List<String> leaveTypes){
		logger.info("[行政口]根据条件获取个人请假信息 userId:{},deptId:{}, startTime:{} endTime:{} leaveTypes:{} ",new Object[]{userId, deptId, startTime, endTime, leaveTypes});

		StringBuilder sql = new StringBuilder("select info.* from bs_leave_info info ");
				sql.append(" left join tbcha_perinfo pinfo on info.person_id = pinfo.piid " );
						sql.append(" left join sys_user_dept ud on ud.dept_id = pinfo.dept " );
							sql.append(" where 1 = 1 ");
		if(null != userId){
			sql.append(" and ud.user_id = " + userId);
		}
		if(null != deptId){
			sql.append(" and pinfo.dept = " + deptId);
		}

		if(StringUtils.hasText(startTime)){
			startTime= startTime+" 00:00:00.000";
			sql.append(" and info.create_date >= '").append(startTime).append("'");
		}
		if(StringUtils.hasText(endTime)){
			endTime = endTime +" 23:59:59.000";
			sql.append(" and info.create_date <= '").append(endTime).append("'");
		}
		SysParacfg cfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		String groupValue = cfg.getParaValue();
		sql.append(" and pinfo.[group] in (").append(groupValue).append(")");

		if(null != leaveTypes && leaveTypes.size() > 0){
			sql.append(" and info.leave_type in(");
			CommonUtil.appendParam(sql, leaveTypes);
			sql.append(")");
		}
		sql.append(" and info.state = 1");

		List<PersonLeave> personLeaves = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(PersonLeave.class).list();
		logger.info("[行政口]获取的请假总数为:{}", personLeaves.size());
		return personLeaves;
	}



	@Override
	public List<PersonLeave> getPersonLeaveBySg(Integer userId, Integer roomId, String startTime, String endTime, List<String> leaveTypes){
		logger.info("[后勤口]根据条件获取个人请假信息 userId:{},roomId:{}, startTime:{} endTime:{} leaveTypes:{} ",new Object[]{userId, roomId, startTime, endTime, leaveTypes});

		StringBuilder sql = new StringBuilder("select info.* from bs_leave_info info ");
		sql.append(" left join tbcha_perinfo pinfo on info.person_id = pinfo.piid " );
		sql.append(" left join tbcha_room room on pinfo.room = room.roomid " );
		sql.append(" left join sys_user_area ua on ua.area_id = room.area ");
		sql.append(" where 1 = 1 ");
		/*
			注意这里是公寓id
		 */
		if(null != userId){
			sql.append(" and ua.user_id = " + userId);
		}
		if(null != roomId){
			sql.append(" and room.proom in  (select roomid from tbcha_room where proom = ").append(roomId).append(" ) ");
		}

		if(StringUtils.hasText(startTime)){
			startTime= startTime+" 00:00:00.000";
			sql.append(" and info.create_date >= '").append(startTime).append("'");
		}
		if(StringUtils.hasText(endTime)){
			endTime = endTime +" 23:59:59.000";
			sql.append(" and info.create_date <= '").append(endTime).append("'");
		}
		SysParacfg cfg = sysParacfgDao.get(ParaConstant.STUDENT_PSN_GROUPS);
		String groupValue = cfg.getParaValue();
		sql.append(" and pinfo.[group] in (").append(groupValue).append(")");

		if(null != leaveTypes && leaveTypes.size() > 0){
			sql.append(" and info.leave_type in(");
			CommonUtil.appendParam(sql, leaveTypes);
			sql.append(")");
		}
		sql.append(" and info.state = 1");

		List<PersonLeave> personLeaves = super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString()).addEntity(PersonLeave.class).list();
		logger.info("[后勤口]获取的请假总数为:{}", personLeaves.size());
		return personLeaves;
	}
	@Override
	public int getQJByAss(Integer userId, Integer deptId, String startTime, String endTime) {
		logger.info("[行政口]获取请假的总人数：userId:{} deptId:{} startTime:{} endTime:{}", new Object[]{userId, deptId, startTime, endTime});
		//TODO 确定请假的leavetype
		List<String> leaveTypes = CommonUtil.getQjLeaveTypes();
		List<PersonLeave> personLeaves = getPersonLeaveByAss(userId, deptId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[行政口]获取到的请假总人数：{}", count);
		return count;
	}

	@Override
	public int getQJBySg(Integer userId, Integer roomId, String startTime, String endTime) {
		logger.info("[后勤口]获取请假的总人数：userId:{} roomId:{} startTime:{} endTime:{}", new Object[]{userId, roomId, startTime, endTime});
		//TODO 确定请假的leavetype

		List<String> leaveTypes = CommonUtil.getQjLeaveTypes();
		List<PersonLeave> personLeaves = getPersonLeaveBySg(userId, roomId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[后勤口]获取到的请假总人数：{}", count);
		return count;
	}

	@Override
	public int getSXByAss(Integer userId, Integer deptId, String startTime, String endTime) {
		logger.info("[行政口]获取请假的总人数：userId:{} deptId:{} startTime:{} endTime:{}", new Object[]{userId, deptId, startTime, endTime});
		//TODO 确定实习的leavetype
		List<String> leaveTypes = CommonUtil.getSxLeaveTypes();

		List<PersonLeave> personLeaves = getPersonLeaveByAss(userId, deptId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[行政口]获取到的请假总人数：{}", count);
		return count;
	}

	@Override
	public int getSXBySg(Integer userId, Integer roomId, String startTime, String endTime) {
		logger.info("[后勤口]获取请假的总人数：userId:{} roomId:{} startTime:{} endTime:{}", new Object[]{userId, roomId, startTime, endTime});
		//TODO 确定实习的leavetype
		List<String> leaveTypes = CommonUtil.getSxLeaveTypes();

		List<PersonLeave> personLeaves = getPersonLeaveBySg(userId, roomId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[后勤口]获取到的请假总人数：{}", count);
		return count;
	}




}
