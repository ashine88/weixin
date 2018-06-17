package com.tdhz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tdhz.dao.AlertInfoDao;
import com.tdhz.pojo.AlertInfo;
import com.tdhz.pojo.PerInfo;
import com.tdhz.service.AlertInfoService;
import com.tdhz.service.PerInfoService;
import com.tdhz.util.PageBean;

@Controller
@Scope("prototype")
public class AlertInfoAction {

	private static final Logger log = LoggerFactory.getLogger(AlertInfoAction.class);
	@Autowired
	private AlertInfoService alertInfoService;
	@Autowired
	private PerInfoService perInfoService;
	@Autowired
	private AlertInfoDao alertInfoDao;
	private AlertInfo alertInfo;
	private List<AlertInfo> alist = new ArrayList<AlertInfo>();
	private PerInfo perInfo;
	private String onday;
	private PageBean pe; //分页信息
	public PerInfo getPerInfo() {
		return perInfo;
	}

	public void setPerInfo(PerInfo perInfo) {
		this.perInfo = perInfo;
	}
	public AlertInfo getAlertInfo() {
		return alertInfo;
	}

	public void setAlertInfo(AlertInfo alertInfo) {
		this.alertInfo = alertInfo;
	}
	public List<AlertInfo> getAlist() {
		return alist;
	}

	public void setAlist(List<AlertInfo> alist) {
		this.alist = alist;
	}
	
	public String getOnday() {
		return onday;
	}

	public void setOnday(String onday) {
		this.onday = onday;
	}

	public PageBean getPe() {
		return pe;
	}

	public void setPe(PageBean pe) {
		this.pe = pe;
	}
	//预警查看(辅导员)
	public String findAlert(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();	
		Integer userid = (Integer) session.getAttribute("userid");
		//定义日期
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(onday) ||onday == null){			
			onday=sdf.format(now);
		}
		List<Object[]> list = alertInfoService.findAlertByAss(onday,userid);
		log.info("辅导员ID（"+userid+"）-=================查看预警信息,共"+list.size());
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				AlertInfo alert = new AlertInfo();
				alert.setPiid((Integer) list.get(i)[0]);
				alert.setPiname(list.get(i)[1].toString());
				String DeptName = (list.get(i)[3].toString());
				DeptName = DeptName.substring(DeptName.indexOf("_",DeptName.indexOf("_")+1) + 1,DeptName.length());
				alert.setDeptName(DeptName);
				alert.setInout(list.get(i)[4].toString());
				alert.setLasttgtime(list.get(i)[5].toString());
				alert.setAlert_type((Integer) list.get(i)[6]);
				alert.setAlert_state(list.get(i)[7].toString());
				alert.setAlert_info_id((Integer) list.get(i)[8]);
				alist.add(alert);
			}
		}
		
		//分页
//		 String p=null;
		int page;
        try {
            //当前页数
            page = Integer.valueOf(pe.getPageNum());
        } catch (NumberFormatException e) {
            page = 1;
        }
		 //总数
        int total = alist.size();
        //每页显示数
        int perPage = 10;
        //总页数
        int totalPages = total % perPage == 0 ? total / perPage : total / perPage + 1;
        //本页起始序号
        int beginIndex = (page - 1) * perPage;
        //本页末尾序号的下一个
        int endIndex = beginIndex + perPage;
        if (endIndex > total){
        	endIndex = total;
        }
        
        pe.setTotalNum(total);
        pe.setMaxPage(totalPages);
        pe.setPageSize(perPage);
        pe.setPageNum(page);
        pe.setBeginIndex(beginIndex);
        pe.setEndIndex(endIndex);
        
//		long total = alertInfoDao.findAlertCount(onday);
//		int rws=Integer.parseInt(String.valueOf(total));
		//定义每页显示条数
//		int pageSize=10; 		
		//获得最大页数
//		int maxPage =  rws%pageSize==0?rws/pageSize:(rws/pageSize)+1;
		
//		pe.setPageSize(pageSize);
//		pe.setMaxPage(maxPage);
//		pe.setTotalNum(rws);	
//		alist = alertInfoService.findAlertByAss(onday,pe,userid);
//		for(int i=0;i<alist.size();i++){
//			alertInfo = alist.get(i);
//			perInfo = perInfoService.findById(alertInfo.getPerson_id().getPiid());
//			String pdeptName = perInfoService.findpDept(perInfo.getDept().getPdept()).getDeptName();
//			perInfo.setPdeptName(pdeptName);
//			alertInfo.setPerson_id(perInfo);
//			alist.set(i, alertInfo);
//		}
		return "alist";
	}
	//预警查看(学生处)
	public String findAlertByChief(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();	
		Integer userid = (Integer) session.getAttribute("userid");
		//定义日期
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if("".equals(onday) ||onday == null){			
			onday=sdf.format(now);
		}
		List<Object[]> list = alertInfoService.findAlertByAss(onday,userid);
		log.info("学生处领导ID（"+userid+"）-=================查看预警信息,共"+list.size());
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				AlertInfo alert = new AlertInfo();
				alert.setPiid((Integer) list.get(i)[0]);
				alert.setPiname(list.get(i)[1].toString());
				String DeptName = (list.get(i)[3].toString());
				DeptName = DeptName.substring(DeptName.indexOf("_",DeptName.indexOf("_")+1) + 1,DeptName.length());
				alert.setDeptName(DeptName);
				alert.setInout(list.get(i)[4].toString());
				alert.setLasttgtime(list.get(i)[5].toString());
				alert.setAlert_type((Integer) list.get(i)[6]);
				alert.setAlert_state(list.get(i)[7].toString());
				alert.setAlert_info_id((Integer) list.get(i)[8]);
				alist.add(alert);
			}
		}
		
		//分页
//				 String p=null;
		int page;
        try {
            //当前页数
            page = Integer.valueOf(pe.getPageNum());
        } catch (NumberFormatException e) {
            page = 1;
        }
		 //总数
        int total = alist.size();
        //每页显示数
        int perPage = 10;
        //总页数
        int totalPages = total % perPage == 0 ? total / perPage : total / perPage + 1;
        //本页起始序号
        int beginIndex = (page - 1) * perPage;
        //本页末尾序号的下一个
        int endIndex = beginIndex + perPage;
        if (endIndex > total){
        	endIndex = total;
        }
        
        pe.setTotalNum(total);
        pe.setMaxPage(totalPages);
        pe.setPageSize(perPage);
        pe.setPageNum(page);
        pe.setBeginIndex(beginIndex);
        pe.setEndIndex(endIndex);
        
		return "xsc_alist";
	}
	//预警处理
	public String updatePer(){
//		perInfo = perInfoService.findById(perInfo.getPiid());
//		perInfo.setIsDel(1);
//		perInfoService.updatePer(perInfo);
//		alertInfoService.updateAler(alertInfo);
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Integer userid = (Integer) session.getAttribute("userid" );	
		alertInfo = alertInfoService.findById(alertInfo.getAlert_info_id());
		alertInfo.setCheck_reason(alertInfo.getCheck_reason());
		alertInfo.setCheck_oper(userid);
		alertInfo.setAlert_state("INACTIVE");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String check_date = sdf.format(date);
		alertInfo.setCheck_date(check_date);
		log.info("辅导员ID（"+userid+"对预警信息进行处理===="+alertInfo.toString());
		alertInfoService.updateAler(alertInfo);
		log.info("更新预警信息");
		return "findAlert";
	}
	
	//更新预警处理原因
	public String preupdatePer(){
		alertInfo = alertInfoService.findById(alertInfo.getAlert_info_id());
		perInfo = perInfoService.findById(alertInfo.getPerson_id().getPiid());
		return "updateAlertReason";
	}
	
	//查看处理详情
	public String selectAlert(){
		log.info("查看预警处理详情");
		alertInfo = alertInfoService.findById(alertInfo.getAlert_info_id());
		perInfo = perInfoService.findById(alertInfo.getPerson_id().getPiid());
		return "showAlert";
	}
}
