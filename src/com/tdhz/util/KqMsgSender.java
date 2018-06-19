package com.tdhz.util;/**
 * Created by liushuai2 on 2018/6/17.
 */

import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tdhz.dto.KqMsgDTO;
import com.tdhz.dto.WxMsgDTO;
import com.tdhz.pojo.AccessToken;
import com.tdhz.pojo.TemplateData;
import com.tdhz.pojo.WxTemplate;

/**
 * Package : com.tdhz.util
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月17日 12:30
 */
public class KqMsgSender {
    private static final Logger logger = LoggerFactory.getLogger(KqMsgSender.class);
    private static final String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";

    public static boolean sendKqMsg(WxMsgDTO<KqMsgDTO> wxMsg){
        String appId = wxMsg.getAppId();
        String appSecret = wxMsg.getAppSecret();
        String openId = wxMsg.getOpenId();
        KqMsgDTO kqMsgDTO = wxMsg.getData();
        logger.info("考勤消息发送：appId={}, toUser={}", new Object[]{appId, openId});
        AccessToken token = WeixinUtil.getAccessToken(appId, appSecret);
        logger.info("获取到的token信息：token={}, expire={}", new Object[]{token.getToken(), token.getExpiresIn()});
        String sendUrl = String.format(SEND_URL, token.getToken());
        WxTemplate mainTemplate = kqMsgDTO.getWxTemplate();
        mainTemplate.setUrl(wxMsg.getUrl());
        mainTemplate.setTouser(openId);
        mainTemplate.setTemplate_id(wxMsg.getTemplateId());
        Map<String,TemplateData> dataMap = kqMsgDTO.getTemplateData();
        mainTemplate.setData(dataMap);

        String requestBody = JSONObject.fromObject(mainTemplate).toString();
        JSONObject responseBody = WeixinUtil.httpRequest(sendUrl, "POST", requestBody);
        logger.info("发送消息内容：{}，响应：{}", new Object[]{requestBody, responseBody});
        int result = 0;
        if (null != responseBody) {
            if (0 != responseBody.getInt("errcode")) {
                result = responseBody.getInt("errcode");
                logger.error("错误 errcode:{} errmsg:{}", new Object[]{responseBody.getInt("errcode"), responseBody.getString("errmsg")});
                return false;
            }else {
                logger.info("消息发送成功");
                return true;
            }
        }
        return false;
    }

}
