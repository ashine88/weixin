package com.tdhz.pojo;
/**
 * 异常信息表
 * @author TD-PC
 *
 */
public class AbnormityInfo {
	
	
	
	public String getPiname() {
		return piname;
	}
	public void setPiname(String piname) {
		this.piname = piname;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public PerInfo getPerson_id() {
		return person_id;
	}
	public void setPerson_id(PerInfo person_id) {
		this.person_id = person_id;
	}
	private Integer abnormity_id;//主键ID
	//private Integer person_id;//人员ID 关联学生表
	private String alert_reason;//异常原因
	private Integer abn_type;//异常类型
	private String alert_state;//异常状态
	private String create_date;//异常生成时间
	private Integer check_oper;//处理人员 关联users表
	private String check_date;//处理时间
	private String check_reason;//处理原因
	private Integer last_tg_id;//通过记录ID 关联通过记录
	private String last_tg_datetime;//最后通过时间
	private String IN_OUT;//进出方向
	
	private PerInfo person_id; //保存人员
	private String backtime;	//返寝室间
	
	private String piname;//学生姓名
	private String deptname;//班级名称
	public String getBacktime() {
		return backtime;
	}
	public void setBacktime(String backtime) {
		this.backtime = backtime;
	}
	
	
	
	public Integer getAbnormity_id() {
		return abnormity_id;
	}
	public void setAbnormity_id(Integer abnormity_id) {
		this.abnormity_id = abnormity_id;
	}
	
	public String getAlert_reason() {
		return alert_reason;
	}
	public void setAlert_reason(String alert_reason) {
		this.alert_reason = alert_reason;
	}
	public Integer getAbn_type() {
		return abn_type;
	}
	public void setAbn_type(Integer abn_type) {
		this.abn_type = abn_type;
	}
	public String getAlert_state() {
		return alert_state;
	}
	public void setAlert_state(String alert_state) {
		this.alert_state = alert_state;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public Integer getCheck_oper() {
		return check_oper;
	}
	public void setCheck_oper(Integer check_oper) {
		this.check_oper = check_oper;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getCheck_reason() {
		return check_reason;
	}
	public void setCheck_reason(String check_reason) {
		this.check_reason = check_reason;
	}
	public Integer getLast_tg_id() {
		return last_tg_id;
	}
	public void setLast_tg_id(Integer last_tg_id) {
		this.last_tg_id = last_tg_id;
	}
	public String getLast_tg_datetime() {
		return last_tg_datetime;
	}
	public void setLast_tg_datetime(String last_tg_datetime) {
		this.last_tg_datetime = last_tg_datetime;
	}
	public String getIN_OUT() {
		return IN_OUT;
	}
	public void setIN_OUT(String iN_OUT) {
		IN_OUT = iN_OUT;
	}
	
	
	
}
