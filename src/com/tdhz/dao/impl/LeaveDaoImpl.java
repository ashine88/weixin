package com.tdhz.dao.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.tdhz.pojo.*;
import com.tdhz.util.CommonUtil;
import com.tdhz.util.TypeConstant;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.tdhz.dao.LeaveDao;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public class LeaveDaoImpl extends HibernateDaoSupport implements LeaveDao{

	private static final Logger logger = LoggerFactory.getLogger(LeaveDaoImpl.class);
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
		if(null != leaveTypes && leaveTypes.size() > 0){
			int length = leaveTypes.size();
			sql.append(" and info.leave_type in(");
			for(int i = 0; i < length; i++){
				if(i == length - 1){
					sql.append("'").append(leaveTypes.get(i)).append("'");
				}else{
					sql.append("'").append(leaveTypes.get(i)).append("', ");
				}
			}
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
		if(null != userId){
			sql.append(" and ua.user_id = " + userId);
		}
		if(null != roomId){
			sql.append(" and room.roomid = " + roomId);
		}

		if(StringUtils.hasText(startTime)){
			startTime= startTime+" 00:00:00.000";
			sql.append(" and info.create_date >= '").append(startTime).append("'");
		}
		if(StringUtils.hasText(endTime)){
			endTime = endTime +" 23:59:59.000";
			sql.append(" and info.create_date <= '").append(endTime).append("'");
		}
		if(null != leaveTypes && leaveTypes.size() > 0){
			int length = leaveTypes.size();
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
		List<String> leaveTypes = getQjLeaveTypes();
		List<PersonLeave> personLeaves = getPersonLeaveByAss(userId, deptId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[行政口]获取到的请假总人数：{}", count);
		return count;
	}

	@Override
	public int getQJBySg(Integer userId, Integer roomId, String startTime, String endTime) {
		logger.info("[后勤口]获取请假的总人数：userId:{} roomId:{} startTime:{} endTime:{}", new Object[]{userId, roomId, startTime, endTime});
		//TODO 确定请假的leavetype

		List<String> leaveTypes = getQjLeaveTypes();
		List<PersonLeave> personLeaves = getPersonLeaveBySg(userId, roomId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[后勤口]获取到的请假总人数：{}", count);
		return count;
	}

	@Override
	public int getSXByAss(Integer userId, Integer deptId, String startTime, String endTime) {
		logger.info("[行政口]获取请假的总人数：userId:{} deptId:{} startTime:{} endTime:{}", new Object[]{userId, deptId, startTime, endTime});
		//TODO 确定实习的leavetype
		List<String> leaveTypes = getSxLeaveTypes();

		List<PersonLeave> personLeaves = getPersonLeaveByAss(userId, deptId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[行政口]获取到的请假总人数：{}", count);
		return count;
	}

	@Override
	public int getSXBySg(Integer userId, Integer roomId, String startTime, String endTime) {
		logger.info("[后勤口]获取请假的总人数：userId:{} roomId:{} startTime:{} endTime:{}", new Object[]{userId, roomId, startTime, endTime});
		//TODO 确定实习的leavetype
		List<String> leaveTypes = new ArrayList<>();
		// 个人请假：实习
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_WORK);

		List<PersonLeave> personLeaves = getPersonLeaveBySg(userId, roomId, startTime, endTime, leaveTypes);
		int count = personLeaves.size();
		logger.info("[后勤口]获取到的请假总人数：{}", count);
		return count;
	}

	private List<String> getQjLeaveTypes(){
		/**
		 * 个人请假：病假 HOLIDAY_PERSON_ILL = "1";

		 * 个人请假：事假 HOLIDAY_PERSON_ABSENCE = "2";
		 *
		 * 个人请假：其他 HOLIDAY_PERSON_OTHER = "4";
		 *
		 */
		List<String> leaveTypes = new ArrayList<>();
		// 病假
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_ILL);
		// 事假
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_ABSENCE);
		// 其他
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_OTHER);
		return leaveTypes;
	}

	/**
	 * 获取实习的leaveTypes
	 * @return
	 */
	private List<String> getSxLeaveTypes(){
		List<String> leaveTypes = new ArrayList<>();
		// 个人请假：实习
		leaveTypes.add(TypeConstant.HOLIDAY_PERSON_WORK);
		return leaveTypes;
	}

}
