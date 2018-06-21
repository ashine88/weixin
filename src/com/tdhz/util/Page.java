package com.tdhz.util;/**
 * Created by liushuai2 on 2018/6/20.
 */

import org.apache.poi.hssf.record.formula.functions.T;

import java.util.List;

/**
 * Package : com.tdhz.util
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月20日 16:04
 */
public class Page<T> extends PageBean {
    List<T> items;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
