package cn.tju.scs.controller;

import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.manager.UserManager;
import cn.tju.scs.util.JSONBuilder;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by lichen.ll on 2016/8/31.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private UserManager userManager;


    @RequestMapping(value="checkme",method= RequestMethod.GET)
    @ResponseBody
    public Object getUserInfo ( HttpSession session ){
        try {
            UserDO userDO = (UserDO)session.getAttribute("user");
            return JSONBuilder.buildSuccessReturn( userDO );
        }catch( Throwable e ){
            logger.error(e);
            return JSONBuilder.buildErrorReturn( e.getMessage());
        }
    }

    @RequestMapping(value="login",method= RequestMethod.POST)
    @ResponseBody
    public Object login (String userName , String password , HttpSession session ){
        try{
            if(Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password))
                return JSONBuilder.buildErrorReturn("用户名或密码为空");
            UserDO userDO = userManager.getUserInfoByName( userName );
            if( userDO == null ){
                return JSONBuilder.buildErrorReturn("用户不存在");
            }
            if( !userDO.getUserName().equals(userName) || !userDO.getPassword().equals(password)){
                return JSONBuilder.buildErrorReturn("用户名密码错误");
            }

            UserDO boss_userDO = userManager.getUserInfoById( userDO.getBossId());
            Map<String, Object> result_noBoss = Maps.newHashMap();
            result_noBoss.put("Warning","boss not exist");
            if (boss_userDO == null){
                session.setAttribute("user",userDO);
                return JSONBuilder.buildSuccessReturn(result_noBoss);
            }
            Map<String, Object> result = Maps.newHashMap();
            result.put("bossName",boss_userDO.getUserName());
            session.setAttribute("user", userDO );
            return JSONBuilder.buildSuccessReturn( result);
        }catch ( BLLException e ){
            logger.error(e);
            return JSONBuilder.buildErrorReturn( e.getErrorMessage());
        }
    }

}
