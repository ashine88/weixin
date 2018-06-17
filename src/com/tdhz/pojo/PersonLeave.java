package com.tdhz.pojo;


/**
 * 个人请假表
 * @author TD-PC
 *
 */
public class PersonLeave {

	@Override
	public String toString() {
		return "PersonLeave [person_leave_id=" + person_leave_id
				+ ", person_id=" + person_id + ", begin_date=" + begin_date
				+ ", end_date=" + end_date + ", leave_type=" + leave_type
				+ ", reason=" + reason + ", create_date=" + create_date
				+ ", state=" + state + ", xj_date=" + xj_date
				+ ", last_tg_time=" + last_tg_time + ", make_oper=" + make_oper
				+ ", last_tg_id=" + last_tg_id + ", ts=" + ts + ", leaveDays="
				+ leaveDays + "]";
	}

	private Integer person_leave_id;//主键ID	
	
	private Integer person_id;		//人员ID  关联学生表
	
	private String begin_date;	//请假开始日期
	private String end_date;	//请假结束日期 
	private String leave_type;	//请假类型
	private String reason;		//请假原因
	private String create_date; //填报时间
	private Integer state;		//请假状态   1请假   0已销假
	private String xj_date;		//销假时间
	private String last_tg_time;//最后离校时间
	
	private Integer make_oper;		//填报人员 关联    user表
	private Integer last_tg_id;		//最后通过记录的id 关联通过记录表
	
	private String ts;//时间戳
	
	private Integer leaveDays;//请假时长
	public Integer getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(Integer leaveDays) {
		this.leaveDays = leaveDays;
	}
	public Integer getPerson_leave_id() {
		return person_leave_id;
	}

	public void setPerson_leave_id(Integer person_leave_id) {
		this.person_leave_id = person_leave_id;
	}

	public Integer getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getLeave_type() {
		return leave_type;
	}

	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getXj_date() {
		return xj_date;
	}

	public void setXj_date(String xj_date) {
		this.xj_date = xj_date;
	}

	public String getLast_tg_time() {
		return last_tg_time;
	}

	public void setLast_tg_time(String last_tg_time) {
		this.last_tg_time = last_tg_time;
	}

	public Integer getMake_oper() {
		return make_oper;
	}

	public void setMake_oper(Integer make_oper) {
		this.make_oper = make_oper;
	}

	public Integer getLast_tg_id() {
		return last_tg_id;
	}

	public void setLast_tg_id(Integer last_tg_id) {
		this.last_tg_id = last_tg_id;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	
	
	
}
