package com.tdhz.pojo;

import java.util.HashSet;
import java.util.Set;

public class AssRankInfo {
	
	

	private Set<Integer> userset = new HashSet<Integer>();  //保存辅ID导员集合
	
	private PerInfo perInfo;//保存学生信息
	
	private Set<Integer> deptset = new HashSet<Integer>(); //保存部门ID集合
	
	private Double rate;//处理率
	public Set<Integer> getUserset() {
		return userset;
	}

	public void setUserset(Set<Integer> userset) {
		this.userset = userset;
	}

	public PerInfo getPerInfo() {
		return perInfo;
	}

	public void setPerInfo(PerInfo perInfo) {
		this.perInfo = perInfo;
	}

	public Set<Integer> getDeptset() {
		return deptset;
	}

	public void setDeptset(Set<Integer> deptset) {
		this.deptset = deptset;
	}
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	
}
