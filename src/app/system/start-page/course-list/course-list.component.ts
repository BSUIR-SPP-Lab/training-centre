import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { Course } from '../../../shared/models/course.model';


@Component({
  selector: 'tc-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.scss']
})
export class CourseListComponent implements OnInit {
  @Input() courseList: Course[];
  @Output() enRollEvent = new EventEmitter<any>();

  constructor() { }

  ngOnInit() {
  }

  onEnroll(courseID) {
    this.enRollEvent.emit(courseID);
  }

}
