package com.tdhz.util;
/**
 *	常量参考定义 对应数据库表 sys_paracfg中的id
 * @author TD-PC
 *
 */
public class ParaConstant {
	/**
     * 上次处理的通过记录的中最新一条记录的ID
     */
	public static final Integer LAST_DEAL_TG_ID = 1;
	
	/**
	 * 晚归异常参考时间
	 */
	public static final Integer BACK_LATER_TIME = 2;
	
	/**
     * 未归预警生成参考时间（小时）
     */
	public static final Integer NO_BACK_ALERT_HOURS = 3;
	
	/**
     * 未出预警生成参考时间（小时）
     */
	public static final Integer NO_OUT_ALERT_HOURS = 4;
	
	/**
     * 假期在校预警生成参考时间（小时）
     */
	public static final Integer HOLIDAY_IN_ALERT_HOURS = 5;
	
	/**
     * 未归异常生成参考时间（小时）
     */
	public static final Integer NO_BACK_ABNORMITY_HOURS = 6;
    
    /**
     * 未出异常生成参考时间（小时）
     */
	public static final Integer NO_OUT_ABNORMITY_HOURS = 7;
    
    /**
     * 学生人员组别
     */
	public static final Integer STUDENT_PSN_GROUPS = 8;
    
    /**
     * 楼栋上级ID
     */
	public static final Integer BUILDING_P_ID = 9;
    
    /**
     * 设备预警  0不启用 、1启用
     */
	public static final Integer MACHINE_ALARM = 10;
	

}
