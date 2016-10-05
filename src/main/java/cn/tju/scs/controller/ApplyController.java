package cn.tju.scs.controller;

import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.util.DateUtils;
import cn.tju.scs.util.JSONBuilder;
import cn.tju.scs.validator.ApplyTypeValidator;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by lichen.ll on 2016/9/16.
 */

@RestController
@RequestMapping("/apply")
public class ApplyController {

    private Logger logger = Logger.getLogger(ApplyController.class);

    @Resource
    ApplyManager applyManager;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Object apply ( Integer type , String reason , String start , String end , HttpSession session){
        UserDO userDO= (UserDO)session.getAttribute("user");
        if( type == null || start == null || end == null )
            return JSONBuilder.buildErrorReturn("参数不完整");
        if(!ApplyTypeValidator.checkType(type))
            return JSONBuilder.buildErrorReturn("当前类型的假期不支持");
        try {
            Date startDate = DateUtils.parseDate(start);
            Date endDate = DateUtils.parseDate(end);
            if( DateUtils.getDuration(startDate,endDate) < 1 ){
                return JSONBuilder.buildErrorReturn("请假日期区间不合法");
            }
            applyManager.clearUselessApply(userDO.getUserId(),type);
            applyManager.applyByType(userDO.getUserId(),startDate,endDate,type,reason);
        }catch ( ParseException e ){
            return JSONBuilder.buildErrorReturn("日期格式错误");
        }catch ( BLLException e ){
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        }
        return JSONBuilder.buildSuccessReturn(null);
    }

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    public Object getApplys ( HttpSession session ){
        UserDO userDO = (UserDO) session.getAttribute("user");
        long userId = userDO.getUserId();
        try {
            List<ApplyDO> list = applyManager.selectApplys(userId);
            return JSONBuilder.buildSuccessReturn(list);
        }catch ( BLLException e ){
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        }
    }

    @RequestMapping(method=RequestMethod.PUT)
    @ResponseBody
    public Object updateApply ( Long applyId , Integer type , String start , String end ){
        try {
            Date startDate = DateUtils.parseDate(start);
            Date endDate = DateUtils.parseDate(end);
            applyManager.updateApplyInfo(applyId , type , startDate , endDate );
        }catch ( ParseException e ){
            return JSONBuilder.buildErrorReturn(e.getMessage());
        }catch ( BLLException e ){
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        }
        return JSONBuilder.buildSuccessReturn(null);
    }

}
