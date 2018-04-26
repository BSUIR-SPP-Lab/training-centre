import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Message} from "../../../shared/models/message.model";
import {CourseInfo} from "../../../shared/models/courseInfo.model";
import {NgForm} from "@angular/forms";
import {CourseInfoService} from "../../../shared/services/courseInfo.service";

@Component({
  selector: 'tc-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.scss']
})
export class CourseInfoComponent implements OnInit {

  message: Message;
  courseName: string;
  courseBody: string;

  @Output() onCourseInfoAdd = new EventEmitter<CourseInfo>();

  constructor(private courseInfoService: CourseInfoService) { }


  onSubmit(form: NgForm) {

    console.log(form.value);
    const { name, body} = form.value;

    this.courseInfoService.addCourse(new CourseInfo(body, name))
      .then((res) => {
      console.log('res ', res)
        this.onCourseInfoAdd.emit(new CourseInfo(body, name));
        this.showMessage({
          text: 'Шаблон успешно добавлен',
          type: 'info'});
      })
      .catch((reason => {
        this.showMessage({
          text: 'Ошибка добавления шаблона',
          type: 'warning'});
      }));

  }

  ngOnInit() {
    this.message = new Message('danger', '');
  }


  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }
}
