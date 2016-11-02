package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;

/**
 * Created by liu on 16-10-30.
 */
public class MaternityApplySalaryRule implements SalaryRule{
    public static final Integer DAYS_OF_MATERNITY = 128;
    public static final Integer DAYS_OF_EARLY_MATERNITY = 98;

    @Override
    public int getSalaryInc ( int num ) {
        return 0;
    }
}
