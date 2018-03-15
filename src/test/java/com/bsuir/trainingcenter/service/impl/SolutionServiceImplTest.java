package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.SolutionDAO;
import com.bsuir.trainingcenter.entity.Solution;
import com.bsuir.trainingcenter.entity.view.SolutionView;
import com.bsuir.trainingcenter.service.SolutionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SolutionServiceImplTest {

    @MockBean
    public SolutionDAO solutionDAO;

    @Autowired
    public SolutionService solutionService;

    List<Solution> list;
    Solution solution;
    List<SolutionView> listResult;
    SolutionView solutionView;

    @Before
    public void setUp(){
        list = new ArrayList<>();
        LocalDateTime l = LocalDateTime.now();
        solution = new Solution(1,1,"test","testFilepath","testNotes",1l, l);
        list.add(solution);
        listResult = new ArrayList<>();
        solutionView = new SolutionView(1,1,"test","testFilepath","testNotes",1l,l.toString());
        listResult.add(solutionView);

    }
    @Test
    public void addSolution() {
        given(solutionDAO.addSolution(solution)).willReturn(true);
        assertTrue(solutionService.addSolution(solutionView));
    }

    @Test
    public void findSolutions_0() {
        given(solutionDAO.findSolutions()).willReturn(list);
        assertEquals(solutionService.findSolutions(),listResult);
    }

    @Test
    public void findSolutions_1() {
        given(solutionDAO.findSolutions(1)).willReturn(list);
        assertEquals(solutionService.findSolutions(1),listResult);
    }

    @Test
    public void updateSolution() {
        given(solutionDAO.updateSolution(solution)).willReturn(true);
        assertTrue(solutionService.updateSolution(solutionView));
    }

    @Test
    public void updateSolutionMark() {
        given(solutionDAO.updateSolutionMark(1,1,"testnote",1)).willReturn(true);
        assertTrue(solutionService.updateSolutionMark(1,1,"testnote",1));
    }

    @Test
    public void deleteSolution() {
        given(solutionDAO.deleteSolution(1,2)).willReturn(true);
        assertTrue(solutionService.deleteSolution(1,2));
    }
}