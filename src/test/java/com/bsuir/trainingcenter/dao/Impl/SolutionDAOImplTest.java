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
        assertEquals(13, solutionDAO.findSolutionsByUserId().size());
    }

    @Test
    public void findSolutions() {
        assertEquals(12, solutionDAO.findSolutionsByUserId().size());
    }

    @Test
    public void findSolutionsByUserId() {
        assertEquals(2, solutionDAO.findSolutionsByUserId(66).size());
    }

    @Test
    public void findSolution() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Solution solution = new Solution(26, 43, null, "/main/answer/file16.txt",
                "Отлично", 10L, LocalDateTime.parse("2018-03-12 19:45:57", formatter));
        assertEquals(solution, solutionDAO.findSolution(solution.getTaskId(), solution.getUserId()));
    }

    @Test
    @Rollback
    public void updateSolution() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Solution solution = new Solution(26, 43, "", "", "", 0L,
                LocalDateTime.parse("2018-03-12 19:45:57", formatter));

        assertTrue(solutionDAO.updateSolution(solution));
        assertEquals(solution, solutionDAO.findSolution(solution.getTaskId(), solution.getUserId()));
    }

    @Test
    @Rollback
    public void updateSolutionMark() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Solution solution = new Solution(26, 43, null, "/main/answer/file16.txt",
                "Отлично", 0L, LocalDateTime.parse("2018-03-12 19:45:57", formatter));
        assertTrue(solutionDAO.updateSolutionMark(solution.getTaskId(), solution.getUserId(),
                solution.getTeacherNotes(), solution.getMark()));
        assertEquals(solution, solutionDAO.findSolution(solution.getTaskId(), solution.getUserId()));
    }

    @Test
    @Rollback
    public void deleteSolution() {
        assertTrue(solutionDAO.deleteSolution(22, 63));
        assertEquals(11, solutionDAO.findSolutionsByUserId().size());
    }

}
