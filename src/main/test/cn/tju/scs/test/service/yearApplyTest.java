package cn.tju.scs.test.service;

import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.exception.Exceptions;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.manager.UserManager;
import cn.tju.scs.manager.impl.AuditManagerImpl;
import cn.tju.scs.manager.impl.UserManagerImpl;
import cn.tju.scs.service.impl.YearApplyOperate;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;

/**
 * Created by liu on 16-12-4.
 */
@RunWith(MockitoJUnitRunner.class)
public class yearApplyTest {
    @InjectMocks
    AuditManager auditManager = new AuditManagerImpl();
    @InjectMocks
    UserManager userManager = new UserManagerImpl();

    @Mock
    ApplyDAO applyDAO;

    @Mock
    UserDAO userDAO;
    @Mock
    YearApplyOperate yearApplyOperate;

    UserDO userDO = new UserDO();
    ApplyDO applyDO = new ApplyDO();
    ApplyDO param = new ApplyDO();

    @Before
    public void setUp() throws Exception{
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
        Mockito.doNothing().when(applyDAO).insertApplyDO(any(ApplyDO.class));
        yearApplyOperate.doOperate(1L,applyDO.getStartDate(),applyDO.getEndDate(),"haha");
//        yearApplyOperate.doOperate(1L,param.getStartDate(),param.getEndDate(),"haha");
    }
    @Test
    public void doOperate_Exception()throws Exception{
        try {
            Mockito.doThrow(new DAOException()).when(applyDAO).insertApplyDO(any(ApplyDO.class));
            yearApplyOperate.doOperate(1L,param.getStartDate(),param.getEndDate(),"haha");
        }catch (BLLException e){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH.getErrorCode());
            return;
        }
//        fail("未抛出期望异常");
    }
}
