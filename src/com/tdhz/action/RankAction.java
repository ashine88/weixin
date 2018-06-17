package com.tdhz.action;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tdhz.pojo.RankInfo;
import com.tdhz.service.AlertInfoService;
import com.tdhz.service.UsersService;

@Controller
@Scope("prototype")
public class RankAction {
	
	@Autowired
	private UsersService userService;
	@Autowired
	private AlertInfoService alertInfoService;
	
	private List<RankInfo> rlist = new ArrayList<RankInfo>();
	private String onday = "2017-05-05";
	
	public List<RankInfo> getRlist() {
		return rlist;
	}
	public void setRlist(List<RankInfo> rlist) {
		this.rlist = rlist;
	}
	//辅导员查看
	public String rankByAss(){
		
		List<Object[]> list = new ArrayList<Object[]>();
		List<Object[]> listAllCount = new ArrayList<Object[]>();
		List<Object[]> listNotCount = new ArrayList<Object[]>();
		//定义日期
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(onday) ||onday == null){			
			onday=sdf.format(now);
		}
		//查看所有辅导员列表		
		list = userService.findAllAss();
		System.out.println(list.size()+"=========");
		if(list.size()>0){
			for(int i=0;i<list.size();i++){		
				RankInfo r = new RankInfo();
				r.setUserId(((Integer) list.get(i)[0]));
				r.setUsername(list.get(i)[1].toString());
				r.setRolename(list.get(i)[2].toString());
				r.setDeptname(list.get(i)[3].toString());
				r.setFulldeptname(list.get(i)[4].toString());
//				System.out.println(list.get(i)[4].toString());
				//查看每个辅导员负责班级的预警人员总数
				listAllCount = alertInfoService.findAlertByAss(onday,(Integer) list.get(i)[0]);
				//查看每个辅导员负责班级的已经处理的人员总数
				listNotCount = alertInfoService.findAlertByAss2(onday,(Integer) list.get(i)[0]);
				
				r.setPersonAllCount(listAllCount.size());
				r.setPersonNotCount(listNotCount.size());
				if(listNotCount.size()==0){
					r.setDeal("0");
				}else{
					//创建一个数值格式化对象
					NumberFormat numberFormat = NumberFormat.getInstance();
					//设置精确到小数点后2位
					numberFormat.setMaximumFractionDigits(2);
					String result = numberFormat.format((float) listNotCount.size() / (float) listAllCount.size() * 100);
					r.setDeal(result);
				}				
				rlist.add(r);
			}
		}		
				
		//处理排名顺序
		Collections.sort(rlist,new Comparator<RankInfo>(){
			public int compare(RankInfo r1, RankInfo r2) { 		         
		         //按照以处理人数进行降序排列  
		         if(r1.getPersonNotCount() < r2.getPersonNotCount()){  
		             return 1;  
		         }  
		         if(r1.getPersonNotCount() == r2.getPersonNotCount()){  
		             return 0;  
		         }  
		         return -1;  
		     } 			
		});
		return "assRank";
	}
	
	//学生处领导查看
	public String rankByChief(){
		List<Object[]> list = new ArrayList<Object[]>();
		List<Object[]> listAllCount = new ArrayList<Object[]>();
		List<Object[]> listNotCount = new ArrayList<Object[]>();
		//定义日期
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(onday) ||onday == null){			
			onday=sdf.format(now);
		}
		//查看所有辅导员列表		
		list = userService.findAllAss();
		System.out.println(list.size()+"=========");
		if(list.size()>0){
			for(int i=0;i<list.size();i++){		
				RankInfo r = new RankInfo();
				r.setUserId(((Integer) list.get(i)[0]));
				r.setUsername(list.get(i)[1].toString());
				r.setRolename(list.get(i)[2].toString());
				r.setDeptname(list.get(i)[3].toString());
				r.setFulldeptname(list.get(i)[4].toString());
				
				//查看每个辅导员负责班级的预警人员总数
				listAllCount = alertInfoService.findAlertByAss(onday,(Integer) list.get(i)[0]);
				//查看每个辅导员负责班级的已经处理的人员总数
				listNotCount = alertInfoService.findAlertByAss2(onday,(Integer) list.get(i)[0]);
				
				r.setPersonAllCount(listAllCount.size());
				r.setPersonNotCount(listNotCount.size());
				if(listNotCount.size()==0){
					r.setDeal("0");
				}else{
					//创建一个数值格式化对象
					NumberFormat numberFormat = NumberFormat.getInstance();
					//设置精确到小数点后2位
					numberFormat.setMaximumFractionDigits(2);
					String result = numberFormat.format((float) listNotCount.size() / (float) listAllCount.size() * 100);
					r.setDeal(result);
				}				
				rlist.add(r);
			}
		}
		//处理排名顺序
		Collections.sort(rlist,new Comparator<RankInfo>(){
			public int compare(RankInfo r1, RankInfo r2) { 		         
		         //按照以处理人数进行降序排列  
		         if(r1.getPersonNotCount() < r2.getPersonNotCount()){  
		             return 1;  
		         }  
		         if(r1.getPersonNotCount() == r2.getPersonNotCount()){  
		             return 0;  
		         }  
		         return -1;  
		     } 			
		});
		return "assRankByChief";
	}
	
	
	//楼栋排名顺序
	public String rankBuildByChief(){
		//查看所有楼栋
		
		
		return null;
	}
}
