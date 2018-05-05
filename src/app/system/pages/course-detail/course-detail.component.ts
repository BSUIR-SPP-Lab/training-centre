import {Component, OnDestroy, OnInit} from '@angular/core';
import {fadeStateTrigger} from "../../../shared/animations/fade.animation";
import {ActivatedRoute, Params} from "@angular/router";
import {CourseService} from "../../../shared/services/course.service";
import {Course} from "../../../shared/models/course.model";
import {Subscription} from "rxjs/Subscription";
import {AuthService} from "../../../shared/services/auth.service";
import {GroupService} from "../../../shared/services/group.service";


@Component({
  selector: 'tc-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.scss'],
  animations: [fadeStateTrigger]
})
export class CourseDetailComponent implements OnInit, OnDestroy {

  routeCourseListPDF = 'pdfUserListCourse/';
  routeCourseListCVS = 'cvsUserListCourse/';
  routeCourseListXLS = 'xlsUserListCourse/';
  // TODO this route
  routeGroupListPDF = 'pdfUserListInGroup/';
  routeGroupListCVS = 'csvUserListInGroup/';
  routeGroupListXLS = 'xlsUserListInGroup';

  routeTasksPDF = 'pdfGroupTasks/';
  routeTasksCVS = 'csvGroupTasks/';
  routeTasksXLS = 'xlsGroupTasks/';

  documentServerRoute: string;
  courseInfo: Course;
  isLoaded = false;
  sub1: Subscription;
  sub2: Subscription;
  courseID: number;
  groupID: number;

  constructor(private route: ActivatedRoute,
              private courseService: CourseService,
              private authService: AuthService,
              private groupService: GroupService) { }

  ngOnInit() {
    this.sub1 = this.route.params
      .mergeMap((params: Params) => this.courseService.getCourseForCourseID(params['id']))
      .subscribe((course: Course) => {
        this.courseInfo = course;
        this.documentServerRoute = this.courseService.getDocumentServerRoute();
        this.courseID = course.courseId;
        this.getGroup(this.courseID);
      });

  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }

    if (this.sub2) {
      this.sub2.unsubscribe();
    }
  }

  getGroup(courseID: number) {
    this.sub2 = this.groupService.getGroupNumberByCourseAndUserID(this.authService.getId(), courseID)
      .subscribe((groupID) => {
        this.groupID = groupID[0];
        this.isLoaded = true;
      });
  }

}

