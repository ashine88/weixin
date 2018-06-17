package com.tdhz.action;/**
 * Created by liushuai2 on 2018/6/17.
 */

import com.tdhz.dto.KqMsgDTO;
import com.tdhz.dto.WxMsgDTO;
import com.tdhz.util.Cfg;
import com.tdhz.util.KqMsgSender;
import com.tdhz.util.SendOrderPaySuccessMsg;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Package : com.tdhz.action
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月17日 9:57
 */
@Controller
@Scope("prototype")
public class ManualSendMsg {

    public @ResponseBody  String sendMsg(){
        System.out.println("---------手动发送消息-----------");
        String openId = "oF0WQ0eOs9wLvo4uH4vwR0HUZg8M";
        SendOrderPaySuccessMsg s = new SendOrderPaySuccessMsg();
        KqMsgDTO kqMsgDTO = new KqMsgDTO();
        kqMsgDTO.setZxyj(1);
        kqMsgDTO.setWcbj(1);
        kqMsgDTO.setWgbj(1);
        kqMsgDTO.setUser("胡科长");
        kqMsgDTO.setCg(1);
        kqMsgDTO.setWg(100);
        kqMsgDTO.setWcbj(100);
        kqMsgDTO.setQj(1);
        kqMsgDTO.setSx(1);
        kqMsgDTO.setTotal(100);
        kqMsgDTO.setTime("2018-01-05");
        WxMsgDTO<KqMsgDTO> wxMsgDTO = WxMsgDTO.getInstance();
        wxMsgDTO.setOpenId(openId);
        wxMsgDTO.setData(kqMsgDTO);
        wxMsgDTO.setTemplateId(Cfg.getConfig("Template_id"));
        KqMsgSender.sendKqMsg(wxMsgDTO);

        return "{}";
    }
}
