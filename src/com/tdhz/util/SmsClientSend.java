package com.tdhz.util;

import java.net.URLEncoder;

public class SmsClientSend {
	public static String sendSms(String url, String userid, String account,
			String password, String mobile, String content) {

		return sendSms(url, userid, account, password, mobile, content, null,
				null, null, null, null, null, null, "POST", "UTF-8", "UTF-8");
	}
	
	private static String sendSms(String url, String userid, String account,
			String password, String mobile, String content, String action,
			String sendTime, String checkContent, String taskName,
			String countNumber, String mobileNumber, String telephoneNumber,
			String sendType, String codingType, String backEncodType) {

		try {
			if (codingType == null || codingType.equals("")) {
				codingType = "UTF-8";
			}
			if (backEncodType == null || backEncodType.equals("")) {
				backEncodType = "UTF-8";
			}
			StringBuffer send = new StringBuffer();
			if (action != null && !action.equals("")) {
				send.append("action=").append(action);
			} else {
				send.append("action=send");
			}

			send.append("&userid=").append(userid);
			send.append("&account=").append(
					URLEncoder.encode(account, codingType));
			send.append("&password=").append(
					URLEncoder.encode(password, codingType));
			send.append("&mobile=").append(mobile);
			send.append("&content=").append(
					URLEncoder.encode(content, codingType));
			if (sendTime != null && !sendTime.equals("")) {
				send.append("&sendTime=").append(
						URLEncoder.encode(sendTime, codingType));
			}
			if (checkContent != null && !checkContent.equals("")) {
				send.append("&checkContent=").append(checkContent);
			}
			if (taskName != null && !taskName.equals("")) {
				send.append("&taskName=").append(
						URLEncoder.encode(taskName, codingType));
			}
			if (countNumber != null && !countNumber.equals("")) {
				send.append("&countNumber=").append(countNumber);
			}
			if (mobileNumber != null && !mobileNumber.equals("")) {
				send.append("&mobileNumber=").append(mobileNumber);
			}
			if (telephoneNumber != null && !telephoneNumber.equals("")) {
				send.append("&telephoneNumber=").append(telephoneNumber);
			}

			if (sendType != null && (sendType.toLowerCase()).equals("get")) {
				return SmsClientAccessTool.getInstance().doAccessHTTPGet(
						url + "?" + send.toString(), backEncodType);
			} else {
				return SmsClientAccessTool.getInstance().doAccessHTTPPost(url,
						send.toString(), backEncodType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "未发送，编码异常";
		}
	}
}
