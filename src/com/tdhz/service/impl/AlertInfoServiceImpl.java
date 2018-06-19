package com.tdhz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.tdhz.util.TypeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tdhz.dao.AlertInfoDao;
import com.tdhz.dao.UsersDeptDao;
import com.tdhz.pojo.AlertInfo;
import com.tdhz.pojo.Dept;
import com.tdhz.pojo.User_Dept;
import com.tdhz.service.AlertInfoService;
import com.tdhz.util.PageBean;

/**
 * 预警  未归预警、未出预警
 * @author TD-PC
 *
 */
@Service
public class AlertInfoServiceImpl implements AlertInfoService{
	
	private static final Logger log = LoggerFactory.getLogger(AlertInfoServiceImpl.class);
	@Resource
	private AlertInfoDao alertInfoDao;

	@Resource
	private UsersDeptDao usersDeptDao;
	
	/**
	 * 辅导员角色功能
	 */
	@Override
	public List<AlertInfo> findAlertByAss(String onday,PageBean pe,Integer userid) {
		//定义最终显示的信息
		List<AlertInfo> finalist = new ArrayList<AlertInfo>();
		List<Dept> deptlist = new ArrayList<Dept>();
//		Integer userid = 5;
		//根据辅导员信息查找负责的所有部门ID
		List<User_Dept> udlist = usersDeptDao.findByUserId(userid);
		if(udlist.size()>0){
			for(int i=0;i<udlist.size();i++){
				//得到部门集合
				Dept dept = udlist.get(i).getDept_id();
				
				deptlist.add(dept);
			}	
//			System.out.println(deptlist.get(4).getDeptName());
		}
		//根据日期查找出当天的所有预警信息
		List<AlertInfo> alist = alertInfoDao.findAll(onday);		
		
		if(alist.size()>0){
			
			for(int n=0;n<alist.size();n++){
//				System.out.println(alist.get(n).getPerson_id().getDept());
				
//				if(alist.get(n).getPerson_id().getDept().getDeptName().equals(deptlist.get(4).getDeptName())){
//					System.out.println("===========");
//					return null;
//				}
				//筛选出该辅导员负责的班级信息
				for(int m=0;m<deptlist.size();m++){
					if(alist.get(n).getPerson_id().getDept().getFullName().equals(deptlist.get(m).getFullName())){
						finalist.add(alist.get(n));
					}
					
				}
//				if(deptlist.contains(alist.get(n).getPerson_id().getDept())){
//					finalist.add(alist.get(n));
//				}
			}
		}
//		System.out.println(finalist.size());
		return finalist;
	}

	@Override
	public AlertInfo findById(Integer alert_info_id) {
		
		return alertInfoDao.findById(alert_info_id);
	}

	@Override
	public void updateAler(AlertInfo alertInfo) {
		
		alertInfoDao.updateAlert(alertInfo);
	}

	@Override
	public List<AlertInfo> findAlertByChief(String onday, PageBean pe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findAlertByAss(String onday, Integer userid) {
		//根据人员 查找所有负责部门ID集合
		String deptIds = "";
		List<User_Dept> udlist = usersDeptDao.findByUserId(userid);
		if(udlist.size()>0){
			for(int i=0;i<udlist.size();i++){
				deptIds = deptIds + udlist.get(i).getDept_id().getDeptId() +",";
			}
		}
		deptIds = deptIds.substring(0,deptIds.length() - 1);
		log.info("操作员ID"+userid+"负责部门 （"+deptIds);
		String begin = onday +" 00:00:;00";
		String end = onday +" 23:59:59";
		StringBuilder sb = new StringBuilder();
		sb.append("select PIID , PINAME,DEPTNAME,FULLDEPTNAME, LASTTGINOUT,last_tg_datetime,alert_type,alert_state ,alert_info_id from bs_psn_alert_info a left join TBCHA_PERINFO p on  a.person_id = p.PIID  left join TBCHA_DEPT dept on p.DEPT = dept.DEPTID");
		sb.append(" where a.create_date between '"+ begin +"' and '"+ end +"'");
		sb.append(" and p.DEPT in("+deptIds+") order by FULLDEPTNAME");
		String sql = sb.toString();
		return alertInfoDao.findAlertByAss(sql);
	}

	@Override
	public List<Object[]> findAlertByAss2(String onday, Integer userid) {
		//根据人员 查找所有负责部门ID集合
		String deptIds = "";
		List<User_Dept> udlist = usersDeptDao.findByUserId(userid);
		if(udlist.size()>0){
			for(int i=0;i<udlist.size();i++){
				deptIds = deptIds + udlist.get(i).getDept_id().getDeptId() +",";
			}
		}
		deptIds = deptIds.substring(0,deptIds.length() - 1);
		
		String begin = onday +" 00:00:;00";
		String end = onday +" 23:59:59";
		StringBuilder sb = new StringBuilder();
		sb.append("select PIID , PINAME,DEPTNAME,FULLDEPTNAME, LASTTGINOUT,last_tg_datetime,alert_type,alert_state ,alert_info_id from bs_psn_alert_info a left join TBCHA_PERINFO p on  a.person_id = p.PIID  left join TBCHA_DEPT dept on p.DEPT = dept.DEPTID");
		sb.append(" where a.create_date between '"+ begin +"' and '"+ end +"'");
		sb.append(" and p.DEPT in("+deptIds+")  and alert_state = 'INACTIVE' order by FULLDEPTNAME");
		String sql = sb.toString();
		return alertInfoDao.findAlertByAss(sql);
	}

	@Override
	public List<Object[]> findAlertByChief(String onday) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getWcByAss(Integer userId, Integer deptId, String startTime, String endTime) {
		int wc = alertInfoDao.findAlertCountByAss(userId, deptId, startTime, endTime, TypeConstant.ALERT_TYPE_NO_OUT);
		log.info("获取到的未出总数：{}", wc);

		return wc;
	}

	@Override
	public int getWgByAss(Integer userId, Integer deptId, String startTime, String endTime) {
		int wg = alertInfoDao.findAlertCountByAss(userId, deptId, startTime, endTime, TypeConstant.ALERT_TYPE_NO_BACK);
		log.info("获取到的未出总数：{}", wg);
		return wg;
	}

	@Override
	public int getZxByAss(Integer userId, Integer deptId, String startTime, String endTime) {
		int wc = alertInfoDao.findAlertCountByAss(userId, deptId, startTime, endTime, TypeConstant.ALERT_TYPE_NO_OUT);
		log.info("获取到的未出总数：{}", wc);

		return wc;
	}
}
