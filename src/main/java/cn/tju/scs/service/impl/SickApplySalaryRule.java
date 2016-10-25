package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;

/**
 * Created by Liu on 2016/10/24.
 */
public class SickApplySalaryRule implements SalaryRule {

    public static final Integer DAYS_OF_SICK = 12;

    @Override
    public int getSalaryInc(int num){
        if (num <= 12) return 0;
        else return 12 - num;
    }
}