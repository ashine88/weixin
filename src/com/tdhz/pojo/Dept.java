package com.tdhz.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 部门表1
 * @author TD-PC
 *
 */
public class Dept {

	
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + ", deptName=" + deptName + ", pdept="
				+ pdept + ", pdeptName=" + pdeptName + ", udset=" + udset
				+ ", perInfos=" + perInfos + "]";
	}
	public Set<User_Dept> getUdset() {
		return udset;
	}
	public void setUdset(Set<User_Dept> udset) {
		this.udset = udset;
	}
	private Integer deptId; //部门ID 
	private String deptName;//部门名称
	private Integer pdept;	//上级部门id
	private String pdeptName;//上级部门名称
	private String fullName;//部门全称
	
	private Set<User_Dept> udset = new HashSet<User_Dept>();
	
	public String getPdeptName() {
		return pdeptName;
	}
	public void setPdeptName(String pdeptName) {
		this.pdeptName = pdeptName;
	}
	private Set<PerInfo> perInfos = new HashSet<PerInfo>();
	
	public Set<PerInfo> getPerInfos() {
		return perInfos;
	}
	public void setPerInfos(Set<PerInfo> perInfos) {
		this.perInfos = perInfos;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getPdept() {
		return pdept;
	}
	public void setPdept(Integer pdept) {
		this.pdept = pdept;
	}
	
	
}
