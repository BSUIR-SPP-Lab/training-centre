import { Component, OnInit, AfterViewInit } from '@angular/core';

import { Router, RouterEvent, Data } from '@angular/router';
import { CourseService } from '../../../services/course.service';


class Course {
  courseId: number;
  courseInfoId: number;
  coordinatorId: number;
  end: Data;
  start: Data;
}


@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit, AfterViewInit {

  courses: Course[] = [];
  course: Course;

  constructor(private courseService: CourseService , private router: Router) {
    router.events.filter(event => event instanceof RouterEvent).subscribe(event => {
      if (event['url'] === '/course' ) {
        this.loadCourse();
        console.log(event['url'], 'update url (course)');
      }
    });

  }

  ngAfterViewInit() {
    this.course = new Course();
    // this.loadTaskTemplate();
  }


  ngOnInit() {
  }

  loadCourse() {
    this.courseService
      .getCourses()
      .subscribe( (courses: Course[]) => {
          this.courses = courses;
        },
        (error) => {
          alert(error);
        }
      );
  }
}
