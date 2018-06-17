package com.tdhz.pojo;
/**
 * 人员预警信息表
 * @author TD-PC
 *
 */
public class AlertInfo {	
	
	@Override
	public String toString() {
		return "AlertInfo [alert_info_id=" + alert_info_id + ", alert_reason="
				+ alert_reason + ", alert_type=" + alert_type
				+ ", alert_state=" + alert_state + ", create_date="
				+ create_date + ", check_oper=" + check_oper + ", check_date="
				+ check_date + ", check_reason=" + check_reason
				+ ", last_tg_id=" + last_tg_id + ", last_tg_datetime="
				+ last_tg_datetime + ", IN_OUT=" + IN_OUT + ", person_id="
				+ person_id + ", piid=" + piid + ", deptName=" + deptName
				+ ", piname=" + piname + ", inout=" + inout + ", lasttgtime="
				+ lasttgtime + "]";
	}
	public String getLasttgtime() {
		return lasttgtime;
	}
	public void setLasttgtime(String lasttgtime) {
		this.lasttgtime = lasttgtime;
	}
	public Integer getPiid() {
		return piid;
	}
	public void setPiid(Integer piid) {
		this.piid = piid;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getPiname() {
		return piname;
	}
	public void setPiname(String piname) {
		this.piname = piname;
	}
	public String getInout() {
		return inout;
	}
	public void setInout(String inout) {
		this.inout = inout;
	}
	public PerInfo getPerson_id() {
		return person_id;
	}
	public void setPerson_id(PerInfo person_id) {
		this.person_id = person_id;
	}
	public String getIN_OUT() {
		return IN_OUT;
	}
	public void setIN_OUT(String iN_OUT) {
		IN_OUT = iN_OUT;
	}
	private Integer alert_info_id;//主键ID
//	private Integer person_id;//人员ID  关联学生表
	private String alert_reason;//预警原因
	private Integer alert_type;//预警类型
	private String alert_state;//预警状态
	private String create_date;//预警生成时间
	private Integer check_oper;//处理人员
	private String check_date;//处理时间
	private String check_reason;//处理原因
	private Integer last_tg_id;//通过记录id  关联通过记录表
	private String last_tg_datetime;//最后通过时间
	private String IN_OUT;//进出方向
	
	private PerInfo person_id; //保存人员
	
	private Integer piid;//学生ID
	private String deptName;//部门名称
	private String piname;//学生姓名
	private String inout;//最后进出方向
	private String lasttgtime;//最后通过时间
	public Integer getAlert_info_id() {
		return alert_info_id;
	}
	public void setAlert_info_id(Integer alert_info_id) {
		this.alert_info_id = alert_info_id;
	}


	public String getAlert_reason() {
		return alert_reason;
	}
	public void setAlert_reason(String alert_reason) {
		this.alert_reason = alert_reason;
	}
	public Integer getAlert_type() {
		return alert_type;
	}
	public void setAlert_type(Integer alert_type) {
		this.alert_type = alert_type;
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
	
	
	
}
