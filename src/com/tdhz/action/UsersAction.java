package com.tdhz.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tdhz.pojo.*;
import com.tdhz.service.*;

@Controller
@Scope("prototype")
public class UsersAction {	
	private static final Logger log = LoggerFactory.getLogger(UsersAction.class);
	//声明输入流对象
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public String getRemFlag() {
		return remFlag;
	}
	public void setRemFlag(String remFlag) {
		this.remFlag = remFlag;
	}
	public List<Tbcha_channeltg_14> getTglist() {
		return tglist;
	}
	public void setTglist(List<Tbcha_channeltg_14> tglist) {
		this.tglist = tglist;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Room> getRlist() {
		return rlist;
	}
	public void setRlist(List<Room> rlist) {
		this.rlist = rlist;
	}

	@Autowired
	private SNSUserInfoService snsUserInfoService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private WeixinService weixinService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserAreaService userAreaService;
	private Users user;
	private String user_code;
	private String user_password;
	private String remFlag;//记住密码
	
	private SNSUserInfo snsUserInfo;
	private Weixin weixin;
	private Users_Role user_role;
	private List<User_Area> ualist;
	private List<Tbcha_channeltg_14> tglist;
	
	
	private List<Room> rlist = new ArrayList<Room>();
	private Room room;
	
	
	public List<User_Area> getUalist() {
		return ualist;
	}
	public void setUalist(List<User_Area> ualist) {
		this.ualist = ualist;
	}
	
	public Users_Role getUser_role() {
		return user_role;
	}
	public void setUser_role(Users_Role user_role) {
		this.user_role = user_role;
	}
	public Weixin getWeixin() {
		return weixin;
	}
	public void setWeixin(Weixin weixin) {
		this.weixin = weixin;
	}
	public SNSUserInfo getSnsUserInfo() {
		return snsUserInfo;
	}
	public void setSnsUserInfo(SNSUserInfo snsUserInfo) {
		this.snsUserInfo = snsUserInfo;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	//查看绑定openid
	public String checkOpenid(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();		
		snsUserInfo = (SNSUserInfo) session.getAttribute("snsUserInfo");		
		
//		//根据openId查询微信信息
//		SNSUserInfo snsUserInfo2 = snsUserInfoService.findByOpenid(snsUserInfo.getOpenId());
//		
//		//如果没有微信信息，添加微信信息
//		if(snsUserInfo2 ==null){
//			snsUserInfoService.insertSNSUserInfo(snsUserInfo);
//		}
		
		//查找微信表如果有openid 不用登陆
		weixin = weixinService.selectOpenid(snsUserInfo.getOpenId());
		if(weixin.getId() ==null){
			weixin.setOpenid(snsUserInfo.getOpenId());
			weixinService.addWeixin(weixin);
			return "error";
		}
		if(weixin !=null && weixin.getUsers() == null){
			return "error";
		}
		if(weixin !=null ){
			
			Integer user_id = weixin.getUsers().getUser_id();
			user_role = userRoleService.getUserRole(user_id);
			if(user_role== null){
				return "error";
			}
			if(user_role.getRole_id() == 2){
				log.info("学生处领导直接进入系统");
				session.setAttribute("userid",weixin.getUsers().getUser_id() );	
				return "xsc";
			}
			if(user_role.getRole_id() == 4 ){
				log.info("辅导员直接进入系统");
				session.setAttribute("userid",weixin.getUsers().getUser_id() );	
				return "fdy";
			}
		}		
		return "error";
	}
	
	//宿管老师登录
	public String loginsg() throws Exception{
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		HttpSession session = request.getSession();		
		user=  usersService.login(user_code,user_password);
		
		if(user ==null){
			return "error";
		}else{
			Integer user_id = user.getUser_id();
			user_role = userRoleService.getUserRole(user_id);
			if(user_role.getRole_id() == 7){//宿管				
				session.setAttribute("user",user );	
				//记住密码功能
				if("1".equals(remFlag)){//"1"表示用户勾选记住密码
					String loginInfo = user_code+"#"+user_password;
		            Cookie userCookie=new Cookie("loginInfo",loginInfo); 
		            userCookie.setMaxAge(7*24*60*60);   //存活期为一个星期 7*24*60*60
		            userCookie.setPath("/");
		            response.addCookie(userCookie); 	
				}
				//查看宿管负责的公寓id
				ualist = userAreaService.getUserArea(user_id);
				if(ualist.size()>0){
					for(int m=0;m<ualist.size();m++){
						String fullname = ualist.get(m).getArea_id().getFullRoomName();
						if(fullname.length()-fullname.replace("_", "").length() !=2){
							//移除不是公寓楼ID
							ualist.remove(m);
						}
					}					
				}				
				if(ualist.size()>0){
					for(int i=0;i<ualist.size();i++){
						room = ualist.get(i).getArea_id();	
						List<Object[]> roomlist =	userAreaService.fingBuildById(room.getRoomId());
						if(roomlist.size()>0){							
							for(int n=0;n<roomlist.size();n++){
								Room r1= new Room();
								r1.setRoomId((Integer) roomlist.get(n)[0]);
								r1.setRoomName( roomlist.get(n)[1].toString());
								r1.setFullRoomName((String) roomlist.get(n)[2]);
								r1.setPersonAllcount((Integer) roomlist.get(n)[3]);
								r1.setPersonIncount((Integer) roomlist.get(n)[4]);
								rlist.add(r1);
							}							
						}												
					}					
				}
				return "suguan";						
			}
		}
		return "error";
	}
	
	//用户登录
	public String login(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();		
		snsUserInfo = (SNSUserInfo) session.getAttribute("snsUserInfo");
		log.info("用户登录  user_code = "+user_code+" user_password = "+user_password);
		user=  usersService.login(user_code,user_password);
		
		if(user ==null){
			return "error";
		}else{
			if(user.getWeixin().isEmpty()){
				weixin = weixinService.selectOpenid(snsUserInfo.getOpenId());
				weixin.setUsers(user);
				weixinService.updateWeixin(weixin);
			}
			if(user.getWeixin().isEmpty()==false){
				weixin = weixinService.selectOpenid(snsUserInfo.getOpenId());
				weixin.setUsers(user);
				weixinService.addWeixin(weixin);
			}
			Integer user_id = weixin.getUsers().getUser_id();
			user_role = userRoleService.getUserRole(user_id);
			if(user_role== null){
				return "error";
			}
			if(user_role.getRole_id() == 2){
				session.setAttribute("userid",weixin.getUsers().getUser_id() );	
				return "xsc";
			}
			if(user_role.getRole_id() == 4 ){
				session.setAttribute("userid",weixin.getUsers().getUser_id() );	
				return "fdy";
			}
			
			
		}		
		return "error";
	}
	
	//退出登录
	public String logout(){
		//获得HttpServletRequest对象
		HttpServletRequest request= ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		snsUserInfo = (SNSUserInfo) session.getAttribute("snsUserInfo");
		String openid = snsUserInfo.getOpenId();
		weixin = weixinService.selectOpenid(openid);
		weixin.setUsers(null);
		weixinService.updateWeixin(weixin);
		log.info("用户退出登录");
		String msg = "";
		try {
			inputStream=new ByteArrayInputStream(msg.getBytes("UTF-8"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return "ajax";
	}
}
