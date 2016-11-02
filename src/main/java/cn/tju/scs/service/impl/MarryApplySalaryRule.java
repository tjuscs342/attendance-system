package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;

/**
 * Created by liu on 16-10-26.
 */
public class MarryApplySalaryRule implements SalaryRule{
    public static final Integer DAYS_OF_REMARRY = 3;
    public static final Integer DAYS_OF_MARRY = 10;

    @Override
    public int getSalaryInc(int num){ return 0; }
}
