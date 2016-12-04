package cn.tju.scs.manager.impl;

import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.manager.ApplyManager;
import cn.tju.scs.manager.AuditManager;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
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

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.any;

/**
 * Created by liu on 16-11-12.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplyManagerImplTest {
    @InjectMocks
    AuditManager auditManager = new AuditManagerImpl();

    @InjectMocks
    ApplyManager applyManager = new ApplyManagerImpl();

    @Mock
    ApplyDAO applyDAO;

    @Mock
    UserDAO userDAO;


    private ApplyDO applyDO = new ApplyDO();

    private UserDO userDO = new UserDO();

    private UserDO param = new UserDO();

    private ApplyDO param2 = new ApplyDO();

    @Before
    public void setUp() throws Exception {
        long userId = 1L;
        long bossId = 2L;
        Date date1 = new Date();

        Date date2 = new Date();
        param.setBossId(2L);
        userDO.setUserId(userId);
        userDO.setBossId(bossId);
        applyDO.setUserId(userId);
        applyDO.setApplyType(1);
        param2.setUserId(userId);
    }

    @Test
    public void test_apply_expect_success() throws Exception{
        Mockito.doNothing().when(applyDAO).insertApplyDO(any(ApplyDO.class));
        applyManager.applyByType(10L,new SimpleDateFormat("yyyy-MM-dd").parse("2016-3-21"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2016-3-26"),1,"哈哈");
    }

    @Test
    public void test_apply_expect_exception() throws Exception{
        try {
            Mockito.doThrow(new DAOException()).when(applyDAO).insertApplyDO(any(ApplyDO.class));
            applyManager.applyByType(10L,new SimpleDateFormat("yyyy-MM-dd").parse("2016-03-21"),
                    new SimpleDateFormat().parse("2016-03-31"),1,"哈哈");
        }catch ( BLLException e ){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(ErrorConstantColletion.ApplyRuleException.APPLY_TOO_MUCH);
            return;
        }
        fail("未抛出期望的异常");
    }
    @Test
    public void test_selectApplyByType() throws Exception{
        List<ApplyDO> list = Lists.newArrayList(applyDO);
        Mockito.doReturn(list).when(applyDAO).selectApplyDO(applyDO);
        List<ApplyDO> result = applyManager.selectApplysByType(applyDO.getUserId(),applyDO.getApplyType());
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getUserId()).isEqualTo(applyDO.getUserId());
    }



}
