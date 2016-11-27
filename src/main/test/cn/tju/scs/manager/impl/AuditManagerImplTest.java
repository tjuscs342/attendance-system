package cn.tju.scs.manager.impl;

import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
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

import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.*;

/**
 *
 * Created by lichen.ll on 16/11/12.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuditManagerImplTest {

    @InjectMocks
    AuditManager auditManager = new AuditManagerImpl();

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
        param.setBossId(2L);
        userDO.setUserId(userId);
        userDO.setBossId(bossId);
        applyDO.setUserId(userId);
        param2.setUserId(userId);
    }

    @Test
    public void test_auditApply_expect_success() throws Exception {
        Mockito.doNothing().when(applyDAO).updateApplyDO(any(ApplyDO.class));
        auditManager.auditApply(1L,1,"呵呵",2L,"嘿嘿");
    }

    @Test
    public void test_auditApplt_expect_exception ( ) throws Exception {
        try {
            Mockito.doThrow(new DAOException()).when(applyDAO).updateApplyDO(any(ApplyDO.class));
            auditManager.auditApply(1L, 1, "呵呵", 2L, "嘿嘿");
        }catch ( BLLException e ){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(ErrorConstantColletion.SYSTEM_ERROR.getErrorCode());
            return;
        }
        fail("未抛出期望的异常");
    }

    @Test
    public void selectApplys() throws Exception {
        List<UserDO> users = Lists.newArrayList(userDO);
        List<ApplyDO> applyDOs = Lists.newArrayList(applyDO);
        Mockito.doReturn(users).when(userDAO).selectUser(any(UserDO.class));
        Mockito.doReturn(applyDOs).when(applyDAO).selectApplyDO(any(ApplyDO.class));
        List<ApplyDO> result = auditManager.selectApplys(users.get(0).getBossId());
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result.get(0).getUserId()).isEqualTo(users.get(0).getUserId());
    }
    @Test
    public void selectApplys_fail() throws Exception{
        try{
            Mockito.doThrow(new DAOException()).when(applyDAO).selectApplyDO(any(ApplyDO.class));
            auditManager.selectApplys(userDO.getBossId());
        }catch (BLLException e){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(ErrorConstantColletion.SYSTEM_ERROR.getErrorCode());
            return;
        }
        fail("未抛出期望异常");
    }
}