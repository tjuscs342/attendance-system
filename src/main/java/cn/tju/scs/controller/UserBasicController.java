package cn.tju.scs.controller;

import cn.tju.scs.manager.UserManager;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * Created by lichen.ll on 2016/8/28.
 */
public class UserBasicController {
    Logger log = Logger.getLogger(UserController.class);

    @Resource
    UserManager userManager;
}
