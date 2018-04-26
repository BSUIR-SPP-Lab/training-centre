import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Group} from "../../../shared/models/group.model";
import {NgForm} from "@angular/forms";
import {Message} from "../../../shared/models/message.model";
import {Course} from "../../../shared/models/course.model";
import {CourseService} from "../../../shared/services/course.service";
import {Subscription} from "rxjs/Subscription";
import {Observable} from "rxjs/Observable";
import {UsersService} from "../../../shared/services/users.service";
import {User} from "../../../shared/models/user.model";
import {GroupService} from "../../../shared/services/group.service";

@Component({
  selector: 'tc-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.scss']
})
export class AddGroupComponent implements OnInit {

  @Output() OutOnGroupAdd = new EventEmitter<Group>();

  message: Message;
  courses: Course[] = [];
  coordinators: User[] = [];
  sub1: Subscription;
  isLoaded = false;

  constructor(private courseService: CourseService,
              private userService: UsersService,
              private groupService: GroupService) { }

  ngOnInit() {
    this.message = new Message('danger', '');
    this.sub1 = Observable.combineLatest(
      this.courseService.getCourses(),
      this.userService.getUserByRole('COORDINATOR')
    )
      .subscribe((data: [Course[], User[]]) => {
        this.courses = data[0];
        this.coordinators = data[1];
        this.isLoaded = true;
      });
  }

  onSubmit(form: NgForm) {
    const {coordinator, course} = form.value;
    console.log(form.value);
    console.log(coordinator, course);
    const group = new Group(coordinator, course);
    console.log(group);

    this.groupService.addGroup(group)
      .then((res) => {
        console.log('res ', res);

        this.showMessage({
          text: 'Группа успешно добавлена',
          type: 'info'});
      })
      .catch((reason => {
        console.log(reason);
        this.showMessage({
          text: 'Ошибка добавления',
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
