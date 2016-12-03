package cn.tju.scs.manager.impl;

import cn.tju.scs.constant.ErrorConstantColletion;
import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.BLLException;
import cn.tju.scs.exception.DAOException;
import cn.tju.scs.manager.AuditManager;
import cn.tju.scs.manager.UserManager;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.registry.infomodel.User;
import java.util.List;

import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Matchers.any;

/**
 * Created by liu on 16-11-24.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserManagerImplTest {
    @InjectMocks
    AuditManager auditManager = new AuditManagerImpl();
    @InjectMocks
    UserManager userManager = new UserManagerImpl();

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
        param.setUserId(3L);
        param.setPassword("123");
        param.setUserName("yangwentao");
        userDO.setUserId(userId);
        userDO.setBossId(bossId);
        applyDO.setUserId(userId);
        param2.setUserId(userId);
    }

    @Test
    public void getUserInfoById_test() throws Exception{
        List<UserDO> list = Lists.newArrayList(param);
        Mockito.doReturn(list).when(userDAO).selectUser(any(UserDO.class));
        UserDO test = userManager.getUserInfoById(1L);
        Assertions.assertThat(test).isNotNull();
        Assertions.assertThat(test.getUserId()).isEqualTo(1L);
    }
    @Test
    public void getUserInfoById_Exception() throws Exception{
        try{
            Mockito.doThrow(new DAOException()).when(userDAO).selectUser(any(UserDO.class));
            userManager.getUserInfoById(param.getUserId());
        }catch (BLLException e){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(ErrorConstantColletion.SYSTEM_ERROR.getErrorCode());
            return;
        }
        fail("未抛出期望异常");
    }

    @Test
    public void getUserListByBossId_test() throws Exception{
        List<UserDO> list = Lists.newArrayList(param);
        List<UserDO> list1 = Lists.newArrayList();
        Mockito.doReturn(list).when(userDAO).selectUser(any(UserDO.class));
        list1 = userManager.getUserListByBossId(2L);
        Assertions.assertThat(list1.get(0)).isEqualTo(list.get(0));
        Assertions.assertThat(list1).isNotNull();
    }

}
