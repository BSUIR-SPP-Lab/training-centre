import {Component, OnInit, AfterViewInit, AfterContentChecked, OnChanges} from '@angular/core';
import {ActivatedRoute, Params, Router, RouterEvent} from '@angular/router';
import { CourseTemplateService } from '../../../services/courseTemplate.service';


class CourseTemplate {
  courseInfoId: number;
  description: string;
  name: string;
}

@Component({
  selector: 'app-course-info-list',
  templateUrl: './course-info-list.component.html',
  styleUrls: ['./course-info-list.component.css']
})
export class CourseInfoListComponent implements OnInit, AfterViewInit {

  courses: CourseTemplate[] = [];
  course: CourseTemplate;

  constructor(private courseTemplateService: CourseTemplateService, private router: Router) {
    router.events.filter(event => event instanceof RouterEvent).subscribe(event => {
      if (event['url'] === '/courseTemplate' ) {
        this.loadCourseTemplate();
        console.log(event['url'], 'update url');
      }
    });

  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.course = new CourseTemplate();

  }

  loadCourseTemplate() {
    this.courseTemplateService
      .getCourseTemplates().
    subscribe((courses: CourseTemplate[]) => {
        this.courses = courses;
      },
      (error) => {
        alert(error);
      }
    );
  }
}
