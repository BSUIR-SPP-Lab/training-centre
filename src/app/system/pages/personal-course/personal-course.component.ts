import { Component, OnInit } from '@angular/core';
import {CourseService} from "../../../shared/services/course.service";
import {Course} from "../../../shared/models/course.model";
import {AuthService} from "../../../shared/services/auth.service";
import {User} from "../../../shared/models/user.model";

@Component({
  selector: 'tc-personal-course',
  templateUrl: './personal-course.component.html',
  styleUrls: ['./personal-course.component.scss']
})
export class PersonalCourseComponent implements OnInit {

  courseList: Course[] = [];
  user: User;

  constructor(private courseService: CourseService,
              private authService: AuthService) { }

  ngOnInit() {
    //TODO Sub ant other
    this.user = JSON.parse(window.localStorage.getItem('user'));

    //this.courseList = this.courseService.getCourseForID(this.user.id);
    //console.log(this.courseList);

  }

}
