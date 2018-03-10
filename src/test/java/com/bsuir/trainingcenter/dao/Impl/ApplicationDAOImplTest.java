package com.bsuir.trainingcenter.dao.Impl;

import com.ibatis.common.jdbc.ScriptRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;

public class ApplicationDAOImplTest {
    private static ScriptRunner scriptRunner;
    private static ApplicationDAOImpl applicationDAO;

    @BeforeClass
    public static void beforeClass() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(ApplicationDAOImplTest.class.getResourceAsStream("/db.properties"));
        scriptRunner = new ScriptRunner(properties.getProperty("driver_name"),"jdbc:mysql://localhost:3306/?useUnicode=true&useSSL=false&serverTimezone=GMT",
                properties.getProperty("user"), properties.getProperty("password"),false, true);
        scriptRunner.runScript(new InputStreamReader(ApplicationDAOImplTest.class.getResourceAsStream("/Insert.sql")));
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName(properties.getProperty("driver_name"))
                .url(properties.getProperty("connection_string"))
                .password(properties.getProperty("password"))
                .username(properties.getProperty("user"))
                .build();
        applicationDAO = new ApplicationDAOImpl();
        applicationDAO.setDataSource(dataSource);
    }


    @AfterClass
    public void tearDown() throws Exception {
        Reader reader = new InputStreamReader(ApplicationDAOImplTest.class.getResourceAsStream("/Drop.sql"));
        scriptRunner.runScript(reader);


    }

    @Before
    public void setUp() throws Exception {
        scriptRunner.runScript(new InputStreamReader(ApplicationDAOImplTest.class.getResourceAsStream("/insert/insert_address.sql")));
    }




    @Test
    public void setDataSource() {
    }

    @Test
    public void addApplication() {
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
    public void deleteApplication() {
    }
}