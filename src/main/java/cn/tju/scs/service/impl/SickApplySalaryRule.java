package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;
import org.springframework.stereotype.Service;

/**
 * Created by Liu on 2016/10/24.
 */
@Service("sickSalaryRule")
public class SickApplySalaryRule implements SalaryRule {

    public static final Integer DAYS_OF_SICK = 12;

    @Override
    public int getSalaryInc(int num){
        return -num+1;
    }
}