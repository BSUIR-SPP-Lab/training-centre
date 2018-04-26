import {Component, OnDestroy, OnInit} from '@angular/core';
import {CourseInfo} from "../../../shared/models/courseInfo.model";
import {CourseInfoService} from "../../../shared/services/courseInfo.service";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'tc-course-controller',
  templateUrl: './course-controller.component.html',
  styleUrls: ['./course-controller.component.scss']
})
export class CourseControllerComponent implements OnInit, OnDestroy {

  isLoaded = false;
  coursesInfo: CourseInfo[] = [];
  sub1: Subscription;

  constructor(private courseInfoService: CourseInfoService) { }

  ngOnInit() {
    this.sub1 = this.courseInfoService.getCourses()
      .subscribe((courses: CourseInfo[]) => {
        this.coursesInfo = courses;
        console.log('start', this.coursesInfo);
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

  newCourseInfoAdd(info: CourseInfo) {
    this.sub1 = this.courseInfoService.getCourses()
      .subscribe((courses: CourseInfo[]) => {
        this.coursesInfo = courses;
        console.log('new', this.coursesInfo);
      });
  }
}
