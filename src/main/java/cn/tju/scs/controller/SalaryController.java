package cn.tju.scs.controller;

import cn.tju.scs.constant.ApplyTypes;
import cn.tju.scs.constant.AuditStatus;
import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.service.SalaryRule;
import cn.tju.scs.service.impl.EventApplyOperate;
import cn.tju.scs.util.DateUtils;
import cn.tju.scs.util.JSONBuilder;
import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by liu on 16-11-5.
 */
@RestController
@RequestMapping(value = "/salary")
public class SalaryController {
    private Logger logger = Logger.getLogger(SalaryController.class);
    @Resource
    ApplyManager applyManager;
    @Resource(name = "sickSalaryRule")
    SalaryRule sickSalaryRule;
    @Resource(name = "eventSalaryRule")
    SalaryRule eventSalaryRule;
    @Resource
    ApplyDAO applyDAO;

    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    @RequestMapping(value="checksalary",method= RequestMethod.POST)
    @ResponseBody
    public Object getMonthSalary(Long userID, String date) {
        int salary = 0;
     try {
         Date param = new Date();
         param = DateUtils.parseDate(date);
         Calendar time = Calendar.getInstance();
         time.setTime(param);
         try {
             ApplyDO applyDO = new ApplyDO();
             applyDO.setUserId(userID);
             List<ApplyDO> sickList = applyManager.selectApplysByType(userID, ApplyTypes.APPLY_SICK);
             List<ApplyDO> eventList = applyManager.selectApplysByType(userID, ApplyTypes.APPLY_EVENT);
             if (sickList == null && eventList == null)
                 return JSONBuilder.buildSuccessReturn("0");
             if (sickList != null)
             for (ApplyDO item : sickList) {
                 for (int day = 1; day <= time.getActualMaximum(Calendar.DAY_OF_MONTH); day++) {
                     time.set(Calendar.DATE, day);
                     if (time.getTime().compareTo(item.getStartDate()) >= 0
                             && time.getTime().compareTo(item.getEndDate()) <= 0)
                         salary--;
                     if ((time.getTime().compareTo(item.getStartDate()) >= 0
                             && time.getTime().compareTo(item.getEndDate()) <= 0) && (DateUtils.isWeekend(time.getTime()) ||
                             ((time.getTime().getMonth() + 1 == 5) && time.getTime().getDate() == 1) ||
                             ((time.getTime().getMonth() + 1 == 1) && time.getTime().getDate() == 1) ||
                             ((time.getTime().getMonth() + 1 == 10) && (time.getTime().getDate() == 1 || time.getTime().getDate() == 2
                                     || time.getTime().getDate() == 3))))
                         salary++;
                 }
//                 if (sickList != null) salary++;
             }
             if(eventList != null)
             for (ApplyDO item : eventList) {
                 for (int day = 1; day <= time.getActualMaximum(Calendar.DAY_OF_MONTH); day++) {
                     time.set(Calendar.DATE, day);
                     if (time.getTime().compareTo(item.getStartDate()) >= 0
                             && time.getTime().compareTo(item.getEndDate()) <= 0)
                         salary--;
                     if (DateUtils.isWeekend(time.getTime()) ||
                             ((time.getTime().getMonth() + 1 == 5) && time.getTime().getDate() == 1) ||
                             ((time.getTime().getMonth() + 1 == 1) && time.getTime().getDate() == 1) ||
                             ((time.getTime().getMonth() + 1 == 10) && (time.getTime().getDate() == 1 || time.getTime().getDate() == 2
                                     || time.getTime().getDate() == 3)))
                         salary++;
                 }
             }
             return JSONBuilder.buildSuccessReturn(salary);

         } catch (BLLException e) {
             logger.error(e);
             return JSONBuilder.buildErrorReturn(e.getErrorMessage());
         }
     }catch (ParseException e){
         return JSONBuilder.buildErrorReturn("日期格式错误");
     }
    }
}
