package cn.tju.scs.test.dao;

import cn.tju.scs.dao.ApplyDAO;
import cn.tju.scs.domain.ApplyDO;
import cn.tju.scs.domain.factory.ApplyFactory;
import cn.tju.scs.exception.DAOException;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lichen.ll on 2016/9/7.
 */
public class ApplyDAOTest extends TestBase {

    @Resource
    ApplyDAO applyDAO;

    @Test
    public void testSelectApplyDO() {
        ApplyDO applyDO = ApplyFactory.getEmptyApply();
        try {
            List list = applyDAO.selectApplyDO(applyDO);
            Assert.assertNotNull(list);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInserApplyDO() {
        ApplyDO applyDO = new ApplyDO();
        applyDO.setUserId(116L);
        applyDO.setStartDate(new Date());
        applyDO.setEndDate(new Date());
        applyDO.setReason("for a joke");
        applyDO.setApplyType(1);
        try {
            applyDAO.insertApplyDO(applyDO);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateApplyDO() {
        ApplyDO applyDO = new ApplyDO();
        applyDO.setApplicationId(2L);
        applyDO.setReason("hahaha");
        applyDO.setEndDate(new Date());
        try {
            applyDAO.updateApplyDO(applyDO);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteApplyDO(){
        try{
            applyDAO.deleteApplyDO(2L);
        }catch ( DAOException e ){
            e.printStackTrace();
        }
    }

}
