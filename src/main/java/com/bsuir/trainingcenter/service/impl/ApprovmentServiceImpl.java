package com.bsuir.trainingcenter.service.impl;

import com.bsuir.trainingcenter.dao.ApplicationDAO;
import com.bsuir.trainingcenter.dao.CourseDAO;
import com.bsuir.trainingcenter.dao.GroupDAO;
import com.bsuir.trainingcenter.dao.StudentGroupDAO;
import com.bsuir.trainingcenter.entity.Application;
import com.bsuir.trainingcenter.entity.Course;
import com.bsuir.trainingcenter.entity.Group;
import com.bsuir.trainingcenter.entity.StudentGroup;
import com.bsuir.trainingcenter.service.ApprovmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApprovmentServiceImpl implements ApprovmentService {


    private ApplicationDAO appDAO;
    private CourseDAO courseDAO;
    private GroupDAO groupDAO;
    private StudentGroupDAO studentGroupDAO;

    @Autowired
    public ApprovmentServiceImpl(ApplicationDAO appDAO, CourseDAO courseDAO, GroupDAO groupDAO, StudentGroupDAO studentGroupDAO) {
        this.appDAO = appDAO;
        this.courseDAO = courseDAO;
        this.groupDAO = groupDAO;
        this.studentGroupDAO = studentGroupDAO;
    }

    @Transactional
    @Override
    public boolean approveApplication(long appId) {
        Optional<Application> app = appDAO.findApplication(appId);
        if(app.isPresent()){
            Optional<Course> course = courseDAO.findCourse(app.get().getCourseId());
            List<Group> groups = groupDAO.findGroupsByCourseId(course.get().getCourseId());
            Group group =null;
            if(!groups.isEmpty()){
                group = groups.get(0);

            }else{
                if(groupDAO.addGroup(new Group(course.get().getCourseId(),course.get().getCoordinatorId()))){
                    group = groupDAO.findGroupsByCourseId(course.get().getCourseId()).get(0);
                }
            }
            appDAO.deleteApplication(appId);
            return studentGroupDAO.addStudentGroup(new StudentGroup(app.get().getStudentId(),group.getGroupId(),false));
        }
        return false;
    }
}
