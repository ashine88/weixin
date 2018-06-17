package com.tdhz.action;/**
 * Created by liushuai2 on 2018/6/17.
 */

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Package : com.tdhz.action
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月17日 14:48
 */
@Controller
@Scope("prototype")
public class KqMainAction {
    String name;
    public String index(){

        name = "liushuai";

        return "index";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
