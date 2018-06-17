package com.tdhz.pojo;
/**
 * 系统参数表
 * @author TD-PC
 *
 */
public class Paracfg {

	private Integer paracfg_ID; //规则ID
	private String para_code;	//规则编号
	private String para_value;	//规则属性值
	
	public Integer getParacfg_ID() {
		return paracfg_ID;
	}
	public void setParacfg_ID(Integer paracfg_ID) {
		this.paracfg_ID = paracfg_ID;
	}
	public String getPara_code() {
		return para_code;
	}
	public void setPara_code(String para_code) {
		this.para_code = para_code;
	}
	public String getPara_value() {
		return para_value;
	}
	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}
	
	
}
