package com.tdhz.dto;/**
 * Created by liushuai2 on 2018/6/17.
 */

import com.tdhz.util.Cfg;

import java.io.Serializable;

/**
 * Package : com.tdhz.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月17日 12:22
 */
public class WxMsgDTO<T> implements Serializable {

    private String templateId;
    private String appId;
    private String appSecret;
    private String openId;
    private String url;
    private T data;


    public static WxMsgDTO getInstance(){
        WxMsgDTO wxMsgDTO = new WxMsgDTO();
        wxMsgDTO.setAppId(Cfg.getConfig("appid"));
        wxMsgDTO.setAppSecret(Cfg.getConfig("appsecret"));
        return wxMsgDTO;
    }
    public WxMsgDTO(){
    }
    public WxMsgDTO(T data){
        this.data = data;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
