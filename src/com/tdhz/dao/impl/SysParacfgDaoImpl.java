package com.tdhz.dao.impl;/**
 * Created by liushuai2 on 2018/6/19.
 */

import com.tdhz.dao.SysParacfgDao;
import com.tdhz.pojo.SysParacfg;
import net.sf.json.JSONObject;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package : com.tdhz.dao.impl
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月19日 14:01
 */
@Repository
public class SysParacfgDaoImpl extends HibernateDaoSupport implements SysParacfgDao {
    public static final Logger logger = LoggerFactory.getLogger(SysParacfgDao.class);

    @Autowired
    public void setSessionFactory01(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    @Override
    public List<SysParacfg> get() {
        logger.info("获取所有的配置信息");
        List<SysParacfg> cfgs = this.getHibernateTemplate().loadAll(SysParacfg.class);
        logger.info("获取所有的配置信息 {}", JSONObject.fromObject(cfgs).toString());
        return cfgs;
    }

    @Override
    public SysParacfg get(Integer id) {
        logger.info("通过id获取配置信息 id：{}", id);
        SysParacfg cfg = this.getHibernateTemplate().get(SysParacfg.class, id);

        return cfg;
    }
}
