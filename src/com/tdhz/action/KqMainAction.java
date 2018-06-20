package com.tdhz.action;/**
 * Created by liushuai2 on 2018/6/17.
 */

import com.tdhz.dto.KqMsgDTO;
import com.tdhz.dto.UserDeptDTO;
import com.tdhz.dto.UserRoomDTO;
import com.tdhz.service.*;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Package : com.tdhz.action
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月17日 14:48
 */
@Controller
@Scope("prototype")
public class KqMainAction {
    public static final Logger logger = LoggerFactory.getLogger(KqMainAction.class);

    @Autowired
    private UserAreaService userAreaService;
    @Autowired
    private UserRoomService userRoomService;
    @Autowired
    private UserDeptService userDeptService;
    @Autowired
    private AbnormityService abnormityService;
    @Autowired
    private AlertInfoService alertInfoService;
    @Autowired
    private KqService kqService;

    String name;
    // 公寓
    List<UserRoomDTO> rooms;
    // 班级
    List<UserDeptDTO> depts;
    /*
    开始时间
     */
    String startTime;
    /**
     * 结束时间
     */
    String endTime;
    /**
     * 公寓id
     */
    Integer roomId;
    /**
     * 班级id
     */
    Integer deptId;



    KqMsgDTO kqMsgDTO;
    public String index(){
        if(deptId != null && deptId == -1){
            deptId = null;
        }
        if(roomId != null && roomId == -1){
            roomId = null;
        }
        logger.info("当前查询参数：deptId：{}，roomId：{}，startTime：{}，endTime：{}", new Object[]{deptId, roomId, startTime, endTime});

        // 获取对应的组织机构信息
        //获得HttpServletRequest对象
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        // 公寓管理员
        userid = 3;

        // 辅导员
//        userid = 2;
        logger.info("当前登录用户信息userId={}", userid);
        rooms = userRoomService.getUserApartment(userid);
        if(rooms == null || rooms.size() == 0){
            logger.info("当前登录用户为行政人员，查询所管理的班级信息");
            depts = userDeptService.getBJ(userid);
            kqMsgDTO = kqService.getByAss(userid, deptId, startTime, endTime);
        }else{
            kqMsgDTO = kqService.getBySg(userid, roomId, startTime, endTime);
        }
        logger.info("获取到的用户信息为：{}", JSONObject.fromObject(kqMsgDTO));
        return "index";
    }


    public String list(){

        // 获取对应的组织机构信息
        //获得HttpServletRequest对象
        HttpServletRequest request= ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");
        userid = 3;
        logger.info("当前登录用户信息userId={}", userid);
        rooms = userRoomService.getUserApartment(userid);
        if(rooms == null || rooms.size() == 0){
            logger.info("当前登录用户为行政人员，查询所管理的班级信息");
            depts = userDeptService.getBJ(userid);
        }
        return "list";
    }

    /**
     * 概览页的查询条件
     * @return
     */
    public String mainSearch(){

        return "main_search";

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserRoomDTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<UserRoomDTO> rooms) {
        this.rooms = rooms;
    }

    public List<UserDeptDTO> getDepts() {
        return depts;
    }

    public void setDepts(List<UserDeptDTO> depts) {
        this.depts = depts;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }


    public KqMsgDTO getKqMsgDTO() {
        return kqMsgDTO;
    }

    public void setKqMsgDTO(KqMsgDTO kqMsgDTO) {
        this.kqMsgDTO = kqMsgDTO;
    }




}
