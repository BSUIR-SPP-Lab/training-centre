import {Component, Input, OnInit} from '@angular/core';
import {CourseInfo} from "../../../shared/models/courseInfo.model";
import {Message} from "../../../shared/models/message.model";
import {NgForm} from "@angular/forms";
import {User} from "../../../shared/models/user.model";
import {Subscription} from "rxjs/Subscription";
import {UsersService} from "../../../shared/services/users.service";
import {CourseService} from "../../../shared/services/course.service";
import {CourseWithoutInfo} from "../../../shared/models/courseWithoutInfo.model";

@Component({
  selector: 'tc-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.scss']
})
export class CourseComponent implements OnInit {

  message: Message;
  coordinators: User[] = [];
  coordId: number;
  sub1: Subscription;
  start: Date;
  end: Date;
  @Input() coursesInfo: CourseInfo[] = [];

  constructor(private userService: UsersService,
              private courseService: CourseService) { }

  ngOnInit() {
    this.start = new Date();
    this.end = new Date();
    this.message = new Message('danger', '');
    this.sub1 = this.userService.getUserByRole('COORDINATOR')
      .subscribe((coords: User[]) => {
        this.coordinators = coords;
        this.coordId = this.coordinators[0].id;
      });
  }

  onSubmit(form: NgForm) {

    console.log(form.value);
     const { courseInfo, coordinator, initTime, endTime} = form.value;

    this.courseService.addCourse(new CourseWithoutInfo( +coordinator, +courseInfo, endTime, initTime))
      .then((res) => {
        console.log('res ', res);

        this.showMessage({
          text: 'Курс успешно добавлен',
          type: 'info'});
      })
      .catch((reason => {
        this.showMessage({
          text: 'Ошибка добавления курса',
          type: 'warning'});
      }));

  }

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }

}
