package com.tdhz.util;/**
 * Created by liushuai2 on 2018/6/20.
 */

import org.hibernate.dialect.SQLServer2008Dialect;

/**
 * Package : com.tdhz.util
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月20日 23:35
 */
public class MySQLServer2008Dialect extends SQLServer2008Dialect {
    public MySQLServer2008Dialect() {
        super();
        registerHibernateType(1, "string");
        registerHibernateType(-9, "string");
        registerHibernateType(-16, "string");
        registerHibernateType(3, "double");
    }
}
