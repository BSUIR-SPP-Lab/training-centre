package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.dao.CertificateDAO;
import com.bsuir.trainingcenter.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CertificateDAOImpl implements CertificateDAO {

    private static final String queryAddCertificate = "INSERT INTO `certificate` (`student_id`, `group_id`) " +
            "VALUES (?, ?)";
    private static final String queryFindCertificates = "SELECT `certificate`.`certificate_id`, " +
            "`certificate`.`student_id`, `certificate`.`group_id` FROM `certificate`";
    private static final String queryFindCertificate = "SELECT `certificate`.`certificate_id`, " +
            "`certificate`.`student_id`, `certificate`.`group_id` FROM `certificate` " +
            "WHERE `certificate`.certificate_id = ?";
    private static final String queryUpdateCertificate = "UPDATE `certificate` SET `certificate`.`student_id` = ?, " +
            "`certificate`.`group_id` = ? WHERE `certificate`.`certificate_id` = ?";
    private static final String queryDeleteCertificate = "DELETE FROM `certificate` WHERE `certificate_id` = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Certificate> rowMapper = ((resultSet, i) -> {
        Certificate certificate = new Certificate();
        certificate.setCertificateId(resultSet.getLong("certificate_id"));
        certificate.setStudentId(resultSet.getLong("student_id"));
        certificate.setGroupId(resultSet.getLong("group_id"));
        return certificate;
    });

    @Override
    public boolean addCertificate(Certificate certificate) {
        return jdbcTemplate.update(queryAddCertificate, certificate.getStudentId(), certificate.getGroupId()) > 0;
    }

    @Override
    public List<Certificate> findCertificates() {
        return jdbcTemplate.query(queryFindCertificates, rowMapper);
    }

    @Override
    public Certificate findCertificate(long certificateId) {
        return jdbcTemplate.queryForObject(queryFindCertificate, new Object[]{certificateId}, rowMapper);
    }

    @Override
    public boolean updateCertificate(Certificate certificate) {
        return jdbcTemplate.update(queryUpdateCertificate, certificate.getStudentId(), certificate.getGroupId(),
                certificate.getCertificateId()) > 0;
    }

    @Override
    public boolean deleteCertificate(long certificateId) {
        return jdbcTemplate.update(queryDeleteCertificate, certificateId) > 0;
    }

}
