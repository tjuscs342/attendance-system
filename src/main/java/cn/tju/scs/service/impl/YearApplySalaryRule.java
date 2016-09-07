package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public class YearApplySalaryRule implements SalaryRule{

    public static final Integer DAYS_OF_YEAR = 7;


    @Override
    public int getSalaryInc(int num) {
        return 0;
    }
}
