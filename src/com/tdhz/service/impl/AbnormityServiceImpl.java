package com.tdhz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tdhz.dao.AbnormityDao;
import com.tdhz.dao.ParacfgDao;
import com.tdhz.dao.UsersDeptDao;
import com.tdhz.pojo.*;
import com.tdhz.service.AbnormityService;
import com.tdhz.util.PageBean;

/**
 * 异常数据  未归异常，未出异常 ，无卡异常， 晚归异常
 * @author TD-PC
 *
 */
@Service
public class AbnormityServiceImpl implements AbnormityService {
	private static final Logger log = LoggerFactory.getLogger(AbnormityServiceImpl.class);
	@Resource
	private AbnormityDao abnormityDao;
	
	@Resource
	private UsersDeptDao usersDeptDao;
	
	@Resource
	private ParacfgDao paracfgDao;
	
	
	@Override
	public List<Tbcha_channeltg_14> findLaterInfo(String onday, PageBean pe,Integer userid) {
		//定义最终需要显示的信息
		List<Tbcha_channeltg_14> finalist = new ArrayList<Tbcha_channeltg_14>();
		List<Dept> deptlist = new ArrayList<Dept>();
		//查找晚归参考时间
//		Paracfg paracfg = paracfgDao.getById(ParaConstant.BACK_LATER_TIME);
		
		//根据辅导员信息查找负责的所有部门ID
		List<User_Dept> udlist = usersDeptDao.findByUserId(userid);
		if(udlist.size()>0){
			for(int i=0;i<udlist.size();i++){
				//得到部门集合
				Dept dept = udlist.get(i).getDept_id();
				
				deptlist.add(dept);
			}
		}
		List<Tbcha_channeltg_14> alist = abnormityDao.findLaterInfo(onday, onday ,pe);
		if(alist.size()>0){
			
			for(int n=0;n<alist.size();n++){
				//筛选出该辅导员负责的班级信息
				for(int m=0;m<deptlist.size();m++){
					System.out.println();
					if(alist.get(n).getPerInfo().getDept().getFullName().equals(deptlist.get(m).getFullName())){
						finalist.add(alist.get(n));
					}
					
				}
			}
		}
		return finalist;
	}
	@Override
	public List<AbnormityInfo> findAbnInfoByAss(String onday, PageBean pe,Integer userid) {
		//定义最终显示的信息
		List<AbnormityInfo> finalist = new ArrayList<AbnormityInfo>();
		List<Dept> deptlist = new ArrayList<Dept>();
		
		//根据辅导员信息查找负责的所有部门ID
		List<User_Dept> udlist = usersDeptDao.findByUserId(userid);
		if(udlist.size()>0){
			for(int i=0;i<udlist.size();i++){
				//得到部门集合
				Dept dept = udlist.get(i).getDept_id();
				
				deptlist.add(dept);
			}
		}
		//根据日期 查询当天的所有异常信息
		List<AbnormityInfo> alist =null;
		
		if(alist.size()>0){
			
			for(int n=0;n<alist.size();n++){
				//筛选出该辅导员负责的班级信息
				for(int m=0;m<deptlist.size();m++){
					System.out.println();
					if(alist.get(n).getPerson_id().getDept().getFullName().equals(deptlist.get(m).getFullName())){
						finalist.add(alist.get(n));
					}
					
				}
			}
		}
		return finalist;
	}
	@Override
	public List<AbnormityInfo> findLaterInfo(String onday, PageBean pe) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//辅导员查看晚归
	@Override
	public List<Object[]> findLaterInfoByAss(String onday, Integer userid) {
		//根据人员 查找所有负责部门ID集合
		String deptIds = "";
		List<User_Dept> udlist = usersDeptDao.findByUserId(userid);
		if(udlist.size()>0){
			for(int i=0;i<udlist.size();i++){
				deptIds = deptIds + udlist.get(i).getDept_id().getDeptId() +",";
			}
		}
		deptIds = deptIds.substring(0,deptIds.length() - 1);
		log.info("操作员ID"+userid+"负责部门id("+deptIds);
		//定义晚归时间
		Paracfg paracfg = paracfgDao.getById(2);		
		String begin = onday +" "+paracfg.getPara_value();
		String end = onday +" 23:59:59";
		StringBuilder sb = new StringBuilder();
		sb.append("select PIID,PINAME,FULLDEPTNAME,TGTIME from tbcha_channeltg_14 c left join TBCHA_PERINFO p on c.PERINFO = p.PIID left join TBCHA_DEPT d on p.DEPT = d.DEPTID ");
		sb.append(" where TGTIME between '"+begin+"' and '"+end+"' and INOUT='4_1'");
		sb.append("and p.DEPT in("+deptIds +") order by FULLDEPTNAME" );
		
		String sql = sb.toString();
		return abnormityDao.findLaterBackByAss(sql);
	}
	@Override
	public List<Object[]> findAbnInfoByAss(String onday, Integer userid) {
		//根据人员 查找所有负责部门ID集合
		String deptIds = "";
		List<User_Dept> udlist = usersDeptDao.findByUserId(userid);
		if(udlist.size()>0){
			for(int i=0;i<udlist.size();i++){
				deptIds = deptIds + udlist.get(i).getDept_id().getDeptId() +",";
			}
		}
		deptIds = deptIds.substring(0,deptIds.length() - 1);
		log.info("操作员ID"+userid+"负责部门id("+deptIds);
		String begin = onday +" 00:00:00";
		String end = onday +" 23:59:59";
		StringBuilder sb = new StringBuilder();
		sb.append("select PIID,PINAME,FULLDEPTNAME,last_tg_datetime,abn_type from  bs_abnormity_info c left join TBCHA_PERINFO p on c.person_id = p.PIID left join TBCHA_DEPT d on p.DEPT = d.DEPTID ");
		sb.append(" where create_date between '"+begin+"' and '"+end+"'");
		sb.append("and p.DEPT in ("+ deptIds + ") order by FULLDEPTNAME");
		String sql = sb.toString();
		return abnormityDao.findCarAbn(sql);
	}

}
