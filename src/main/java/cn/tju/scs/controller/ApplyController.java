package cn.tju.scs.controller;

import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.service.ApplyOperate;
<<<<<<< HEAD
=======
import cn.tju.scs.service.impl.*;
>>>>>>> lyj_new
import cn.tju.scs.util.DateUtils;
import cn.tju.scs.util.JSONBuilder;
import cn.tju.scs.validator.ApplyTypeValidator;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import vo.UpdateApplyVO;

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
    @Resource(name = "yearApplyOperate")
    ApplyOperate yearApplyOperate;
    @Resource(name = "sickApplyOperate")
    ApplyOperate sickApplyOperate;
    @Resource(name = "marryApplyOperate")
    ApplyOperate marryApplyOperate;
    @Resource(name = "eventApplyOperate")
    ApplyOperate eventApplyOperate;
    @Resource(name = "maternityApplyOperate")
    ApplyOperate maternityApplyOperate;
    @Resource
    ApplyOperate yearApplyOperate;

    @Resource
    ApplyManager applyManager;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Object apply ( Integer type , String reason , String start , String end , HttpSession session){
        UserDO userDO= (UserDO)session.getAttribute("user");
        if(userDO == null) return JSONBuilder.buildErrorReturn("you need to login");
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
<<<<<<< HEAD
            //applyManager.clearUselessApply(userDO.getUserId(),type);
            yearApplyOperate.doOperate(userDO.getUserId(),startDate,endDate,reason);
            //applyManager.applyByType(userDO.getUserId(),startDate,endDate,type,reason);
=======
            if(type == 1){
//                YearApplyOperate yearApplyOperate = new YearApplyOperate();
                yearApplyOperate.doOperate(userDO.getUserId(),startDate,endDate,reason);
            }
            else if(type == 2){
//                SickApplyOperate sickApplyOperate = new SickApplyOperate();
                sickApplyOperate.doOperate(userDO.getUserId(),startDate,endDate,reason);
            }
            else if(type == 3){
//                MarryApplyOperate marryApplyOperate =new MarryApplyOperate();
                marryApplyOperate.doOperate(userDO.getUserId(),startDate,endDate,reason);
            }
            else if(type == 4){
 //               EventApplyOperate eventApplyOperate =new EventApplyOperate();
                eventApplyOperate.doOperate(userDO.getUserId(),startDate,endDate,reason);
            }
            else if(type == 5){
//                MaternityApplyOperate maternityApplyOperate =new MaternityApplyOperate();
                maternityApplyOperate.doOperate(userDO.getUserId(),startDate,endDate,reason);
            }
//            applyManager.clearUselessApply(userDO.getUserId(),type);
//            applyManager.applyByType(userDO.getUserId(),startDate,endDate,type,reason);
>>>>>>> lyj_new
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

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object updateApply ( @RequestParam Long applyId ,
                                @RequestParam(required = false) String start ,
                                @RequestParam(required = false) Integer type,
                                @RequestParam(required = false) String end,
                                @RequestParam(required = false) String reason){
        try {
            Date startDate=null,endDate=null;
            if( start != null )
                startDate= DateUtils.parseDate(start);
            if( end != null )
                endDate = DateUtils.parseDate(end);
            applyManager.updateApplyInfo(applyId , type , startDate , endDate , reason );
        }catch ( ParseException e ){
            return JSONBuilder.buildErrorReturn(e.getMessage());
        }catch ( BLLException e ){
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        }
        return JSONBuilder.buildSuccessReturn(null);
    }

}
