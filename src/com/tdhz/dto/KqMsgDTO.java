package com.tdhz.dto;/**
 * Created by liushuai2 on 2018/6/17.
 */

import com.tdhz.pojo.TemplateData;
import com.tdhz.pojo.WxTemplate;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Package : com.tdhz.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月17日 12:24
 */
public class KqMsgDTO {
    //考勤结果：总人数：{{total.DATA}}，实习人数：{{sx.DATA}}，请假人数：{{qj.DATA}}，迟归人数：{{cg.DATA}}，未归人数：{{wg.DATA}}，未归报警：{{wgbj.DATA}}，未出报警：{{wcbj.DATA}}，在校预警：{{zxyj.DATA}}

    /**
     * 接收人
     */
    private String user;
    /**
     * 报表日期
     */
    private String time;
    /**
     * 总人数
     */
    private long total;
    /**
     * 实习
     */
    private long sx;
    /**
     * 请假
     */
    private long qj;
    /**
     * 迟归
     */
    private long cg;
    /**
     * 未归
     */
    private long wg;
    /**
     * 未归报警
     */
    private long wgbj;
    /**
     * 未出报警
     */
    private long wcbj;
    /**
     * 在校预警
     */
    private long zxyj;


    public KqMsgDTO(){}

    public KqMsgDTO(String user, String time, int total, long sx, long qj, long cg, long wg, long wgbj, long wcbj, long zxyj) {
        this.user = user;
        this.time = time;
        this.total = total;
        this.sx = sx;
        this.qj = qj;
        this.cg = cg;
        this.wg = wg;
        this.wgbj = wgbj;
        this.wcbj = wcbj;
        this.zxyj = zxyj;
    }

    public String getUser() {
        return user;
    }

    public String getWxDataColumn(String field){
        return field + ".DATA";
    }

    public TemplateData getUserTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getUser() +" 您好，人脸识别考勤报表已发布，请查阅");
        templateData.setColor("#173177");
        return templateData;
    }
    public void setUser(String user) {
        this.user = user;
    }


    public String getTime() {
        return time;
    }
    public TemplateData getTimeTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getTime());
        return templateData;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public long getTotal() {
        return total;
    }
    public TemplateData getTotalTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getTotal()+"");
        return templateData;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    public TemplateData getSxTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getSx()+"");
        return templateData;
    }
    public long getSx() {
        return sx;
    }
    public void setSx(int sx) {
        this.sx = sx;
    }

    public TemplateData getQjTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getQj()+"");
        return templateData;
    }
    public long getQj() {
        return qj;
    }
    public void setQj(int qj) {
        this.qj = qj;
    }

    public long getCg() {
        return cg;
    }
    public TemplateData getCgTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getCg()+"");
        return templateData;
    }
    public void setCg(int cg) {
        this.cg = cg;
    }

    public TemplateData getWgTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getCg()+"");
        return templateData;
    }

    public long getWg() {
        return wg;
    }
    public void setWg(int wg) {
        this.wg = wg;
    }

    public long getWgbj() {
        return wgbj;
    }
    public TemplateData getWgbjTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getCg()+"");
        return templateData;
    }

    public void setWgbj(int wgbj) {
        this.wgbj = wgbj;
    }

    public long getWcbj() {
        return wcbj;
    }
    public TemplateData getWcbjTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getCg()+"");
        return templateData;
    }


    public void setWcbj(int wcbj) {
        this.wcbj = wcbj;
    }

    public long getZxyj() {
        return zxyj;
    }

    public TemplateData getZxyjTemplate() {
        TemplateData templateData = new TemplateData();
        templateData.setValue(this.getCg()+"");
        return templateData;
    }

    public void setZxyj(int zxyj) {
        this.zxyj = zxyj;
    }

    public static String firstUpper(String value){
        Assert.notNull(value, "value is null");
        String firstChar = value.substring(0, 1);
        value = firstChar.toUpperCase() + value.substring(1, value.length());
        System.out.println("第一个字母类名" + value);
        return value;
    }

    public static Method getTemplateMethod(Class clzz, String fieldName){
        String methodName = "get" + firstUpper(fieldName) + "Template";
        try {
            Method method = clzz.getMethod(methodName);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Map<String, TemplateData> getTemplateData(){
        Field[] fields = KqMsgDTO.class.getDeclaredFields();
        Map<String, TemplateData> dataMap = new HashMap<>();
        for(Field field : fields){
            String fieldName = field.getName();
            System.out.println("filedName=" + fieldName);
            Method method = getTemplateMethod(KqMsgDTO.class,fieldName);
            try{
                if(method != null){
                    TemplateData templateData = (TemplateData) method.invoke(this);
                    dataMap.put(fieldName, templateData);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return dataMap;
    }

    public WxTemplate getWxTemplate(){
        WxTemplate mainTemplate = new WxTemplate();
        mainTemplate.setTopcolor("#173177");
        return mainTemplate;
    }
    public static void main(String[] args) {
        KqMsgDTO kqMsgDTO = new KqMsgDTO();
        kqMsgDTO.setCg(1);
        kqMsgDTO.setQj(1);
        kqMsgDTO.setTime("2018-01-01");
        kqMsgDTO.setSx(1);
        kqMsgDTO.setTotal(1);
        kqMsgDTO.setUser("胡科长你好");
        kqMsgDTO.setWg(1);
        kqMsgDTO.setWcbj(1);
        kqMsgDTO.setWgbj(1);
        kqMsgDTO.setZxyj(1);

        Map<String, TemplateData> dataMap = kqMsgDTO.getTemplateData();
        System.out.println(dataMap);
    }
}
