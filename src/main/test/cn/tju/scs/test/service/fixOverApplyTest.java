package cn.tju.scs.test.service;

import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.manager.UserManager;
import cn.tju.scs.manager.impl.AuditManagerImpl;
import cn.tju.scs.manager.impl.UserManagerImpl;
import cn.tju.scs.service.impl.FixMoneyApplyOperator;
import cn.tju.scs.service.impl.FixOverTimeApplyOperator;
import cn.tju.scs.util.ApplyDoGenerator;
import cn.tju.scs.util.UserDoGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liu on 16-12-6.
 */
@RunWith(MockitoJUnitRunner.class)
public class fixOverApplyTest {
    @InjectMocks
    AuditManager auditManager = new AuditManagerImpl();
    @InjectMocks
    UserManager userManager = new UserManagerImpl();

    @Mock
    ApplyDAO applyDAO;

    @Mock
    UserDAO userDAO;
    @Mock
    FixOverTimeApplyOperator fixOverTimeApplyOperator;

    UserDO userDO = new UserDO();
    ApplyDO applyDO = new ApplyDO();
    ApplyDO param = new ApplyDO();

    @Before
    public void setUp()throws Exception{
        userDO.setUserId(1L);
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2016-05-04");
        applyDO.setStartDate(date);
        param.setStartDate(date);
        Date date1 = format.parse("2016-05-06");
        applyDO.setEndDate(date1);
        Date date2 = format.parse("2016-06-03");
        param.setEndDate(date2);
    }
    @Test
    public void doOperate_test() throws Exception{
        ApplyDoGenerator applyDoGenerator = new ApplyDoGenerator();
        UserDoGenerator userDoGenerator = new UserDoGenerator();
        for(int i = 0 ; i <= 30 ; i++){
            applyDoGenerator.generator(2);
            UserDO userDO_P = new UserDO();
            ApplyDO applyDO_P = new ApplyDO();
            applyDO_P = applyDoGenerator.generator(2);
            userDO_P = userDoGenerator.generator();
            System.out.println(applyDO_P.getStartDate());
            System.out.println(applyDO_P.getEndDate());
            fixOverTimeApplyOperator.doOperate(userDO_P.getUserId(),applyDO_P.getStartDate(),applyDO_P.getEndDate(),"haha");
        }
    }
}
