package com.tdhz.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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

import com.tdhz.dao.LeaveDao;
import com.tdhz.pojo.*;
import com.tdhz.service.LeaveService;
import com.tdhz.service.PerInfoService;
import com.tdhz.util.CountDays;

@Controller
@Scope("prototype")
public class LeaveAction {
	
	private static final Logger log = LoggerFactory.getLogger(LeaveAction.class);
	public List<User_Dept> getUdlist() {
		return udlist;
	}

	public void setUdlist(List<User_Dept> udlist) {
		this.udlist = udlist;
	}
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private PerInfoService perInfoService;
	@Autowired
	private LeaveDao leaveDao;
	private PersonLeave personLeave;
	private ClassLeave classLeave;
	private PerInfo perInfo;
	private Dept dept;
	private List<User_Dept> udlist;
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public PersonLeave getPersonLeave() {
		return personLeave;
	}

	public void setPersonLeave(PersonLeave personLeave) {
		this.personLeave = personLeave;
	}

	public ClassLeave getClassLeave() {
		return classLeave;
	}

	public void setClassLeave(ClassLeave classLeave) {
		this.classLeave = classLeave;
	}

	public PerInfo getPerInfo() {
		return perInfo;
	}

	public void setPerInfo(PerInfo perInfo) {
		this.perInfo = perInfo;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}
	//声明输入流对象
		private InputStream inputStream;
		
		public InputStream getInputStream() {
			return inputStream;
		}
	//计算请假时长
	public String countDay(){
		if(personLeave.getBegin_date() ==null || "".equals(personLeave.getBegin_date())  ){
			msg ="请输入日期";
		}else{
			CountDays cd = new CountDays();
			String  days =cd.getDays(personLeave.getBegin_date(), personLeave.getEnd_date());
			int count =Integer.parseInt(String.valueOf(days));
			if(count<= 0){
				msg ="请输入正确日期";
			}else{
				days = String.valueOf(count);
				msg=days;
			}
			
		}
		
		try {
			inputStream=new ByteArrayInputStream(msg.getBytes("UTF-8"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "ajax";
	}
	
	//个人请假
	public String addPersonLeave(){
		if(perInfo.getPiName() == null || personLeave.getBegin_date() ==null || personLeave.getEnd_date()==null || personLeave.getReason().isEmpty() ||personLeave.getLeaveDays() ==null){
			msg="假条信息不完整，请补充。。。";
			return "error";
		}	
		perInfo = perInfoService.findOnePerInfo(perInfo.getPiName());
		if(perInfo ==null){
			msg ="学生姓名不合法。";
			return "error";
		}
		CountDays cd = new CountDays();
		String  days =cd.getDays(personLeave.getBegin_date(), personLeave.getEnd_date());
		int count =Integer.parseInt(String.valueOf(days));
		if(count < 0 ){
			msg ="请假日期有错误。结束日期必须大于开始日期";
			return "error";
		}
		personLeave.setLeaveDays(count);
		//定义请假时间
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creat_date = sdf.format(now);
		
		personLeave.setState(1);
		personLeave.setCreate_date(creat_date);
		personLeave.setPerson_id(perInfo.getPiid());
		leaveService.addPersonLeave(personLeave);
		log.info("个人请假"+personLeave.toString());
		return "ok";
	}
	//根据userID  查出负责的所有班级
	public String selectAllClass(){
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();	
		Integer user_id = (Integer) session.getAttribute("userid");
		udlist = leaveService.selectAllClass(user_id);
		
		return "classleave";
	}
	//加载请假班级
	public String preAddClassLeave(){
		
		dept = leaveDao.fingById(dept.getDeptId());
		return "addclassleave";
	}
	
	//班级请假
	public String addClassLeave(){
			
//		if("".equals(dept.getDeptName()) ||dept.getDeptName() == null || "".equals(classLeave.getBegin_day()) || classLeave.getBegin_day() == null || "".equals(classLeave.getEnd_day()) || classLeave.getEnd_day() == null){
//			msg="假条信息不完整，请补充。。。";
//			return "class_error";
//		}
//		dept = perInfoService.findOneDept(dept.getDeptName());
//		if(dept == null){
//			msg="班级不存在。。";
//			return "class_error";
//		}
		CountDays cd = new CountDays();
		if("".equals(classLeave.getBegin_day()) || classLeave.getBegin_day() ==null || "".equals(classLeave.getEnd_day()) || classLeave.getEnd_day() == null){
			msg ="请填写日期";
			return "class_error";
		}
		String  days = cd.getDays(classLeave.getBegin_day(), classLeave.getEnd_day());
		int count =Integer.parseInt(String.valueOf(days));
		if(count <= 0 ){
			msg ="结束日期必须大于开始日期";
			return "class_error";
		}
		//定义请假时间
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String creat_date = sdf.format(now);
		
		classLeave.setHoliday_state("1");
		classLeave.setCreate_datetime(creat_date);
		classLeave.setDept_id(dept.getDeptId());
		leaveService.addClassLeave(classLeave);
		log.info("班级请假"+classLeave.toString());
		return "ok";
	}

}
