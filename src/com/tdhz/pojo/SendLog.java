package com.tdhz.pojo;
/**
 * 发送微信、短信日志 记录表
 * @author TD-PC
 *
 */
public class SendLog {

	private Integer id;
	private Integer sendWX;//是否发送微信推送  0未发送  1已发送
	private Integer sendSms;//是否发送短信 	0未发送  1已发送
	private String create_time;//日志创建时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSendWX() {
		return sendWX;
	}
	public void setSendWX(Integer sendWX) {
		this.sendWX = sendWX;
	}
	public Integer getSendSms() {
		return sendSms;
	}
	public void setSendSms(Integer sendSms) {
		this.sendSms = sendSms;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
