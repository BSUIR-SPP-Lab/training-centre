import {Component, Input, OnInit} from '@angular/core';
import {Group} from "../../../shared/models/group.model";
import {User} from "../../../shared/models/user.model";
import {Course} from "../../../shared/models/course.model";
import {UsersService} from "../../../shared/services/users.service";
import {CourseService} from "../../../shared/services/course.service";
import {Message} from "../../../shared/models/message.model";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'tc-add-teacher-to-group',
  templateUrl: './add-teacher-to-group.component.html',
  styleUrls: ['./add-teacher-to-group.component.scss']
})
export class AddTeacherToGroupComponent implements OnInit {

  @Input() groups: Group[] = [];
  courseGroup: Group[] = [];
  users: User[] = [];
  courses: Course[] = [];
  message: Message;
  sub1: Subscription;
  isLoaded = false;

  constructor(private  userService: UsersService,
              private courseService: CourseService) { }

  ngOnInit() {
    this.message = new Message('danger', '');
    this.sub1 = Observable.combineLatest(
      this.courseService.getCourses(),
      this.userService.getUsers()
    )
      .subscribe((data: [Course[], User[]]) => {
        this.courses = data[0];
        this.users = data[1];
        this.isLoaded = true;
      });
  }

  onChangeCourse(form) {
    console.log(form);
    const course = +form['course'];
    console.log(course);
    this.courseGroup = this.groups.filter(g => g.courseId === course);
    console.log(this.courseGroup);

  }

  onSubmit(form: NgForm) {
    const {course, grou, user} = form.value;
    console.log(form.value);

    const teachers = this.users.filter(u => u.id === +user);
    console.log('teachers', teachers);
    let teacher = teachers[0];
    console.log('teacher', teacher);
    teacher.role = 'TEACHER';
    console.log('teacher', teacher);
    this.userService.updateUser(teacher)
      .then( res => {
        this.userService.addTeacher(user, grou)
          .then((res2) => {
            this.showMessage({
              text: 'Преподаватель успешно добавлен',
              type: 'info'});
          })
          .catch((reason => {
            console.log(reason);
            this.showMessage({
              text: 'Ошибка добавления',
              type: 'warning'});
          }));
      })
      .catch(reason => {
        this.showMessage({
          text: 'Ошибка добавления',
          type: 'warning'});
      });




  }

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }

}
