package cn.tju.scs.controller;

import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.service.ApplyOperate;
import cn.tju.scs.service.impl.*;

import cn.tju.scs.util.DateUtils;
import cn.tju.scs.util.JSONBuilder;
import cn.tju.scs.validator.ApplyTypeValidator;
import com.google.common.collect.Lists;
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
    @Resource(name = "withMaternityOperator")
    ApplyOperate withmaternityOperator;
    @Resource(name = "fixOverTimeApplyOperator")
    ApplyOperate fixOverTimeApplyOperator;
    @Resource(name = "overTimeApplyOperator")
    ApplyOperate overTimeApplyOperator;
    @Resource(name = "fixMoneyApplyOperator")
    ApplyOperate fixMoneyApplyOperator;
    @Resource
    ApplyManager applyManager;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object apply(Integer type, String reason, String start, String end, HttpSession session) {
        UserDO userDO = (UserDO) session.getAttribute("user");
        if (userDO == null) return JSONBuilder.buildErrorReturn("you need to login");
        if (type == null || start == null || end == null)
            return JSONBuilder.buildErrorReturn("参数不完整");
        if (!ApplyTypeValidator.checkType(type))
            return JSONBuilder.buildErrorReturn("当前类型的假期不支持");
        try {
            Long l = new Long(0);
            Date startDate = DateUtils.parseDate(start);
            Date endDate = DateUtils.parseDate(end);
            if (isApply(userDO.getUserId(),l,startDate, endDate) == false) {
                return JSONBuilder.buildErrorReturn("此段时间已请过假");
            }
            if (DateUtils.getDuration(startDate, endDate) < 1) {
                return JSONBuilder.buildErrorReturn("请假日期区间不合法");
            }
            if (type == 1) {
//                YearApplyOperate yearApplyOperate = new YearApplyOperate();
                yearApplyOperate.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 2) {
//                SickApplyOperate sickApplyOperate = new SickApplyOperate();
                sickApplyOperate.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 3) {
//                MarryApplyOperate marryApplyOperate =new MarryApplyOperate();
                marryApplyOperate.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 4) {
                //               EventApplyOperate eventApplyOperate =new EventApplyOperate();
                eventApplyOperate.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 5) {
//                MaternityApplyOperate maternityApplyOperate =new MaternityApplyOperate();
                maternityApplyOperate.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 6) {
                withmaternityOperator.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 7) {
                overTimeApplyOperator.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 8) {
                fixOverTimeApplyOperator.doOperate(userDO.getUserId(), startDate, endDate, reason);
            } else if (type == 9) {
                fixMoneyApplyOperator.doOperate(userDO.getUserId(), startDate, endDate, reason);
            }
//            applyManager.clearUselessApply(userDO.getUserId(),type);
//            applyManager.applyByType(userDO.getUserId(),startDate,endDate,type,reason);
        } catch (ParseException e) {
            return JSONBuilder.buildErrorReturn("日期格式错误");
        } catch (BLLException e) {
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        } catch (Exception e) {
            return JSONBuilder.buildErrorReturn(e.getMessage());
        }
        return JSONBuilder.buildSuccessReturn(null);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object getApplys(HttpSession session) {
        UserDO userDO = (UserDO) session.getAttribute("user");
        long userId = userDO.getUserId();
        try {
            List<ApplyDO> list = applyManager.selectApplys(userId);
            return JSONBuilder.buildSuccessReturn(list);
        } catch (BLLException e) {
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        }
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    @ResponseBody
    public Object updateApply(Long applyId, String start, Integer type, String end, String reason, HttpSession session) {
        UserDO userDO = (UserDO) session.getAttribute("user");
        try {
            if (applyId == null) {
                return JSONBuilder.buildErrorReturn("没有选定假期");
            }
            Date startDate = null, endDate = null;
            if (start != null)
                startDate = DateUtils.parseDate(start);
            if (end != null)
                endDate = DateUtils.parseDate(end);
            if (isApply(userDO.getUserId(), applyId,startDate, endDate) == false) {
                return JSONBuilder.buildErrorReturn("此段时间已请过假");
            }
            applyManager.updateApplyInfo(applyId, type, startDate, endDate, reason);
        } catch (ParseException e) {
            return JSONBuilder.buildErrorReturn(e.getMessage());
        } catch (BLLException e) {
            return JSONBuilder.buildErrorReturn(e.getErrorMessage());
        } catch (Exception e){
            return JSONBuilder.buildErrorReturn(e.getMessage());
        }
        return JSONBuilder.buildSuccessReturn(null);
    }

    public boolean isApply(Long userId,Long applyId, Date start, Date end) throws Exception {

        List<ApplyDO> list = Lists.newArrayList();
        list = applyManager.selectApplys(userId);
        for (ApplyDO applyDO1 : list) {
            Integer result = applyDO1.getResult();
            Long id = applyDO1.getApplicationId();
            if (result.compareTo(3)==0 || id.compareTo(applyId) == 0) {
                continue;
            }
                Date date1 = applyDO1.getStartDate();
                Date date2 = applyDO1.getEndDate();
                if (end.compareTo(date1) < 0 || start.compareTo(date2) > 0) {
                    continue;
                }
                else return false;
            }
            return true;
        }
    }