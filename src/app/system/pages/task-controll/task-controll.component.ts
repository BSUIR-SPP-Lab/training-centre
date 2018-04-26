import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs/Subscription";
import {TaskInfo} from "../../../shared/models/taskInfo.model";
import {TaskInfoService} from "../../../shared/services/taskInfo.service";

@Component({
  selector: 'tc-task-controll',
  templateUrl: './task-controll.component.html',
  styleUrls: ['./task-controll.component.scss']
})
export class TaskControllComponent implements OnInit, OnDestroy {

  sub1: Subscription;
  tasks: TaskInfo[] = [];
  isLoaded = false;

  constructor(private taskInfoService: TaskInfoService) { }

  ngOnInit() {
    this.sub1 = this.taskInfoService.getTasks()
      .subscribe((tasks: TaskInfo[]) => {
        this.tasks = tasks;
        this.isLoaded = true;
      });
  }

  ngOnDestroy() {
    if (this.sub1) {
      this.sub1.unsubscribe();
    }
  }

  onTaskInfoAdded(info: TaskInfo) {
    this.sub1 = this.taskInfoService.getTasks()
      .subscribe((tasks: TaskInfo[]) => {
        this.tasks = tasks;
      });
  }
}
