package com.tdhz.pojo;
/**
 * 班级请假表
 * @author TD-PC
 *
 */
public class ClassLeave {
	

	@Override
	public String toString() {
		return "ClassLeave [holiday_info_id=" + holiday_info_id + ", dept_id="
				+ dept_id + ", holiday_type=" + holiday_type
				+ ", holiday_state=" + holiday_state + ", begin_day="
				+ begin_day + ", end_day=" + end_day + ", create_oper="
				+ create_oper + ", create_datetime=" + create_datetime + "]";
	}
	private Integer holiday_info_id;//主键ID
	private Integer dept_id;//部门ID
	private String holiday_type;//请假类型
	private String holiday_state;//假期状态  1请假  0 已销假
	private String begin_day;//开始时间
	private String end_day;//结束时间
	
	private Integer create_oper;//关联user表
	private String create_datetime;//填报时间
	public Integer getHoliday_info_id() {
		return holiday_info_id;
	}
	public void setHoliday_info_id(Integer holiday_info_id) {
		this.holiday_info_id = holiday_info_id;
	}
	public Integer getDept_id() {
		return dept_id;
	}
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	public String getHoliday_type() {
		return holiday_type;
	}
	public void setHoliday_type(String holiday_type) {
		this.holiday_type = holiday_type;
	}
	public String getHoliday_state() {
		return holiday_state;
	}
	public void setHoliday_state(String holiday_state) {
		this.holiday_state = holiday_state;
	}
	public String getBegin_day() {
		return begin_day;
	}
	public void setBegin_day(String begin_day) {
		this.begin_day = begin_day;
	}
	public String getEnd_day() {
		return end_day;
	}
	public void setEnd_day(String end_day) {
		this.end_day = end_day;
	}
	public Integer getCreate_oper() {
		return create_oper;
	}
	public void setCreate_oper(Integer create_oper) {
		this.create_oper = create_oper;
	}
	public String getCreate_datetime() {
		return create_datetime;
	}
	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}
	
	
	
}
