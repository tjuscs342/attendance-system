package cn.tju.scs.test.dao;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lichen.ll on 2016/8/31.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc.xml", "classpath:spring-mybatis.xml"})
@Transactional
public class TestBase {
    @Before
    public void before() {

    }
}
