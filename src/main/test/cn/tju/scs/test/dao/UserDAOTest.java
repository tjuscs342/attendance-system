package cn.tju.scs.test.dao;

import cn.tju.scs.dao.UserDAO;
import cn.tju.scs.domain.UserDO;
import cn.tju.scs.exception.DAOException;
import org.junit.Assert;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lichen.ll on 2016/8/31.
 */
public class UserDAOTest extends TestBase {


    @Resource
    UserDAO userDAO;

    @Test
    public void testSelectUser ( ){
        UserDO param = new UserDO();
        param.setUserId(116L);
        try {
            List<UserDO> userDO = userDAO.selectUser(param);
            Assert.assertNotNull( userDO );
        }catch(DAOException e ){
            e.printStackTrace();;
        }
    }

    @Test
    public void testInsertUser( ){
        try{
            UserDO userDO = new UserDO();
            userDO.setAge(11);
            userDO.setBossId(123L);
            userDO.setChildNum(0);
            userDO.setMarryTimes(0);
            userDO.setPassword("123");
            userDO.setPhone("1234567");
            userDO.setUserName("shuanglang");
            userDO.setUserPower(1);
            userDO.setGmtCreate( new Date());
            userDO.setGmtModified(new Date());
            userDO.setSex(0);
            userDAO.insertUser( userDO );
        }catch(DAOException e ){
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateContactInfo ( ){
        String phone = "4545454554";
        Long userId = 116L;
        try {
            userDAO.updateContactInfo(userId, phone);
        }catch ( DAOException e ){
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdatePower ( ){
        Integer power = 2;
        Long userId = 116L;
        try{
            userDAO.updatePower(userId , power);
        }catch ( DAOException e ){
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteUser ( ){
        Long userId = 118L;
        try{
            userDAO.deleteUser( userId );
        }catch ( DAOException e ){
            e.printStackTrace();
        }
    }


}
