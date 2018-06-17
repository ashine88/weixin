package com.tdhz.util;

public class SendSms {
	
	public static String url = "http://115.28.50.135:8888/sms.aspx";
	public static String userid = "3432";
	public static String account = "泰德汇智";
	public static String password = "0KXDyGmK";
	public static String checkWord = "这个字符串中是否包含了屏蔽字";
	public static String mobile = "";
	public static String content1 = "【学生昨日行为信息提醒】";
	
	public  void sendSms(String productID,String tel,long findAlertCount,long findLaterCount) {
		
		String sendMessage = SmsClientSend.sendSms(url, userid, account, password, tel, content1+"预警人数:"+findAlertCount+"晚归人数"+findLaterCount+"。");
		System.out.println(sendMessage);
		
	}
}
