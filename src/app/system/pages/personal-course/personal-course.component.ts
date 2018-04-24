import {Component, OnDestroy, OnInit} from '@angular/core';
import {CourseService} from "../../../shared/services/course.service";
import {Course} from "../../../shared/models/course.model";
import {AuthService} from "../../../shared/services/auth.service";
import {User} from "../../../shared/models/user.model";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'tc-personal-course',
  templateUrl: './personal-course.component.html',
  styleUrls: ['./personal-course.component.scss']
})
export class PersonalCourseComponent implements OnInit, OnDestroy {

  sub1: Subscription;
  courseList: Course[] = [];
  user: User;
  isLoaded = false;

  constructor(private courseService: CourseService,
              private authService: AuthService) { }

  ngOnInit() {

    this.sub1 = this.courseService.getCourseForID(this.authService.getId())
      .subscribe((courses: Course[]) => {
        this.courseList = courses;
        this.isLoaded = true;
      });

  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

}
