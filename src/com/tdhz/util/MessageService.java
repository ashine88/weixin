package com.tdhz.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tdhz.dao.*;
import com.tdhz.pojo.SendLog;
import com.tdhz.pojo.Users;
import com.tdhz.pojo.Weixin;
import com.tdhz.service.AbnormityService;
import com.tdhz.service.AlertInfoService;
import com.tdhz.service.UsersService;


/**
 * 定时器
 * @author TD-PC
 * 定时查看晚归总数  预警总数
 *
 */

public class MessageService {

	public UsersService getUserService() {
		return userService;
	}

	public void setUserService(UsersService userService) {
		this.userService = userService;
	}

	public AlertInfoService getAlertInfoService() {
		return alertInfoService;
	}

	public void setAlertInfoService(AlertInfoService alertInfoService) {
		this.alertInfoService = alertInfoService;
	}

	public AbnormityService getAbnormityService() {
		return abnormityService;
	}

	public void setAbnormityService(AbnormityService abnormityService) {
		this.abnormityService = abnormityService;
	}

	@Autowired
	private UsersService userService;
	@Autowired
	private AbnormityDao abnormityDao;
	@Autowired
	private AlertInfoService alertInfoService;
	@Autowired
	private AbnormityService abnormityService;
	@Autowired
	private AlertInfoDao alertInfoDao;
	@Autowired
	private WeixinDao weixinDao;
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private SendLogDao sendLogDao;
	
	public SendLogDao getSendLogDao() {
		return sendLogDao;
	}

	public void setSendLogDao(SendLogDao sendLogDao) {
		this.sendLogDao = sendLogDao;
	}

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	public AbnormityDao getAbnormityDao() {
		
		System.out.println(abnormityDao);
		return abnormityDao;
	}

	public void setAbnormityDao(AbnormityDao abnormityDao) {
		this.abnormityDao = abnormityDao;
	}

	public AlertInfoDao getAlertInfoDao() {
		return alertInfoDao;
	}

	public void setAlertInfoDao(AlertInfoDao alertInfoDao) {
		this.alertInfoDao = alertInfoDao;
	}

	public WeixinDao getWeixinDao() {
		return weixinDao;
	}

	public void setWeixinDao(WeixinDao weixinDao) {
		this.weixinDao = weixinDao;
	}

	public void sendMsg() throws Exception{
		SendOrderPaySuccessMsg s = new SendOrderPaySuccessMsg();
		SendSms send = new SendSms();
		SendLog log = new SendLog();
		Date date  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String onday=sdf.format(date);//规范查询日期
		String create_time=sdf.format(date);//日志创建时间
		log.setCreate_time(create_time);
		sendLogDao.saveLog(log);
//		String onday="2017-03-19";
		//晚归人数			
//		long findLaterCount = this.getAbnormityDao().findLaterCount(onday);
		
		//预警人数		
//		long findAlertCount = this.getAlertInfoDao().findAlertCount(onday);
		
		//查看所有辅导员列表	
		List<Object[]> listAllCount = new ArrayList<Object[]>();
		List<Object[]> listNotCount = new ArrayList<Object[]>();
		List<Users> ulist = new ArrayList<Users>()	; 	
		List<Object[]> list = userService.findAllAssByWx();
		System.out.println(list.size());
		if(list.size()>0){
			for(int i=0;i<list.size();i++){		
				Users u = new Users();
				u.setUser_id(((Integer) list.get(i)[0]));
				u.setUser_name(list.get(i)[1].toString());
				u.setRoleName(list.get(i)[2].toString());				
				u.setCon_phone_no(list.get(i)[3].toString());
				System.out.println(u.getUser_name());
				//查看每个辅导员负责班级的预警人员总数
				listAllCount = this.getAlertInfoService().findAlertByAss(onday,(Integer) list.get(i)[0]);
				//查看每个辅导员负责班级的晚归人员总数
				listNotCount = this.getAbnormityService().findLaterInfoByAss(onday, (Integer) list.get(i)[0]);
				
				ulist.add(u);				
			}			
		}
		if(ulist.size()>=0){
			for(int i=0;i<ulist.size();i++){
				Users user = ulist.get(i);				
				//发送短信消息
				System.out.println(user.getCon_phone_no());
				send.sendSms(null, user.getCon_phone_no(),listAllCount.size(),listNotCount.size() );
				log.setSendSms(1);
				sendLogDao.update(log);
				//查看一个辅导员绑定的所有微信	
				List<Weixin> wlist = this.getWeixinDao().selectByUser(user.getUser_id());
				for(int n = 0;n<wlist.size();n++){
					Weixin weixin = wlist.get(n);
					//发送微信推送
					try{
						s.send_template_message(Cfg.getConfig("appid"), Cfg.getConfig("appsecret"), weixin.getOpenid(),listAllCount.size(),listNotCount.size(),onday);
						log.setSendWX(1);
						
					}catch(Exception e){
						e.printStackTrace();
					}
					sendLogDao.update(log);
				}							
			}
		}
	}	
}
