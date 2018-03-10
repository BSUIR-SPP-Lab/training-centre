import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';

import { NgForm } from '@angular/forms';
import { CourseTemplateService } from '../../../services/courseTemplate.service';


class CourseTemplate {
  courseInfoId: number;
  description: string;
  name: string;
}

@Component({
  selector: 'app-course-template',
  templateUrl: './course-template.component.html',
  styleUrls: ['./course-template.component.css']
})


export class CourseTemplateComponent implements OnInit {

  constructor(private route: ActivatedRoute, private courseTemplateService: CourseTemplateService, private router: Router) { }

  course: CourseTemplate;

  isChanged: boolean;
  isCreated: boolean;


  ngOnInit() {
    this.course = new CourseTemplate();
    this.course.courseInfoId = this.route.snapshot.params['id'];
    if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
      this.isChanged = false;
      this.isCreated = true;
      console.log(this.route.snapshot.params['id']);
      this.course.courseInfoId = +this.route.snapshot.params['id'];
      this.loadCourseTemplates(this.course.courseInfoId);
    } else {
      this.isChanged = true;
      this.isCreated = false;
      this.course = new CourseTemplate();
    }
    this.route.params.subscribe((params: Params ) => {
      if ((this.route.snapshot.params['id'] !== undefined) && (this.route.snapshot.params['id'] !== 'add')) {
        console.log(this.route.snapshot.params['id']);
        this.isChanged = true;
        this.isCreated = false;
        this.course.courseInfoId = +this.route.snapshot.params['id'];
        this.loadCourseTemplates(this.course.courseInfoId);
      } else {
        this.isChanged = false;
        this.isCreated = true;
        this.course = new CourseTemplate();
      }
    });
  }

  loadCourseTemplates(id: number) {
    this.courseTemplateService
      .getCourseTemplate(id)
      .subscribe( (course: CourseTemplate) => {
          this.course = course;
        },
        (error) => {
          alert('Error loading template');
        });
  }

  deleteCourseTemplate() {
    this.courseTemplateService
      .deleteCourseTemplate(this.course.courseInfoId)
      .then(() => {
          console.log('Template was deleted');
          this.router.navigate(['/courseTemplate']);
        },
        (error) => {
          alert('Error while deleting template');
        });

  }


  submitForm(form: NgForm) {
    console.log(form);
    if (this.isCreated) {
      console.log('isCreated');
      // console.log(' sub ', this.user );
      this.courseTemplateService.addCourseTemplate(this.course)
        .then(_ => {
          console.log('course was added');
          this.course = new CourseTemplate();
          this.router.navigate(['/courseTemplate']);

        });

    } else {
      if (this.isChanged) {
        console.log('isChanged');
        this.courseTemplateService.updateCourseTemplate(this.course)
          .then(_ => {
            console.log('Course Template was changed');
            this.router.navigate(['/courseTemplate']);
          });
      }
    }

  }
}
