package cn.tju.scs.controller;

import cn.tju.scs.exception.BLLException;
import cn.tju.scs.manager.UserManager;
import cn.tju.scs.util.JSONBuilder;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.annotation.Resource;

/**
 * Created by lichen.ll on 2016/8/31.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private UserManager userManager;


    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public Object getUserInfoList (){
        try {
            List list = userManager.getUserList();
            return JSONBuilder.buildSuccessReturn( list );
        }catch( BLLException e ){
            logger.error(e);
            return JSONBuilder.buildErrorReturn( e.getErrorMessage());
        }
    }
}
