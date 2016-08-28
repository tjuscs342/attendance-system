package cn.tju.scs.controller;

import cn.tju.scs.domain.UserDO;
import cn.tju.scs.manager.UserManager;
import cn.tju.scs.util.JSONBuilder;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by lichen.ll on 2016/8/28.
 */

@Controller
@RequestMapping("/user")
public class UserController extends UserBasicController{

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request ){
        log.info("查询指定用户信息");
        Integer userId = Integer.parseInt( request.getParameter("userId"));
        UserDO userDO = userManager.getUser( userId );
        request.setAttribute("userName" , userDO.getUserName());
        return "showUser";
    }
}
