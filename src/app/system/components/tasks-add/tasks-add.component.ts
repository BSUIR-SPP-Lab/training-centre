import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {TaskInfo} from "../../../shared/models/taskInfo.model";
import {TaskInfoService} from "../../../shared/services/taskInfo.service";
import {Message} from "../../../shared/models/message.model";
import {UsersService} from "../../../shared/services/users.service";
import {TaskService} from "../../../shared/services/task.service";
import {User} from "../../../shared/models/user.model";
import {NgForm} from "@angular/forms";
import {Observable} from "rxjs/Observable";
import {TeacherGroup} from "../../../shared/models/teacher.model";
import {TaskWithoutInfo} from "../../../shared/models/taskWithoutInfo.model";

@Component({
  selector: 'tc-tasks-add',
  templateUrl: './tasks-add.component.html',
  styleUrls: ['./tasks-add.component.scss']
})
export class TasksAddComponent implements OnInit, OnDestroy {

  @Input() tasksInfo: TaskInfo[] = [];
  isLoaded = false;
  message: Message;
  teachers: User[] = [];
  teachersGroups: TeacherGroup[] = [];
  teacherGroups: TeacherGroup[] = [];
  teacherId: number;
  taskInfoId: number;
  groupId: number;
  sub1: Subscription;
  end: Date;

  constructor(private userService: UsersService,
              private taskService: TaskService) { }

  ngOnInit() {
    this.end = new Date();
    this.message = new Message('danger', '');
    this.sub1 = Observable.combineLatest(
      this.userService.getUserByRole('TEACHER'),
      this.userService.getTeacherGroup()
    )
      .subscribe((data: [User[], TeacherGroup[]]) => {
        this.teachers = data[0];
        this.teachersGroups = data[1];
        this.teacherId = this.teachers[0].id;
        this.taskInfoId = this.tasksInfo[0].taskInfoId;
        this.onChangeTeacher(1);
      });
  }

  onSubmit(form: NgForm) {

    console.log(form.value);
    const {taskInfo, teacher, group, endTime } = form.value;

    const task = new TaskWithoutInfo(group, taskInfo, teacher, endTime);
    this.taskService.addTask(task)
      .then((res) => {
        console.log('res ', res);

        this.showMessage({
          text: 'Задание успешно добавлена',
          type: 'info'});
      })
      .catch((reason => {
        this.showMessage({
          text: 'Ошибка добавления',
          type: 'warning'});
      }));

  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

  onChangeTeacher(value) {
    console.log('change teacher ', value, this.teacherId, this.taskInfoId);
    console.log('all group' , this.teachersGroups);
    this.teacherGroups = this.teachersGroups.filter(g => g.teacherId === +this.teacherId);
    console.log('after filter group' , this.teacherGroups);
    this.groupId = this.teachersGroups[0].groupId;
  }

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }
}
