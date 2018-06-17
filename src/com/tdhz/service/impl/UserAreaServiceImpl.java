package com.tdhz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdhz.dao.UserAreaDao;
import com.tdhz.dao.UserOperaLogDao;
import com.tdhz.pojo.*;
import com.tdhz.service.UserAreaService;

@Service
public class UserAreaServiceImpl implements UserAreaService {

	@Autowired
	private UserAreaDao userAreaDao;
	
	@Autowired
	private UserOperaLogDao userOperaLogDao;
	@Override
	public List<User_Area> getUserArea(Integer userId) {
		
		return userAreaDao.getUserArea(userId);
	}

	@Override
	public Room getRoomById(Integer rid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> findAllfloorByProomId(Integer proom) {
		// TODO Auto-generated method stub
		return userAreaDao.findAllfloorByProom(proom);
	}

	@Override
	public List<PerInfo> findAllPerByroomId(Integer roomId) {
		
		return userAreaDao.findAllperByroomId(roomId);
	}

	@Override
	public List<Tbcha_channeltg_14> findlastByRoomId(Integer roomId) {
		List<Room> roomlist = new ArrayList<Room>();
		List<PerInfo> perlist = new ArrayList<PerInfo>();
		List<Tbcha_channeltg_14> tglist = new ArrayList<Tbcha_channeltg_14>();
		//根据宿舍楼ID 查看楼层ID
		List<Room> floorlist = userAreaDao.findAllfloorByProom(roomId);
		System.out.println("楼层数"+floorlist.size());
		//根据楼层ID  查看房间ID
		for(int i=0;i<floorlist.size();i++){
			List<Room> rlist= userAreaDao.findAllfloorByProom(floorlist.get(i).getRoomId());
			roomlist.addAll(rlist);
		}
		//根据房间ID  查看人员ID
		for(int n=0;n<roomlist.size();n++){
			List<PerInfo> plist = userAreaDao.findAllperByroomId(roomlist.get(n).getRoomId());
			perlist.addAll(plist);
		}
		//根据每个人的人员ID  查找通过记录
		for(int m=0;m<perlist.size();m++){
			List<Tbcha_channeltg_14> tgls = userAreaDao.findTgByPer(perlist.get(m).getPiid());
			if(tgls.size()>0){
				tglist.add(tgls.get(0));
			}
			
		}
		return tglist;
	}

	@Override
	public List<Room> findAllroomByProomId(Integer proom) {
		// TODO Auto-generated method stub
		return userAreaDao.findAllfloorByProom(proom);
	}

	@Override
	public List<PerInfo> findPerByRoomId(Integer roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> fingBuildById(Integer roomId) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("set nocount on ");
		sb.append("create table #temp(	roomId int,	roomName [nvarchar](400) NULL,RoomFullName [nvarchar](400) NULL,PersonAllCount int,PersonInCount int)  ");
		
		sb.append("insert into #temp(roomId,roomName,RoomFullName) select roomId,roomName,FULLROOMNAME from TBCHA_ROOM room where room.roomId = "+roomId+" and isnull(room.ISDEL,0)=0 order by FULLROOMNAME");     
		
		sb.append("   DECLARE @roomFunllName varchar(200)	DECLARE @personAllCount int	DECLARE @personInCount int	DECLARE cGetPersonCount CURSOR");
		
		sb.append("   FOR SELECT RoomFullName FROM #temp OPEN cGetPersonCount FETCH NEXT FROM cGetPersonCount INTO @roomFunllName WHILE @@FETCH_STATUS =0 	BEGIN");

		sb.append(" select @personAllCount=count(1)	from TBCHA_PERINFO person	left join TBCHA_ROOM room 	on person.ROOM=room.ROOMID	where room.FULLROOMNAME like '%'+@roomFunllName+'%' and isnull(person.ISDEL,0)=0	 ");
		sb.append("	select @personInCount=count(1) from TBCHA_PERINFO person left join TBCHA_ROOM room 	on person.ROOM=room.ROOMID	where room.FULLROOMNAME like '%'+@roomFunllName+'%'  and isnull(person.ISDEL,0)=0 and person.LASTROOMTGINOUT='4_2'	print @personInCount");
		sb.append(" update #temp   set PersonAllCount=@personAllCount,personInCount=@personInCount	where RoomFullName=@roomFunllName FETCH NEXT FROM  cGetPersonCount INTO @roomFunllName END CLOSE cGetPersonCount DEALLOCATE cGetPersonCount ");
		
		sb.append("   select * from #temp");			

		String sql=sb.toString();
		
		return userAreaDao.findByRoomId(sql);
	}

	@Override
	public List<Object[]> findRoomById(Integer roomId) {
		StringBuilder sb = new StringBuilder();
		sb.append("set nocount on ");
		sb.append("create table #temp(	roomId int,	roomName [nvarchar](400) NULL,RoomFullName [nvarchar](400) NULL,PersonAllCount int,PersonInCount int)  ");
		
		sb.append("insert into #temp(roomId,roomName,RoomFullName) select roomId,roomName,FULLROOMNAME from TBCHA_ROOM room where room.roomId in( select room.roomid from TBCHA_ROOM room where room.PROOM = "+roomId+" ) and isnull(room.ISDEL,0)=0 order by FULLROOMNAME");     
		
		sb.append("   DECLARE @roomFunllName varchar(200)	DECLARE @personAllCount int	DECLARE @personInCount int	DECLARE cGetPersonCount CURSOR");
		
		sb.append("   FOR SELECT RoomFullName FROM #temp OPEN cGetPersonCount FETCH NEXT FROM cGetPersonCount INTO @roomFunllName WHILE @@FETCH_STATUS =0 	BEGIN");

		sb.append(" select @personAllCount=count(1)	from TBCHA_PERINFO person	left join TBCHA_ROOM room 	on person.ROOM=room.ROOMID	where room.FULLROOMNAME like '%'+@roomFunllName+'%' and isnull(person.ISDEL,0)=0	 ");
		sb.append("	select @personInCount=count(1) from TBCHA_PERINFO person left join TBCHA_ROOM room 	on person.ROOM=room.ROOMID	where room.FULLROOMNAME like '%'+@roomFunllName+'%'  and isnull(person.ISDEL,0)=0 and person.LASTROOMTGINOUT='4_2'	print @personInCount");
		sb.append(" update #temp   set PersonAllCount=@personAllCount,personInCount=@personInCount	where RoomFullName=@roomFunllName FETCH NEXT FROM  cGetPersonCount INTO @roomFunllName END CLOSE cGetPersonCount DEALLOCATE cGetPersonCount ");
		
		sb.append("   select * from #temp");		
		String sql=sb.toString();
		return userAreaDao.findByRoomId(sql);
	}

	@Override
	public List<PerInfo> finNotBackByfloorId(Integer roomId) {
		String roomIds = "";
		List<Room> roomlist = userAreaDao.findAllfloorByProom(roomId);
		if(roomlist.size()>0){
			for(int i=0;i<roomlist.size();i++){
				roomIds =roomIds+roomlist.get(i).getRoomId()+",";
			}
		}
		roomIds = roomIds.substring(0,roomIds.length() - 1);
		
		
		return userAreaDao.findNotBackPerByfloorId(roomIds);
	}

	@Override
	public void addUserOperaLog(UserOperaLog u) {
		userOperaLogDao.addUserOperaLog(u);
		
	}
	
	

}
