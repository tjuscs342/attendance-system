package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;

/**
 * Created by yangwentao on 11/4/16.
 */
public class WithMaternitySalaryRule implements SalaryRule{

    @Override
    public int getSalaryInc(int num) {
        return 0;
    }
}
