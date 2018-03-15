package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest
@Transactional
@Sql("sql/sql_application.sql")
public class ApplicationDAOImplTest {


    private ApplicationDAOImpl applicationDAO = new ApplicationDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource){
        applicationDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addApplication() {
        assertTrue(applicationDAO.addApplication(new Application(0,43,2)));
        assertEquals(applicationDAO.findApplications().size(),3);

    }

    @Test
    public void findApplications() {

        assertEquals(applicationDAO.findApplications().size(),2);
    }

    @Test
    public void findApplication() {

    }

    @Test
    public void updateApplication() {
    }

    @Test
    @Rollback
    public void deleteApplication() {

        assertTrue(applicationDAO.deleteApplication(8));
        assertEquals(applicationDAO.findApplications().size(),1);

    }
}