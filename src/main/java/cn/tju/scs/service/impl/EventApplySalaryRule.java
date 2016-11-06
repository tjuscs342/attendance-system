package cn.tju.scs.service.impl;

import cn.tju.scs.service.SalaryRule;
import org.springframework.stereotype.Service;

/**
 * Created by liu on 16-10-30.
 */
@Service("eventSalaryRule")
public class EventApplySalaryRule implements SalaryRule {
    @Override
    public int getSalaryInc(int num){
        return -num;
    }
}
