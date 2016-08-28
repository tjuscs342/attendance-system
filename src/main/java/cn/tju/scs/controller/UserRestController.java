package cn.tju.scs.controller;

import cn.tju.scs.domain.UserDO;
import cn.tju.scs.util.JSONBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lichen.ll on 2016/8/28.
 */

@RestController
@RequestMapping("/userInfo")
public class UserRestController extends UserBasicController{

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public Object userInfo ( ){
        log.info("查詢指定用戶信息");
        UserDO userDO = userManager.getUser( 111 );
        return JSONBuilder.buildSuccessReturn( userDO.getUserName());
    }
}
