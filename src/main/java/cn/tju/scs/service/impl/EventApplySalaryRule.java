package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;

/**
 * Created by liu on 16-10-30.
 */
public class EventApplySalaryRule implements SalaryRule {
    @Override
    public int getSalaryInc(int num){
        return -num;
    }
}
