package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Certificate;
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
public class CertificateDAOImplTest {

    private CertificateDAOImpl certificateDAO = new CertificateDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        certificateDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addCertificate() {
        Certificate certificate = new Certificate(62, 13);
        assertTrue(certificateDAO.addCertificate(certificate));
        assertEquals(certificateDAO.findCertificates().size(), 11);
    }

    @Test
    public void findCertificates() {
        assertEquals(certificateDAO.findCertificates().size(), 10);
    }

    @Test
    public void findCertificate() {
        Certificate certificate = new Certificate(9, 63, 10);
        assertEquals(certificateDAO.findCertificate(9), certificate);
    }

    @Test
    @Rollback
    public void updateCertificate() {
        Certificate certificate = new Certificate(11, 62, 13);
        assertTrue(certificateDAO.updateCertificate(certificate));
        assertEquals(certificateDAO.findCertificate(11), certificate);
    }

    @Test
    @Rollback
    public void deleteCertificate() {
        assertTrue(certificateDAO.deleteCertificate(5));
        assertEquals(certificateDAO.findCertificates().size(), 9);
    }

}
