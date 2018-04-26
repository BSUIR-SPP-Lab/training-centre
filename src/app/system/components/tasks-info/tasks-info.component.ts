import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TaskInfo} from "../../../shared/models/taskInfo.model";
import {TaskInfoService} from "../../../shared/services/taskInfo.service";
import {Subscription} from "rxjs/Subscription";
import {Message} from "../../../shared/models/message.model";
import {NgForm} from "@angular/forms";
import {CourseInfo} from "../../../shared/models/courseInfo.model";

@Component({
  selector: 'tc-tasks-info',
  templateUrl: './tasks-info.component.html',
  styleUrls: ['./tasks-info.component.scss']
})
export class TasksInfoComponent implements OnInit {

  @Output() OutTaskInfoAdd = new EventEmitter<TaskInfo>();
  sub1: Subscription;
  private message: Message;
  constructor(private taskInfoService: TaskInfoService) { }

  ngOnInit() {
    this.message = new Message('danger', '');
  }

  onSubmit(form: NgForm) {

    console.log(form.value);
    const { name, body} = form.value;

    this.taskInfoService.addTask(new TaskInfo(body, name))
      .then((res) => {
        console.log('res ', res)
        this.OutTaskInfoAdd.emit(new TaskInfo(body, name));
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

  private showMessage(message: Message) {
    this.message = message;
    window.setTimeout( () => {
      this.message.text = '';
    }, 5000);
  }
}
