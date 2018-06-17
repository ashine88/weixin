package com.tdhz;/**
 * Created by liushuai2 on 2018/6/17.
 */

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Package : com.tdhz
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月17日 9:01
 */
public class Main {
    public static void main(String[] args) {
            String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
            String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Bluesky_rpt"; // 连接服务器和数据库
            String userName = "sa"; // 默认用户名
            String userPwd = "YIXIN@0000"; // 安装 sql server 2008 时的密码
            @SuppressWarnings("unused")
            Connection dbConn;
            try {
                Class.forName(driverName);
                dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
                System.out.println("Connection Successful!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
