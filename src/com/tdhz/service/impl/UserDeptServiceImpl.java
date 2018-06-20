package com.tdhz.service.impl;/**
 * Created by liushuai2 on 2018/6/18.
 */

import com.tdhz.dao.UsersDeptDao;
import com.tdhz.dto.UserDeptDTO;
import com.tdhz.pojo.User_Dept;
import com.tdhz.service.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Package : com.tdhz.service.impl
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月18日 11:44
 */
@Service
public class UserDeptServiceImpl  implements UserDeptService {
    @Autowired
    private UsersDeptDao usersDeptDao;


    /**
     * 根据用户获取所管理的班级
     * @param userId
     * @return
     */
    @Override
    public List<UserDeptDTO> getBJ(Integer userId){
        List<User_Dept> user_depts = usersDeptDao.getBJ(userId);
        List<UserDeptDTO> userDeptDTOS = new ArrayList<>();
        // 添加全部

        if(user_depts != null && user_depts.size() > 0 ){
            for(User_Dept ud :user_depts){
                UserDeptDTO userDeptDTO = new UserDeptDTO();
                userDeptDTO.setUserId(userId);
                userDeptDTO.setDeptId(ud.getDept_id().getDeptId());
                userDeptDTO.setDeptName(ud.getDept_id().getDeptName());
                userDeptDTOS.add(userDeptDTO);
            }
        }
        return userDeptDTOS;
    }

}
