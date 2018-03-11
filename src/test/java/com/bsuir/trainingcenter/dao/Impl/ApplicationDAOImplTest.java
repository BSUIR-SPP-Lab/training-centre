package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest
@Sql(scripts = "/sql/sql_application.sql")
public class ApplicationDAOImplTest {


    private ApplicationDAOImpl applicationDAO = new ApplicationDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource){
        applicationDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addApplication() {
        assertEquals(applicationDAO.findApplications(),null);
    }

    @Test
    public void findApplications() {
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
        applicationDAO.deleteApplication(1);
    }
}