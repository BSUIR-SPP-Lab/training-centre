package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest
@Transactional
public class ApplicationDAOImplTest {

    private ApplicationDAOImpl applicationDAO = new ApplicationDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        applicationDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addApplication() {
        Application application = new Application(41, 5);
        assertTrue(applicationDAO.addApplication(application));
        assertEquals(applicationDAO.findApplications().size(), 13);
    }

    @Test
    public void findApplications() {
        assertEquals(applicationDAO.findApplications().size(), 12);
    }

    @Test
    public void findApplication() {
        Application app = new Application(5, 61, 5);
        assertEquals(applicationDAO.findApplication(5), app);
    }

    @Test
    @Rollback
    public void updateApplication() {
        Application app = new Application(5, 61, 2);
        assertTrue(applicationDAO.updateApplication(app));
        assertEquals(applicationDAO.findApplication(5), app);
    }

    @Test
    @Rollback
    public void deleteApplication() {
        assertTrue(applicationDAO.deleteApplication(5));
        assertEquals(applicationDAO.findApplications().size(), 11);
    }

}