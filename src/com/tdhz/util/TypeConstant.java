package com.tdhz.util;
/**
 * 定义预警或异常类型标识常量
 * @author TD-PC
 *
 */
public class TypeConstant {
public static void main(String[] args) {
	String m = "2017-04-23 23:16:01.250".substring(11, 16);
	Integer i = "2017-04-23 23:16:01.250".substring(11, 16).compareTo("22:00:00");
	System.out.println(i);
}

	/**
     * 异常：非法通过
     */
    public static  final int ABN_TYPE_ILLEGAL_PASS = 1;
    
    /**
     * 异常：无卡通过
     */
    public static  final int ABN_TYPE_NOCARD_TG = 2;
    
    /**
     * 异常：晚归
     */
    public static  final int ABN_TYPE_BACK_LATE = 3;
    
    /**
     * 异常：未出
     */
    public static  final int ABN_TYPE_NO_OUT = 4;
    
    /**
     * 异常：未归
     */
    public static  final int ABN_TYPE_NO_BACK = 5;
    
    /**
     * 异常：假期在校
     */
    public static  final int ABN_TYPE_HOLIDAY_IN = 6;
    
    /**
     * 异常：非学生
     */
    public static  final int ABN_TYPE_NOT_STUDENT = 7;
    
    public static  final String ABN_STATUS_ACTIVE = "ACTIVE";
    
    public static  final String ABN_STATUS_INACTIVE = "INACTIVE";
    
    /**
     * 预警：假期在校
     */
    public static  final int ALERT_TYPE_HOLIDAY_IN = 6;
    
    /**
     * 预警：未出
     */
    public static  final int ALERT_TYPE_NO_OUT = 4;
    
    /**
     * 预警：未归
     */
    public static  final int ALERT_TYPE_NO_BACK = 5;
    
    /**
     * 人员状态：在寝/通过：进
     */
    public static  final String PERSON_STATE_IN = "4_1";
    
    /**
     * 人员状态：外出/通过：出
     */
    public static  final String PERSON_STATE_OUT = "4_2";
    
    /**
     * 班级休假：暑假
     */
    public static  final String HOLIDAY_CLASS_SUMMER = "1";
    
    /**
     * 班级休假：寒假
     */
    public static  final String HOLIDAY_CLASS_WINTER = "2";
    
    /**
     * 班级休假：实习
     */
    public static  final String HOLIDAY_CLASS_WORK = "3";
    
    /**
     * 班级休假：其他
     */
    public static  final String HOLIDAY_CLASS_OTHER = "4";
    
    /**
     * 班级休假：已销假
     */
    public static  final String HOLIDAY_CLASS_CANCEL = "0";
    
    /**
     * 个人请假：已销假
     */
    public static  final String HOLIDAY_PERSON_CANCEL = "0";
    
    /**
     * 个人请假：病假
     */
    public static  final String HOLIDAY_PERSON_ILL = "1";
    
    /**
     * 个人请假：事假
     */
    public static  final String HOLIDAY_PERSON_ABSENCE = "2";
    
    /**
     * 个人请假：实习
     */
    public static  final String HOLIDAY_PERSON_WORK = "3";
    
    /**
     * 个人请假：其他
     */
    public static  final String HOLIDAY_PERSON_OTHER = "4";
    
    /**
     * 通过方式：卡片
     */
    public static  final String TGMODE_CARD = "42_1";
    
    /**
     * 通过方式：刷脸
     */
    public static  final String TGMODE_BRUSH_FACE = "42_2";
    
    /**
     * 通过方式：学号
     */
    public static  final String TGMODE_STUDENT_NUM = "42_3";
    
    /**
     * 通过方式：二维码
     */
    public static  final String TGMODE_QR_CODE = "42_4";
}
