package com.tdhz.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tdhz.pojo.PerInfo;
import com.tdhz.pojo.Room;
import com.tdhz.pojo.Tbcha_channeltg_14;
import com.tdhz.pojo.UserOperaLog;
import com.tdhz.service.UserAreaService;

@Controller
@Scope("prototype")
public class RoomAction {

	
	
	@Autowired
	private UserAreaService userAreaService;

	private Integer roomId;  //房间ID
	private String roomName;//房间名称
	private String buildName; //公寓名称
	private Integer buildnotback; //公寓不在寝人数
	private String floorName; //楼层名称
	private Integer floornotback;//楼层不在寝人数
	private String sysusername;//宿管员名称
	private List<Room> rlist =new ArrayList<Room>();
	private List<PerInfo> plist =new ArrayList<PerInfo>();
	private List<Tbcha_channeltg_14> tdlist =new ArrayList<Tbcha_channeltg_14>();
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getSysusername() {
		return sysusername;
	}
	public void setSysusername(String sysusername) {
		this.sysusername = sysusername;
	}
	public Integer getFloornotback() {
		return floornotback;
	}
	public void setFloornotback(Integer floornotback) {
		this.floornotback = floornotback;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public Integer getBuildnotback() {
		return buildnotback;
	}
	public void setBuildnotback(Integer buildnotback) {
		this.buildnotback = buildnotback;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public List<Tbcha_channeltg_14> getTdlist() {
		return tdlist;
	}
	public void setTdlist(List<Tbcha_channeltg_14> tdlist) {
		this.tdlist = tdlist;
	}	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public List<PerInfo> getPlist() {
		return plist;
	}
	public void setPlist(List<PerInfo> plist) {
		this.plist = plist;
	}
	public List<Room> getRlist() {
		return rlist;
	}	
	public void setRlist(List<Room> rlist) {
		this.rlist = rlist;
	}
	
	//根据公寓的ID查询所有楼层
	public String selectAllfloor(){
		List<Room> rlist1 = userAreaService.findAllfloorByProomId(roomId);
			if(rlist1.size()>0){
				for(int i=0;i<rlist1.size();i++){
					List<Object[]> roomlist =	userAreaService.fingBuildById(rlist1.get(i).getRoomId());
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
				return "showAllfloor";
			}
		return "error";
	}
	//根据楼层的ID查询所有房间
	public String selectAllroom(){
		List<Room> rlist1 = userAreaService.findAllfloorByProomId(roomId);
		if(rlist1.size()>0){
			for(int i=0;i<rlist1.size();i++){
				List<Object[]> roomlist =	userAreaService.fingBuildById(rlist1.get(i).getRoomId());
				
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
			return "showAllroom";
		}		
		return "error";
	}
	//根据楼层ID查找所有不在寝人员信息
	public String findNotBackByfloorId(){
		//增加报表，宿管员操作记录表(id username date chakan 查看楼层人员不在寝明细操作记录)
		UserOperaLog u = new UserOperaLog();
		u.setUsername(sysusername);
		u.setOperadate(new Date());
		u.setData("查看"+buildName+floorName+"不在寝人员明细 !");
		userAreaService.addUserOperaLog(u);
		
		List<PerInfo> plist1 = userAreaService.finNotBackByfloorId(roomId);
		if(plist1.size()>0){
			for(int i=0;i<plist1.size();i++){
				PerInfo per = new PerInfo();
				per.setPiid((Integer)plist1.get(i).getPiid());
				per.setPiName(plist1.get(i).getPiName().toString());
				per.setDeptName(plist1.get(i).getDept().getDeptName().toString());
				per.setRoomName(plist1.get(i).getRoom().getRoomName().toString());
				per.setLastRoomTGDT((Date)plist1.get(i).getLastRoomTGDT());
				per.setLastRoomTGINOUT(plist1.get(i).getLastRoomTGINOUT().toString());
				plist.add(per);
			}
			HttpServletRequest request= ServletActionContext.getRequest();
			HttpSession session = request.getSession();	
			session.setAttribute("plist", plist);
			return "perdetail";
		}
		return "error";
	}
	//根据房间ID查找所有人员通过记录
	public String selectAllper(){
		//增加报表，宿管员操作记录表(id username date chakan 查看房间人员明细操作记录)
		UserOperaLog u = new UserOperaLog();
		u.setUsername(sysusername);
		u.setOperadate(new Date());
		u.setData("查看"+buildName+floorName+roomName+"人员通过明细 !");
		userAreaService.addUserOperaLog(u);
		plist = userAreaService.findAllPerByroomId(roomId);
		if(plist.size()>0){
			return "showAllper";
		}		
		return "error";
	}	
}
