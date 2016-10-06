package cn.tju.scs.controller;

import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.util.JSONBuilder;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by lichen.ll on 2016/9/16.
 */
@RestController
@RequestMapping("/audit")
public class AuditController {

    private Logger logger = Logger.getLogger(AuditController.class);

    @Resource
    AuditManager auditManager;

    @RequestMapping(method = RequestMethod.GET )
    @ResponseBody
    public Object getApplyToAudit ( HttpSession session ){
        try{
            UserDO userDO = (UserDO)session.getAttribute("user");
            long userId = userDO.getUserId();
            List list = auditManager.selectApplys(userId);
            System.out.println(list.size());
            return JSONBuilder.buildSuccessReturn(list);
        }catch ( BLLException e ){
            logger.error(e.getErrorCode());
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT )
    @ResponseBody
    public Object audit (@RequestParam String auditStatus ,
                         @RequestParam String remark ,
                         @RequestParam Long applicationId ,
                         @RequestParam HttpSession session ){
        try{
            UserDO userDO = (UserDO)session.getAttribute("user");
            int status = 1;
            if( auditStatus.equals("agree"))
                status = 2;
            else if ( auditStatus.equals("disagree"))
                status = 3;
            if( status == 1 )
                return JSONBuilder.buildErrorReturn("请给出正确的审批意见");
            auditManager.auditApply(applicationId,status,remark,userDO.getUserId(),userDO.getUserName());
            return JSONBuilder.buildSuccessReturn(null);
        }catch ( BLLException e ){
            e.printStackTrace();
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        }
    }
}
