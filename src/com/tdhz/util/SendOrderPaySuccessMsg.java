package com.tdhz.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tdhz.pojo.AccessToken;
import com.tdhz.pojo.TemplateData;
import com.tdhz.pojo.WxTemplate;

public class SendOrderPaySuccessMsg {

	Logger log = LoggerFactory.getLogger(getClass());
	 
	 /**
    * 发送模板消息
    * appId 公众账号的唯一标识
    * appSecret 公众账号的密钥
    * openId 用户标识
    */
	 public void send_template_message(String appId, String appSecret, String openId,long findAlertCount,long findLaterCount  ,String onday) {
		 
		 	AccessToken token = WeixinUtil.getAccessToken(appId, appSecret);
		    System.out.println(token);
		 	String access_token = token.getToken();
		    String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		   
		    WxTemplate temp = new WxTemplate();
		    //url="http://192.168.8.35:8089/G.aspx?fId=1^5c21ec35-28cb-46a8-bbcf-999d4b2ac217^1";
		    temp.setUrl("http://"+Cfg.getConfig("domainname")+"/weixin1/alertAction_findAlert.action?pe.pageNum=1&onday ='"+onday+"'");
		    //temp.setUrl(channel.getImgurl());
		    temp.setTouser(openId);
		    temp.setTopcolor("#173177");
		
		    temp.setTemplate_id(Cfg.getConfig("Template_id"));
		    Map<String,TemplateData> m = new HashMap<String,TemplateData>();
		    
		    TemplateData first = new TemplateData();
		    first.setColor("#173177");  
		    first.setValue("昨日人员统计信息如下:");  
		    m.put("first", first);  
		    
		    TemplateData name = new TemplateData();  
		    name.setColor("#173177");  
		    name.setValue(""+findAlertCount+"人  ");
		    //name.setValue(channel.getPerInfo().getPiName());
		    m.put("name", name);
		    
		    TemplateData time = new TemplateData();  
		    time.setColor("#173177");  
		    time.setValue(""+findLaterCount +"人  ");
		    //time.setValue(channel.getTime());
		    m.put("time", time);
		    
		    
		    
		    
		    temp.setData(m);
		    String jsonString = JSONObject.fromObject(temp).toString();
		    JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonString);
		    System.out.println(jsonObject);
		    int result = 0;
		    if (null != jsonObject) {  
		         if (0 != jsonObject.getInt("errcode")) {  
		             result = jsonObject.getInt("errcode");
		             log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
		         }  
		     }
		    log.info("模板消息发送结果："+result);
	 }


	public void send_template_message1(String appId, String appSecret, String openId,long findAlertCount,long findLaterCount  ,String onday) {

		AccessToken token = WeixinUtil.getAccessToken(appId, appSecret);
		System.out.println(token);
		String access_token = token.getToken();
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;

		WxTemplate temp = new WxTemplate();
		//url="http://192.168.8.35:8089/G.aspx?fId=1^5c21ec35-28cb-46a8-bbcf-999d4b2ac217^1";
		temp.setUrl("http://"+Cfg.getConfig("domainname")+"/weixin/alertAction_findAlert.action?pe.pageNum=1&onday ='"+onday+"'");
		//temp.setUrl(channel.getImgurl());
		temp.setTouser(openId);
		temp.setTopcolor("#173177");

		temp.setTemplate_id(Cfg.getConfig("Template_id"));
		Map<String,TemplateData> m = new HashMap<String,TemplateData>();

//		TemplateData first = new TemplateData();
//		first.setColor("#173177");
//		first.setValue("昨日人员统计信息如下:");
//		m.put("user", first);

		/**
		 *
		 * {{user.DATA}} 您好，人脸识别全校报表已发布，请查阅
		 考勤日期：{{time.DATA}}
		 考勤结果：总人数：{{total.DATA}}，实习人数：{{sx.DATA}}，请假人数：{{qj.DATA}}，迟归人数：{{cg.DATA}}，未归人数：{{wg.DATA}}，未归报警：{{wgbj.DATA}}，未出报警：{{wcbj.DATA}}，在校预警：{{zxyj.DATA}}
		 *
		 */

		TemplateData userData = new TemplateData();
		userData.setColor("#173177");
		userData.setValue("王校长 您好，人脸识别全校报表已发布，请查阅");
		m.put("user", userData);

		TemplateData timeData = new TemplateData();
		timeData.setColor("#173177");
		timeData.setValue("2018-01-01");
		m.put("time", timeData);

		TemplateData totalData = new TemplateData();
		totalData.setValue("1000");
		m.put("total", totalData);

		TemplateData szData = new TemplateData();
		szData.setValue("1000");
		m.put("sx", szData);

		TemplateData qjData = new TemplateData();
		qjData.setValue("1000");
		m.put("qj", qjData);

		TemplateData cgData = new TemplateData();
		cgData.setValue("1000");
		m.put("cg", cgData);

		TemplateData wgData = new TemplateData();
		wgData.setValue("1000");
		m.put("wg", wgData);

		TemplateData wgbjData = new TemplateData();
		wgbjData.setValue("1000");
		m.put("wgbj", wgbjData);

		TemplateData wcbjData = new TemplateData();
		wcbjData.setValue("1000");
		m.put("wcbj", wcbjData);

		TemplateData zxyjData = new TemplateData();
		zxyjData.setValue("1000");
		m.put("zxyj", zxyjData);





		temp.setData(m);
		String jsonString = JSONObject.fromObject(temp).toString();
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonString);
		System.out.println(jsonObject);
		int result = 0;
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("错误 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		log.info("模板消息发送结果："+result);
	}
}
