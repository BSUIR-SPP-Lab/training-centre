import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Data, Params, Router} from '@angular/router';
import { UsersService } from '../../../services/users.service';
import { CourseTemplateService } from '../../../services/courseTemplate.service';
import { CourseService } from '../../../services/course.service';
import { NgForm } from '@angular/forms';


class User {
  email: string;
  firstName: string;
  id: number;
  lastName: string;
  login: string;
  password: string;
  phone: string;
  role: string;
}

class CourseTemplate {
  courseInfoId: number;
  description: string;
  name: string;
}

class Course {
  courseId: number;
  courseInfoId: number;
  coordinatorId: number;
  end: Data;
  start: Data;
}


@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private router: Router,
              private usersService: UsersService,
              private courseTemplateService: CourseTemplateService,
              private courseService: CourseService ) { }

  users: User[] = [];
  courseTemplates: CourseTemplate[] = [];
  course: Course;

  isChanged: boolean;
  isCreated: boolean;

  ngOnInit() {
    this.loadCourseTemplate();
    this.loadUsers();

    this.course = new Course();

    this.course.courseId = this.route.snapshot.params['id'];
    if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
      this.isChanged = false;
      this.isCreated = true;
      console.log(this.route.snapshot.params['id']);
      this.course.courseId = +this.route.snapshot.params['id'];
      this.loadCourse(this.course.courseId);
    } else {
      this.isChanged = true;
      this.isCreated = false;
      this.course = new Course();
    }
    this.route.params.subscribe((params: Params ) => {
      if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
        console.log(this.route.snapshot.params['id']);
        this.isChanged = true;
        this.isCreated = false;
        this.course.courseId = +this.route.snapshot.params['id'];
        this.loadCourse(this.course.courseId);
      } else {
        this.isChanged = false;
        this.isCreated = true;
        this.course = new Course();
      }
    });
  }

  loadCourse(id: number) {
    this.courseService
      .getCourse(id)
      .subscribe( (course: Course) => {
          this.course = course;
        },
        (error) => {
          alert('Error loading task');
        });
  }

  deleteCourse() {
    this.courseService
      .deleteCourse(this.course.courseId)
      .then(() => {
          console.log('Task was deleted');
          this.router.navigate(['/task']);
        },
        (error) => {
          alert('Error while deleting task');
        });

  }


  submitForm(form: NgForm) {

    // console.log(this.course);

    if (this.isCreated) {
      console.log('isCreated');
      console.log('obj', this.course);
      this.courseService.addCourse(this.course)
        .then(_ => {
          console.log('course was added');
          this.course = new Course();
          this.router.navigate(['/course']);

        });

    } else {
      if (this.isChanged) {
        console.log('isChanged');
        this.courseService.updateCourse(this.course)
          .then(_ => {
            console.log('course  was changed');
            this.router.navigate(['/course']);
          });
      }
    }



  }

  loadCourseTemplate() {
    this.courseTemplateService
      .getCourseTemplates()
      .subscribe( (course: CourseTemplate[]) => {
          this.courseTemplates = course;
        },
        (error) => {
          alert(error);
        }
      );
  }

  loadUsers() {
    this.usersService
      .getUsers().subscribe((users: User[]) => {
        users.forEach( (user: User) => {

          if (user.role === 'COORDINATOR') {
            this.users.push(user);
          }
        });
      },
      (error) => {
        alert(error);
      }
    );
  }


}
