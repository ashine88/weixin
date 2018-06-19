package com.tdhz.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 房间表
 * @author TD-PC
 *
 */
public class Room {

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fullRoomName == null) ? 0 : fullRoomName.hashCode());
		result = prime * result
				+ ((perInfos == null) ? 0 : perInfos.hashCode());
		result = prime * result
				+ ((personAllcount == null) ? 0 : personAllcount.hashCode());
		result = prime * result
				+ ((personIncount == null) ? 0 : personIncount.hashCode());
		result = prime * result + ((proom == null) ? 0 : proom.hashCode());
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		result = prime * result
				+ ((roomName == null) ? 0 : roomName.hashCode());
		result = prime * result + ((sexType == null) ? 0 : sexType.hashCode());
		result = prime * result + ((uaset == null) ? 0 : uaset.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (fullRoomName == null) {
			if (other.fullRoomName != null)
				return false;
		} else if (!fullRoomName.equals(other.fullRoomName))
			return false;
		if (perInfos == null) {
			if (other.perInfos != null)
				return false;
		} else if (!perInfos.equals(other.perInfos))
			return false;
		if (personAllcount == null) {
			if (other.personAllcount != null)
				return false;
		} else if (!personAllcount.equals(other.personAllcount))
			return false;
		if (personIncount == null) {
			if (other.personIncount != null)
				return false;
		} else if (!personIncount.equals(other.personIncount))
			return false;
		if (proom == null) {
			if (other.proom != null)
				return false;
		} else if (!proom.equals(other.proom))
			return false;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		if (roomName == null) {
			if (other.roomName != null)
				return false;
		} else if (!roomName.equals(other.roomName))
			return false;
		if (sexType == null) {
			if (other.sexType != null)
				return false;
		} else if (!sexType.equals(other.sexType))
			return false;
		if (uaset == null) {
			if (other.uaset != null)
				return false;
		} else if (!uaset.equals(other.uaset))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomName=" + roomName
				+ ", fullRoomName=" + fullRoomName + ", proom=" + proom
				+ ", sexType=" + sexType + ", personAllcount=" + personAllcount
				+ ", personIncount=" + personIncount + ", perInfos=" + perInfos
				+ ", uaset=" + uaset + "]";
	}
	public Integer getPersonAllcount() {
		return personAllcount;
	}
	public void setPersonAllcount(Integer personAllcount) {
		this.personAllcount = personAllcount;
	}
	public Integer getPersonIncount() {
		return personIncount;
	}
	public void setPersonIncount(Integer personIncount) {
		this.personIncount = personIncount;
	}
	
	private Integer roomId;	//房间ID 主键
	private String roomName;//房间名称
	private String fullRoomName;//房间全名
	private Integer proom; //上级房间ID
	private String sexType;//男女寝室
	private Integer personAllcount;//总数
	private Integer personIncount;//在寝人数
	
	private Set<PerInfo> perInfos = new HashSet<PerInfo>();
	private Set<User_Area> uaset = new HashSet<User_Area>();


	private Integer area; //区域id

	public Set<User_Area> getUaset() {
		return uaset;
	}
	public void setUaset(Set<User_Area> uaset) {
		this.uaset = uaset;
	}
	public Set<PerInfo> getPerInfos() {
		return perInfos;
	}
	public void setPerInfos(Set<PerInfo> perInfos) {
		this.perInfos = perInfos;
	}
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getFullRoomName() {
		return fullRoomName;
	}
	public void setFullRoomName(String fullRoomName) {
		this.fullRoomName = fullRoomName;
	}
	public Integer getProom() {
		return proom;
	}
	public void setProom(Integer proom) {
		this.proom = proom;
	}
	public String getSexType() {
		return sexType;
	}
	public void setSexType(String sexType) {
		this.sexType = sexType;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}
}
