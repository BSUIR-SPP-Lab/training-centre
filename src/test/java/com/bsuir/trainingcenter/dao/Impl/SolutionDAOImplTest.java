package com.bsuir.trainingcenter.dao.Impl;

import com.bsuir.trainingcenter.config.TestConfig;
import com.bsuir.trainingcenter.entity.Solution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@SpringBootTest
@Transactional
public class SolutionDAOImplTest {

    private SolutionDAOImpl solutionDAO = new SolutionDAOImpl();

    @Autowired
    private void setDataSource(DataSource dataSource) {
        solutionDAO.setDataSource(dataSource);
    }

    @Test
    @Rollback
    public void addSolution() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Solution solution = new Solution(21, 41, "", "", "", 0L,
                LocalDateTime.parse("2018-01-01 00:00", formatter));
        assertTrue(solutionDAO.addSolution(solution));
        assertEquals(solutionDAO.findSolutions().size(), 13);
    }

    @Test
    public void findSolutions() {
        assertEquals(solutionDAO.findSolutions().size(), 12);
    }

    @Test
    public void findSolutionsByUserId() {
        assertEquals(solutionDAO.findSolutions(66).size(), 2);
    }

    @Test
    @Rollback
    public void updateSolution() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Solution solution = new Solution(21, 64, "", "", "", 0L,
                LocalDateTime.parse("2018-01-01 00:00", formatter));
        assertTrue(solutionDAO.updateSolution(solution));
    }

    @Test
    @Rollback
    public void updateSolutionMark() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        long taskId = 21;
        long userId = 64;
        String teacherNotes = "";
        long mark = 10;
        assertTrue(solutionDAO.updateSolutionMark(taskId, userId, teacherNotes, mark));
    }

    @Test
    @Rollback
    public void deleteSolution() {
        assertTrue(solutionDAO.deleteSolution(22, 63));
        assertEquals(solutionDAO.findSolutions().size(), 11);
    }

}
